package com.charles.zlbbs.test;

import com.charles.zlbbs.domain.entity.Board;
import com.charles.zlbbs.domain.entity.Post;
import com.charles.zlbbs.domain.entity.User;
import com.charles.zlbbs.repositories.BoardRepository;
import com.charles.zlbbs.repositories.PostRepository;
import com.charles.zlbbs.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostTest {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BoardRepository boardRepository;


    @Test
    public void TestAdd(){

        User user = userRepository.findAll().get(0);
        Board board = boardRepository.findById(1).get();

        Post post = new Post();
        post.setTitle("如何零基础自学Python？");
        post.setContent("如何零基础自学Python？");
        post.setAuthor(user);
        post.setBoard(board);
        postRepository.save(post);
    }

}
