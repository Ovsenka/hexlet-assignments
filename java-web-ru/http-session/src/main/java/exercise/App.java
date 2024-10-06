package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> config.bundledPlugins.enableDevLogging());

        // BEGIN
        app.get("/users", ctx -> {
            var page = ctx
                    .queryParamAsClass("page", Integer.class)
                    .getOrDefault(0);
            var perPage = ctx
                    .queryParamAsClass("per", Integer.class)
                    .getOrDefault(5);
            var start = (page - 1) * perPage;
            var end = page * perPage;
            ctx.json(USERS.subList(start, end));
        });
        // END

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
