package gq.optimalorange.websitenavigator.rest.prototype;

import javax.annotation.Nonnull;

import gq.optimalorange.account.Result;

public class ResultWrapper<T, E> {

  private final Result<T, E> result;

  private ResultWrapper(Result<T, E> result) {
    this.result = result;
  }

  @Nonnull
  public static <T, E> ResultWrapper<T, E> of(Result<T, E> result) {
    return new ResultWrapper<>(result);
  }

  public boolean getSucceeded() {
    return result.succeeded();
  }

  public T getResult() {
    return result.result();
  }

  public E getCause() {
    return result.cause();
  }

}
