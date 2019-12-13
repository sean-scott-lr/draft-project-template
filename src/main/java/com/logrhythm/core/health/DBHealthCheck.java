package com.logrhythm.core.health;

import java.sql.Connection;
import java.sql.Statement;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Readiness
@ApplicationScoped
public class DBHealthCheck implements HealthCheck {

  private static final Logger LOG = LoggerFactory.getLogger(DBHealthCheck.class);
  private DataSource dataSource;

  @Inject
  public DBHealthCheck(DataSource datasource) {
    this.dataSource = datasource;
    LOG.info("DBHealthCheck initialized");
  }

  @Override
  public HealthCheckResponse call() {

    HealthCheckResponseBuilder responseBuilder =
        HealthCheckResponse.named("Database connection health check");
    responseBuilder.state(isDbOk());
    return responseBuilder.build();
  }

  private boolean isDbOk() {
    try (Connection conn = dataSource.getConnection()) {
      Statement s = conn.createStatement();
      s.execute(getSqlStatement());
      return true;
    } catch (Throwable t) {
      return false;
    }
  }

  protected String getSqlStatement() {
    return "select 1";
  }
}
