@import exercise.dto.users.UsersPage;

@param UsersPage page

@template.layout.page(
    content = @`
    <form action="/users" method="get">
        <input type="search" required name="term" value="${page.getTerm()}" />
        <input type="submit" value="Search" />
    </form>
    @if (page.getUsers() != null && !page.getUsers().isEmpty())
        @for(var user : page.getUsers())
            <div>
                <p>${user.getFirstName()} ${user.getLastName()}</p>
            </div>
        @endfor
    @endif
    `
)