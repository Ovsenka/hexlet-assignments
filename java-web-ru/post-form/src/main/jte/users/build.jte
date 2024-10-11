@template.layout.page(
    content = @`
        <h1>Добавить пользователя</h1>
        <form method="POST" action="/users">
            <input type="text" name="firstName" placeholder="Имя" required/>
            <input type="text" name="lastName" placeholder="Фамилия" required/>
            <input type="text" name="email" placeholder="E-mail" required/>
            <input type="text" name="password" placeholder="Пароль" required/>
        </form>
    `
)