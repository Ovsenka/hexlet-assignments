@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostPage
@param PostPage page

<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>App</title>
</head>
@template.layout.page(
content = @`
    <h1>Post:</h1>
    <table class="table table-striped">
            <tr>
                <td>
                    ${page.getPost().getName()}
                </td>
                <td>
                    <a>${page.getPost().getBody()}</a>
                </td>
            </tr>
    </table>
    <div>
        <a href="${NamedRoutes.postsPath()}">All the posts</a>
    </div>
`
)
</html>