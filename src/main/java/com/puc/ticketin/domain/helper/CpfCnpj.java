package com.puc.ticketin.domain.helper;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Bean validation for CPF and CNPJ represented in the same field. It's required the dependency:
 * <pre>{@code
 *      <groupId>br.com.caelum.stella</groupId>
 *      <artifactId>caelum-stella-core</artifactId>}
 * </pre>
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = CpfCnpjValidator.class)
public @interface CpfCnpj {
    String message() default "{custom.validation.constraints.CpfCnpj.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean isFormatted() default false;
}
