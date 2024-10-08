@import org.example.hexlet.dto.users.UserPage
@param UserPage page

<html>
    <head>
        <title>Хекслет</title>
    </head>
    <body>
        <p>${user.getId()}</p>
        <p>${user.getFirstName()}</p>
        <p>${user.getLastName()}</p>
        <p>${user.getEmail()}</p>
    </body>
</html>