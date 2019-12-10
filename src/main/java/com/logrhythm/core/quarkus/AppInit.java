package com.logrhythm.core.quarkus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.quarkus.jackson.ObjectMapperCustomizer;
import io.quarkus.runtime.StartupEvent;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class AppInit {

  private static final Logger LOG = LoggerFactory.getLogger(AppInit.class);

  @Singleton
  @Produces
  public ObjectMapperCustomizer jacksonCustomizer() {

    return new ObjectMapperCustomizer() {
      public void customize(ObjectMapper mapper) {
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      }
    };
  }

  void onStart(@Observes StartupEvent ev) {
    LOG.info("onStart");
  }

}
