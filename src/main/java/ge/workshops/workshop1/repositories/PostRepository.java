package ge.workshops.workshop1.repositories;


import ge.workshops.workshop1.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Integer> {
}

