package ge.workshops.workshop1.repository;

import ge.workshops.workshop1.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository <Post, Integer> {
    List<Post> findByUserId(int userId);
}
