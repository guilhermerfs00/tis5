package com.puc.ticketin.domain.helper;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.caelum.stella.validation.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CpfCnpjValidator implements ConstraintValidator<CpfCnpj, String> {
    private boolean isFormatted;

    @Override
    public void initialize(final CpfCnpj cpfCnpj) {
        this.isFormatted = cpfCnpj.isFormatted();
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.isEmpty()) {
            return Boolean.TRUE;
        }
        return validateCpf(value) || validateCnpj(value);
    }

    private Boolean validateCpf(final String value) {
        CPFValidator cpfValidator = new CPFValidator(isFormatted);

        if (cpfValidator.isEligible(value)) {
            return validate(value, cpfValidator);
        }
        return Boolean.FALSE;
    }

    private boolean validateCnpj(final String value) {
        return validate(value, new CNPJValidator(isFormatted));
    }

    private boolean validate(String value, Validator<String> validator) {
        try {
            validator.assertValid(value);
            return Boolean.TRUE;
        } catch (InvalidStateException e) {
            return Boolean.FALSE;
        }
    }
}
