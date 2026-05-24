package br.com.ludopet.repository;

import br.com.ludopet.model.ForumPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumPostRepository
        extends JpaRepository<ForumPost, Long> {
}