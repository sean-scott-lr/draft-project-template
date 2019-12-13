package com.logrhythm.core.jaxrs.exception.mappers;

import com.logrhythm.core.model.v1.Response;
import com.logrhythm.core.model.v1.Response.ValidationFailure;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.MessageInterpolator;
import javax.validation.MessageInterpolator.Context;
import javax.validation.Path;
import javax.validation.ValidatorFactory;
import javax.validation.metadata.ConstraintDescriptor;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@ApplicationScoped
// TODO: Its possible that this entire class could go away. ( or be greatly reduced )
// https://github.com/quarkusio/quarkus/issues/5908

public class ConstraintViolationExceptionMapper extends BaseExceptionMapper
    implements ExceptionMapper<ConstraintViolationException> {

  private MessageInterpolator interpolator;

  @Inject
  public ConstraintViolationExceptionMapper(ValidatorFactory validatorFactory) {
    this.interpolator = validatorFactory.getMessageInterpolator();
  }

  public javax.ws.rs.core.Response toResponse(ConstraintViolationException e) {

    Locale l = getRequestedLocale();
    return javax.ws.rs.core.Response.status(Status.BAD_REQUEST)
        .type(MediaType.APPLICATION_JSON)
        .entity(
            Response.error(
                Status.BAD_REQUEST.getStatusCode(),
                errorMessage(e, l),
                map(e.getConstraintViolations(), l)))
        .build();
  }

  protected String errorMessage(ConstraintViolationException e, Locale l) {
    // TODO : Implement this
    return "Validation failed.";
  }

  protected List<ValidationFailure> map(Set<ConstraintViolation<?>> violations, Locale l) {
    return violations.stream().map(v -> map(v, l)).collect(Collectors.toList());
  }

  protected ValidationFailure map(ConstraintViolation v, Locale locale) {
    return new ValidationFailure(
        fieldName(v.getPropertyPath()),
        interpolator.interpolate(v.getMessageTemplate(), createContext(v), locale));
  }

  protected String fieldName(Path path) {
    // TODO: Create a path without the METHOD... and use the classname vs the variable name for a
    // type
    return path.toString();
  }

  protected Context createContext(ConstraintViolation violation) {
    return new ContextImpl(violation);
  }

  private static final class ContextImpl implements Context, Serializable {

    private final ConstraintViolation violation;

    private ContextImpl(ConstraintViolation violation) {
      this.violation = violation;
    }

    @Override
    public ConstraintDescriptor getConstraintDescriptor() {
      return violation.getConstraintDescriptor();
    }

    @Override
    public Object getValidatedValue() {
      return violation.getInvalidValue();
    }

    @Override
    public <T> T unwrap(Class<T> type) {
      if (type.isAssignableFrom(getClass())) {
        return type.cast(this);
      } else {
        throw new ClassCastException(
            type.getName() + " is not assignable from " + getClass().getName());
      }
    }
  }
}
