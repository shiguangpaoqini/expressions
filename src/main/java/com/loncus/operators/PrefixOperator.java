package com.loncus.operators;

import static com.loncus.operators.OperatorIfc.OPERATOR_PRECEDENCE_UNARY;

import java.lang.annotation.*;

/** The prefix operator annotation */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PrefixOperator {

  /** Operator precedence, usually one from the constants in {@link OperatorIfc}. */
  int precedence() default OPERATOR_PRECEDENCE_UNARY;

  /** Operator associativity, defaults to <code>true</code>. */
  boolean leftAssociative() default true;
}
