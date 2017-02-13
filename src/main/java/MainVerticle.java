import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;

public class MainVerticle extends AbstractVerticle {
    @Override
    public void start(Future<Void> fut) {
        Router router = Router.router(vertx);

        router.mountSubRouter("/", new MainController(vertx).router());



        // Create the HTTP server and pass the "accept" method to the request handler.
        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(
                        // Retrieve the port from the configuration,
                        // default to 8080.
                        config().getInteger("http.port", 8080),
                        result -> {
                            if (result.succeeded()) {
                                fut.complete();
                            } else {
                                fut.fail(result.cause());
                            }
                        }
                );
    }
    public void stop() {

    }
}
