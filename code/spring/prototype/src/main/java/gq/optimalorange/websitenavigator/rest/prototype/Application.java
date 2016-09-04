package gq.optimalorange.websitenavigator.rest.prototype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import gq.optimalorange.account.sample.inject.DaggerServiceComponent;
import gq.optimalorange.account.sample.inject.ServiceComponent;

@SpringBootApplication
public class Application {

  private static final Application INSTANCE = new Application();

  public final ServiceComponent serviceComponent = DaggerServiceComponent.create();

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  public static Application getInstance() {
    return INSTANCE;
  }

}
