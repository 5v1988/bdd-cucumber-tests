package enabler;

import dev.failsafe.Failsafe;
import dev.failsafe.RetryPolicy;
import dev.failsafe.RetryPolicyBuilder;
import dev.failsafe.function.CheckedRunnable;
import dev.failsafe.function.CheckedSupplier;
import enabler.util.Formatters;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.ElementClickInterceptedException;

public interface RetryUtilityStep extends Formatters {

  Logger log = Logger.getLogger(RetryUtilityStep.class);
  int MAX_RETRIES_DEFAULT = 2;
  int MAX_DELAY_SECONDS_DEFAULT = 5;
  RetryPolicyBuilder<Object> policyBuilder = RetryPolicy.builder();
  List<Class<? extends Throwable>> exceptionsList = Arrays.asList(
      NoSuchElementException.class,
      ElementClickInterceptedException.class,
      StaleElementReferenceException.class,
      AssertionError.class
  );

  default void retryStep(CheckedRunnable runnable, int maxRetry, int delaySeconds) {
    RetryPolicy<Object> policy = policyBuilder.withMaxRetries(maxRetry)
        .withDelay(Duration.ofSeconds(delaySeconds))
        .handle(exceptionsList)
        .onRetry(e -> log.warn(sf("attempting retry#: %d", e.getAttemptCount())))
        .onFailure(
            e -> log.error(sf("attempts: %d have failed", e.getExecutionCount())))
        .build();
    Failsafe.with(policy).run(runnable);
  }

  default Object getWithRetryStep(CheckedSupplier<Object> supplier, int maxRetry,
      int delaySeconds) {
    RetryPolicy<Object> policy = policyBuilder.withMaxRetries(maxRetry)
        .withDelay(Duration.ofSeconds(delaySeconds))
        .handle(exceptionsList)
        .handleResult(null)
        .onRetry(e -> log.warn(sf("attempting retry#: %d", e.getAttemptCount())))
        .onFailure(
            e -> log.error(sf("attempts: %d have failed", e.getExecutionCount())))
        .build();
    return Failsafe.with(policy).get(supplier);
  }

}
