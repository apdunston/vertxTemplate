import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class MainController extends BaseController {
    public MainController(Vertx vertx) {
        super(vertx);
    }

    public Router router() {
        router.route("/").handler(MainController::hello);
        return router;
    }

    private static void hello(RoutingContext routingContext) {
        sendMessage(routingContext, "Hello, there");
    }
}