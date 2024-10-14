package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;

import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;

public class SessionsController {

    // BEGIN
    public static void build (Context context) {
        LoginPage page = new LoginPage(context.sessionAttribute("auth"), null);
        context.render("build.jte", model("page", page));
    }

    public static void index (Context context) {
        LoginPage page = new LoginPage(context.sessionAttribute("auth"), null);
        context.render("index.jte", model("page", page));
    }

    public static void create (Context context) {
        String name = context.formParam("name");
        String password = context.formParam("password");
        String encryptPassword = Security.encrypt(password);
        var user = UsersRepository.findByName(name);
        if (user.isEmpty() || !user.get().getPassword().equals(encryptPassword)) {
            LoginPage page = new LoginPage(name, "Wrong username or password");
            context.render("build.jte", model("page", page)).status(302);
        } else {
            context.sessionAttribute("auth", name);
            context.redirect(NamedRoutes.rootPath());
        }
    }

    public static void destroy (Context context) {
        context.sessionAttribute("auth", null);
        context.redirect(NamedRoutes.rootPath());
    }
    // END
}
