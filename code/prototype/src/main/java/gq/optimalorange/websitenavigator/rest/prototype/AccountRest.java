package gq.optimalorange.websitenavigator.rest.prototype;

import javax.annotation.Nullable;

import gq.optimalorange.account.Identifier;
import gq.optimalorange.account.SubjectService;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class AccountRest implements Rest {

  private final SubjectService subjectService =
      Main.getInstance().serviceComponent.getSubjectService();

  @Override
  public Router newRouter() {
    final Router router = Router.router(Main.getInstance().vertx);
    router.get("/exist").handler(this::handleGetExist);
    return router;
  }

  private void handleGetExist(RoutingContext routingContext) {
    final Identifier identifier = getIdentifier(routingContext.request());
    final HttpServerResponse response = routingContext.response();
    if (identifier == null) {
      sendError(404, response);
      return;
    }
    subjectService.exist(identifier).subscribe(result -> {
      response
          .putHeader("content-type", "application/json")
          .end(new JsonObject()
                   .put("succeeded", result.succeeded())
                   .put("result", result.result())
                   .put("cause", result.cause())
                   .encodePrettily());
    }, error -> sendError(503, response));
  }

  @Nullable
  private Identifier getIdentifier(HttpServerRequest request) {
    final String id = request.getParam("id");
    final String username = request.getParam("username");
    if (id != null) {
      return Identifier.id(id);
    } else if (username != null) {
      return Identifier.username(username);
    } else {
      return null;
    }
  }

  private void sendError(int statusCode, HttpServerResponse response) {
    response.setStatusCode(statusCode).end();
  }


}
