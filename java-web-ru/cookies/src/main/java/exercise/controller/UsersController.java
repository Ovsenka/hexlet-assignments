package exercise.controller;

import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void register(Context context) {
        String firstName = context.formParamAsClass("firstName", String.class).get();
        String lastName = context.formParamAsClass("lastName", String.class).get();
        String email = context.formParamAsClass("email", String.class).get();
        String password = context.formParamAsClass("password", String.class).get();
        String token = Security.generateToken();
        context.cookie("user", token);
        User user = new User(firstName, lastName, email, password, token);
        UserRepository.save(user);
        Long id = UserRepository.search(firstName).stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundResponse("There is no User with this name"))
                .getId();
        context.redirect(NamedRoutes.userPath(id));
    }

    public static void show(Context context) {
        Long id = context.pathParamAsClass("id", Long.class).get();
        var user = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("There is no User with this id"));
        String cookie = context.cookie("user");
        UserPage page = new UserPage(user);
        if (user == null || !user.getToken().equals(cookie)) {
            context.redirect(NamedRoutes.buildUserPath());

        } else {
            context.render("users/show.jte", model("page", page));
        }

    }
    // END
}
