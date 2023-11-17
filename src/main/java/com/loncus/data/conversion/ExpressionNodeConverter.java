package com.loncus.data.conversion;

import com.loncus.config.ExpressionConfiguration;
import com.loncus.data.EvaluationValue;
import com.loncus.parser.ASTNode;

/** Converter to convert to the EXPRESSION_NODE data type. */
public class ExpressionNodeConverter implements ConverterIfc {
  @Override
  public EvaluationValue convert(Object object, ExpressionConfiguration configuration) {
    return EvaluationValue.expressionNodeValue((ASTNode) object);
  }

  @Override
  public boolean canConvert(Object object) {
    return object instanceof ASTNode;
  }
}
