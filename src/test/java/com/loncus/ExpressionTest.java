package com.loncus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.loncus.config.ExpressionConfiguration;
import com.loncus.data.EvaluationValue;
import com.loncus.parser.ASTNode;
import com.loncus.parser.ParseException;
import java.math.MathContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ExpressionTest {

  @Test
  void testExpressionDefaults() {
    Expression expression = new Expression("a+b");

    assertThat(expression.getExpressionString()).isEqualTo("a+b");
    assertThat(expression.getConfiguration().getMathContext())
        .isEqualTo(ExpressionConfiguration.DEFAULT_MATH_CONTEXT);
    assertThat(expression.getConfiguration().getFunctionDictionary().hasFunction("SUM")).isTrue();
    assertThat(expression.getConfiguration().getOperatorDictionary().hasInfixOperator("+"))
        .isTrue();
    assertThat(expression.getConfiguration().getOperatorDictionary().hasPrefixOperator("+"))
        .isTrue();
    assertThat(expression.getConfiguration().getOperatorDictionary().hasPostfixOperator("+"))
        .isFalse();
  }

  @Test
  void testValidateOK() throws ParseException {
    new Expression("1+1").validate();
  }

  @Test
  void testValidateFail() {
    assertThatThrownBy(() -> new Expression("2#3").validate())
        .isInstanceOf(ParseException.class)
        .hasMessage("Undefined operator '#'");
  }

  @Test
  void testExpressionNode() throws ParseException, EvaluationException {
    Expression expression = new Expression("a*b");
    ASTNode subExpression = expression.createExpressionNode("4+3");

    EvaluationValue result = expression.with("a", 2).and("b", subExpression).evaluate();

    assertThat(result.getStringValue()).isEqualTo("14");
  }

  @Test
  void testWithValues() throws ParseException, EvaluationException {
    Expression expression = new Expression("(a + b) * (a - b)");

    Map<String, Object> values = new HashMap<>();
    values.put("a", 3.5);
    values.put("b", 2.5);

    EvaluationValue result = expression.withValues(values).evaluate();

    assertThat(result.getStringValue()).isEqualTo("6");
  }

  @Test
  void testWithValuesDoubleMap() throws ParseException, EvaluationException {
    Expression expression = new Expression("a+b");

    Map<String, Double> values = new HashMap<>();
    values.put("a", 3.9);
    values.put("b", 3.1);

    EvaluationValue result = expression.withValues(values).evaluate();

    assertThat(result.getStringValue()).isEqualTo("7");
  }

  @Test
  void testWithValuesStringMap() throws ParseException, EvaluationException {
    Expression expression = new Expression("a+b+c");

    Map<String, String> values = new HashMap<>();
    values.put("a", "Hello");
    values.put("b", " ");
    values.put("c", "world");

    EvaluationValue result = expression.withValues(values).evaluate();

    assertThat(result.getStringValue()).isEqualTo("Hello world");
  }

  @Test
  void testWithValuesMixedMap() throws ParseException, EvaluationException {
    Expression expression = new Expression("a+b+c");

    Map<String, Object> values = new HashMap<>();
    values.put("a", true);
    values.put("b", " ");
    values.put("c", 24.7);

    EvaluationValue result = expression.withValues(values).evaluate();

    assertThat(result.getStringValue()).isEqualTo("true 24.7");
  }

  @Test
  void testDefaultExpressionOwnsOwnConfigurationEntries() {
    Expression expression1 = new Expression("1+1");
    Expression expression2 = new Expression("1+1");

    assertThat(expression1.getDataAccessor()).isNotSameAs(expression2.getDataAccessor());
    assertThat(expression1.getConfiguration().getOperatorDictionary())
        .isNotSameAs(expression2.getConfiguration().getOperatorDictionary());
    assertThat(expression1.getConfiguration().getFunctionDictionary())
        .isNotSameAs(expression2.getConfiguration().getFunctionDictionary());
    assertThat(expression1.getConfiguration().getDefaultConstants())
        .isNotSameAs(expression2.getConfiguration().getDefaultConstants());
  }

  @Test
  void testDoubleConverterDefaultMathContext() {
    Expression defaultMathContextExpression = new Expression("1");
    assertThat(defaultMathContextExpression.convertDoubleValue(1.67987654321).getNumberValue())
        .isEqualByComparingTo("1.67987654321");
  }

  @Test
  void testDoubleConverterLimitedMathContext() {
    Expression limitedMathContextExpression =
        new Expression(
            "1", ExpressionConfiguration.builder().mathContext(new MathContext(3)).build());
    assertThat(limitedMathContextExpression.convertDoubleValue(1.6789).getNumberValue())
        .isEqualByComparingTo("1.68");
  }

  @Test
  void testGetAllASTNodes() throws ParseException {
    Expression expression = new Expression("1+2/3");
    List<ASTNode> nodes = expression.getAllASTNodes();
    assertThat(nodes.get(0).getToken().getValue()).isEqualTo("+");
    assertThat(nodes.get(1).getToken().getValue()).isEqualTo("1");
    assertThat(nodes.get(2).getToken().getValue()).isEqualTo("/");
    assertThat(nodes.get(3).getToken().getValue()).isEqualTo("2");
    assertThat(nodes.get(4).getToken().getValue()).isEqualTo("3");
  }

  @Test
  void testGetUsedVariables() throws ParseException {
    //  Expression expression = new Expression("A/2*PI+MIN(e,b)");
    Expression expression =
        new Expression(
            "{d2776448-620b-4e80-8e4d-6db6ccc269cf}/(1-{474ec7e1-bf0f-4001-ae19-8baedada25c9}/100)-{474ec7e1-bf0f-4001-ae19-8baedada25c9}");

    Expression expression1 =
        new Expression(
            "{edfbb9b6-562a-4d86-a3ab-ddf2eb15f5f8}-MOVE({f63658e8-9aca-4ed7-abcd-7123f82bda9c},\"DAY\",1)+MOVE({3ef13fa5-7551-4eb6-b44e-1d01ca70bdc8},\"DAY\",1)");
    Set vars = expression.getUsedVariables();
    Set vars1 = expression1.getUsedVariables();
    assertThat(vars)
        .containsExactlyInAnyOrder(
            "{d2776448-620b-4e80-8e4d-6db6ccc269cf}", "{474ec7e1-bf0f-4001-ae19-8baedada25c9}");
  }

  @Test
  void testGetUsedVariablesLongNames() throws ParseException {
    Expression expression = new Expression("var1/2*PI+MIN(var2,var3)");
    assertThat(expression.getUsedVariables()).containsExactlyInAnyOrder("var1", "var2", "var3");
  }

  @Test
  void testGetUsedVariablesNoVariables() throws ParseException {
    Expression expression = new Expression("1/2");
    assertThat(expression.getUsedVariables()).isEmpty();
  }

  @Test
  void testGetUsedVariablesCaseSensitivity() throws ParseException {
    Expression expression = new Expression("a+B*b-A/PI*(1/2)*pi+e-E+a");
    assertThat(expression.getUsedVariables()).containsExactlyInAnyOrder("a", "b");
  }

  @Test
  void testGetUndefinedVariables() throws ParseException {
    Expression expression = new Expression("a+A+b+B+c+C+E+e+PI+x").with("x", 1);
    assertThat(expression.getUndefinedVariables()).containsExactlyInAnyOrder("a", "b", "c");
  }
}
