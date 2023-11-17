package com.loncus.operators;

import static com.loncus.operators.OperatorIfc.OperatorType.PREFIX_OPERATOR;

import com.loncus.config.ExpressionConfiguration;
import lombok.Getter;

/**
 * Abstract implementation of the {@link OperatorIfc}, used as base class for operator
 * implementations.
 */
public abstract class AbstractOperator implements OperatorIfc {

  @Getter private final int precedence;

  private final boolean leftAssociative;

  OperatorType type;

  /**
   * Creates a new operator and uses the {@link InfixOperator} annotation to create the operator
   * definition.
   */
  protected AbstractOperator() {
    InfixOperator infixAnnotation = getClass().getAnnotation(InfixOperator.class);
    PrefixOperator prefixAnnotation = getClass().getAnnotation(PrefixOperator.class);
    PostfixOperator postfixAnnotation = getClass().getAnnotation(PostfixOperator.class);
    if (infixAnnotation != null) {
      this.type = OperatorType.INFIX_OPERATOR;
      this.precedence = infixAnnotation.precedence();
      this.leftAssociative = infixAnnotation.leftAssociative();
    } else if (prefixAnnotation != null) {
      this.type = PREFIX_OPERATOR;
      this.precedence = prefixAnnotation.precedence();
      this.leftAssociative = prefixAnnotation.leftAssociative();
    } else if (postfixAnnotation != null) {
      this.type = OperatorType.POSTFIX_OPERATOR;
      this.precedence = postfixAnnotation.precedence();
      this.leftAssociative = postfixAnnotation.leftAssociative();
    } else {
      throw new OperatorAnnotationNotFoundException(this.getClass().getName());
    }
  }

  @Override
  public int getPrecedence(ExpressionConfiguration configuration) {
    return getPrecedence();
  }

  @Override
  public boolean isLeftAssociative() {
    return leftAssociative;
  }

  @Override
  public boolean isPrefix() {
    return type == PREFIX_OPERATOR;
  }

  @Override
  public boolean isPostfix() {
    return type == OperatorType.POSTFIX_OPERATOR;
  }

  @Override
  public boolean isInfix() {
    return type == OperatorType.INFIX_OPERATOR;
  }
}
