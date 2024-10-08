@import exercise.dto.users.UsersPage
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
                   <h2><a href="/users/${user.getId()}">${user.getFirstName()}</a></h2>
                    <p>${user.getLastname()}</p>
                   <p>${user.getEmail()}</p>
               </div>
            @endfor
        @endif
    </body>
</html>