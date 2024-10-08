@import exercise.dto.users.UsersPage
@import jte.layout.page
@param UsersPage page

<html>
    <head>
        <title>Хекслет</title>
    </head>
    <body>
        @if(page.getUsers().isEmpty())
            <p>Пока не добавлено ни одного пользователя</p>
        @else
            @for(var user : page.getUsers())
               <div>
                   <h2><a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a></h2>
                   <p>${user.getEmail()}</p>
               </div>
            @endfor
        @endif
        ${footer}
    </body>
</html>