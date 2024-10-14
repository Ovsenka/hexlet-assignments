@import exercise.util.NamedRoutes
@import exercise.dto.LoginPage
@param LoginPage page

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hello Hexlet!</title>
</head>
<body>
<main>
    <form action="${NamedRoutes.loginPath()}" method="post">
        <input type="text" placeholder="Nickname" name="name" />
        <input type="password" placeholder="Password" name="password" />
        <input type="submit" />
    </form>

    @if(page.getError() != null)
        <div>
            ${page.getError()}
        </div>
    @endif
</main>
</body>
</html>