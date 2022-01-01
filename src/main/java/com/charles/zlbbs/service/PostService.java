package com.charles.zlbbs.service;

import cn.dev33.satoken.stp.StpUtil;
import com.charles.zlbbs.domain.entity.Board;
import com.charles.zlbbs.domain.entity.Post;
import com.charles.zlbbs.domain.entity.User;
import com.charles.zlbbs.domain.enu.ResultCode;
import com.charles.zlbbs.domain.vo.PostParam;
import com.charles.zlbbs.exception.BizException;
import com.charles.zlbbs.repositories.BoardRepository;
import com.charles.zlbbs.repositories.PostRepository;
import com.charles.zlbbs.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Post> findAll() {
        return postRepository.findAll(Sort.by(Sort.Direction.DESC,"createdTime"));
    }

    public void save(PostParam postParam) {
        Integer boardId = postParam.getBoardId();
        Optional<Board> optional = boardRepository.findById(boardId);
        if(!optional.isPresent()){
            throw new BizException(ResultCode.BOARD_NOT_FOUND);
        }
        Board board = optional.get();
        String loginId = (String) StpUtil.getLoginId();
        User author = userRepository.findById(loginId).get();

        Post post = new Post();
        BeanUtils.copyProperties(postParam,post);
        post.setAuthor(author);
        post.setBoard(board);
        postRepository.save(post);
    }
}
