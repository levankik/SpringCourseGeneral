package ge.workshops.workshop1.services;


import ge.workshops.workshop1.entities.Post;
import ge.workshops.workshop1.entities.PostSearchParams;

import java.util.List;

public interface PostService {
    List<Post> getAll (PostSearchParams searchParams);
    Post add (Post post);
    Post getPost (int id);
    Post update (int id, Post post);
    void delete(int id);
}