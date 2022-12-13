package ge.workshops.workshop1.services;

import ge.workshops.workshop1.repository.PostRepository;
import ge.workshops.workshop1.entity.Post;
import ge.workshops.workshop1.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPost(int id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post not found"));
    }

    @Override
    public List<Post> getPostsById(int userId) {
        return postRepository.findByUserId(userId);
    }
}
