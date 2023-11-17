package com.loncus.functions;

import lombok.Builder;
import lombok.Value;

/** Definition of a function parameter. */
@Value
@Builder
public class FunctionParameterDefinition {

  /** Name of the parameter, useful for error messages etc. */
  String name;

  /**
   * Whether this parameter is a variable argument parameter (can be repeated).
   *
   * @see com.loncus.functions.basic.MinFunction for an example.
   */
  boolean isVarArg;

  /**
   * Set to true, the parameter will not be evaluated in advance, but the corresponding {@link
   * com.loncus.parser.ASTNode} will be passed as a parameter value.
   *
   * @see com.loncus.functions.basic.IfFunction for an example.
   */
  boolean isLazy;

  /** If the parameter does not allow zero values. */
  boolean nonZero;

  /** If the parameter does not allow negative values. */
  boolean nonNegative;
}
