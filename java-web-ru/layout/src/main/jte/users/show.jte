@import jte.layout.page

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
        ${footer}}
    </body>
</html>