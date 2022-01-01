package com.charles.zlbbs.repositories;

import com.charles.zlbbs.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,String> {
}
