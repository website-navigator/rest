package gq.optimalorange.websitenavigator.rest.prototype;

import gq.optimalorange.account.sample.inject.DaggerServiceComponent;
import gq.optimalorange.account.sample.inject.ServiceComponent;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class Main implements Runnable {

  private static final Main INSTANCE = new Main();

  public final ServiceComponent serviceComponent = DaggerServiceComponent.create();

  public final Vertx vertx = Vertx.vertx();

  public static Main getInstance() {
    return INSTANCE;
  }

  public static void main(String[] args) {
    getInstance().run();
  }

  @Override
  public void run() {
    HttpServer server = vertx.createHttpServer();

    Router router = Router.router(vertx);

    router.mountSubRouter("/accounts", new AccountRest().newRouter());

    server.requestHandler(router::accept).listen(80);
  }

}
