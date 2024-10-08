@import exercise.dto.users.UserPage
@param UserPage page

<html>
    <head>
        <title>Хекслет</title>
    </head>
    <body>
        <p>${page.getId()}</p>
        <p>${page.getFirstname()}</p>
        <p>${page.getLastname()}</p>
        <p>${page.getEmail()}</p>
    </body>
</html>