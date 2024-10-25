package exercise;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> index(@RequestParam(defaultValue = "10") Integer limit) {
        List<Post> postList = posts.stream().limit((long) limit).toList();
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(posts.size()))
                .body(postList);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> show(@PathVariable String id) {
        var post = posts.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
        return post.map(value -> ResponseEntity
                .ok()
                .body(value)).orElseGet(() -> ResponseEntity.status(404).build());
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> create(@RequestBody Post post) {
        posts.add(post);
        return ResponseEntity.status(201).body(post);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post newData) {
        var post = posts.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
        if (post.isPresent()) {
            Post currentPost = post.get();
            currentPost.setBody(newData.getBody());
            currentPost.setId(newData.getId());
            currentPost.setTitle(newData.getTitle());
            return ResponseEntity.ok()
                    .body(currentPost);
        } else {
            return ResponseEntity.status(204).build();
        }
    }
    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
