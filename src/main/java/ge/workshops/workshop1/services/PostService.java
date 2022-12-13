package ge.workshops.workshop1.services;

import ge.workshops.workshop1.entity.Post;

import java.util.List;


public interface PostService {
    List<Post> getPosts();

    Post getPost(int id);


    List<Post> getPostsById(int userId);
}
