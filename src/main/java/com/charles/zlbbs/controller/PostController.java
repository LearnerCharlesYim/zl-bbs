package com.charles.zlbbs.controller;

import com.charles.zlbbs.domain.dto.R;
import com.charles.zlbbs.domain.entity.Board;
import com.charles.zlbbs.domain.vo.PostParam;
import com.charles.zlbbs.service.BoardService;
import com.charles.zlbbs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private PostService postService;

    @GetMapping("/publish")
    public String publishUI(Model model){
        List<Board> boards = boardService.findAll();
        model.addAttribute("boards",boards);
        return "front/publish_post";
    }

    @ResponseBody
    @PostMapping("/publish")
    public R publish(@Validated PostParam postParam){
        postService.save(postParam);
        return R.ok();
    }
}
