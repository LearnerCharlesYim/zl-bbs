package com.charles.zlbbs.service;

import com.charles.zlbbs.domain.entity.Board;
import com.charles.zlbbs.repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> findAll() {
        return boardRepository.findAll();
    }
}
