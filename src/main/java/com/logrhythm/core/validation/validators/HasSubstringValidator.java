package com.logrhythm.core.validation.validators;

import com.logrhythm.core.validation.constraints.HasSubstring;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HasSubstringValidator implements ConstraintValidator<HasSubstring, String> {

  private String substring;

  @Override
  public void initialize(HasSubstring constraintAnnotation) {
    substring = constraintAnnotation.value().toLowerCase();
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value == null || value.isEmpty() || value.toLowerCase().contains(substring);
  }
}
