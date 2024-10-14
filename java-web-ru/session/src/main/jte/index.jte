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
    <h1>Check validation</h1>
    @if(page.getName() != null)
        <form action="${NamedRoutes.logoutPath()}" method="post">
            <input type="text" placeholder="Nickname" name="name" value="${page.getName()}"/>
            <input type="submit" value="Выйти"/>
        </form>
    @else
        <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
    @endif
</main>
</body>
</html>