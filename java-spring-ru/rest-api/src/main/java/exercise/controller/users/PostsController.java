package exercise.controller.users;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api/users/{id}/posts")
class PostsController {
    private List<Post> posts = Data.getPosts();
    @GetMapping
    public List<Post> getPostsByUserId(
            @PathVariable int id
    ) {
        return posts
                .stream()
                .filter(post -> post.getUserId() == id)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(
            @PathVariable int id,
            @RequestBody Post post
    ) {
        post.setUserId(id);
        posts.add(post);
        return post;
    }
}
// END
