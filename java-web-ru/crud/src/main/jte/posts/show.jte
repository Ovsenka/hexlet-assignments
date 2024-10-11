@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostsPage
@param PostsPage page

<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
</head>
@template.layout.page(
content = @`
    <h1>Posts:</h1>
    <table class="table table-striped">
        @for(var post : page.getPosts())
            <tr>
                <td>
                    <a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a>
                </td>
            </tr>
        @endfor
    </table>
    <div>
        @if(page.getCurrPageNum() > 1)
            <a href="${NamedRoutes.postsPath()}?page=${page.getCurrPageNum() - 1}">Move Back</a>
        @endif
        <a href="${NamedRoutes.postsPath()}?page=${page.getCurrPageNum() + 1}">Move Forward</a>
    </div>
`
)
</html>