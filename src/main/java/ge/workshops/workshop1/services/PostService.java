package ge.workshops.workshop1.services;

import ge.workshops.workshop1.dto.PostSearchParams;
import ge.workshops.workshop1.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {
    Page<Post> getPosts(PostSearchParams params, Pageable pageable);

    Post  getPost(int id);

    List<Post> getPostsById(int userId);

    Post addPost(Post post);

    void startProcessing();

    void fill();
}
