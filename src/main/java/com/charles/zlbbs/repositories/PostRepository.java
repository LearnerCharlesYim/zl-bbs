package com.charles.zlbbs.repositories;

import com.charles.zlbbs.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,String> {
}
