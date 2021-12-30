package com.charles.zlbbs.repositories;

import com.charles.zlbbs.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,String> {
    User findByEmail(String email);
}
