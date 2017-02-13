import java.util.function.BiFunction;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

abstract class BaseController {
    private static final String JSON_HEADER = "application/json";
    private static final String HTML_HEADER = "text/html";

    Vertx vertx;
    Router router;

    public BaseController(Vertx vertx) {
        router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
    }

    public abstract Router router();

    static void sendMessage(RoutingContext routingContext, String message) {
        routingContext.response()
                .putHeader("content-type", HTML_HEADER)
                .end(getHTML(message));
    }

    static void sendJson(RoutingContext routingContext, String message) {
        routingContext.response()
                .putHeader("content-type", JSON_HEADER)
                .end(message);
    }

    static String getHTML(String message) {
        return "<html><body style=\"background-color: #50E1CE\"><div style=\"position: relative\"><div style=\"position: absolute; top: 50%; left: 50%; transform: translate(-50%, 50%)\"><h1>Adrian Dunston is the most rad dude ever.</h1><h2>" + message + "</h2></div></div></body></html>";
    }

    String success() {
        return "{\"success\":true}";
    }

}