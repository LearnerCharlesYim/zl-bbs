package com.charles.zlbbs.controller;

import com.charles.zlbbs.domain.entity.Board;
import com.charles.zlbbs.domain.entity.Post;
import com.charles.zlbbs.service.BoardService;
import com.charles.zlbbs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    @Value("${qiniu.url}")
    private String url;

    @Autowired
    private PostService postService;

    @Autowired
    private BoardService boardService;

    @GetMapping("/")
    public String index(Model model){
        List<Post> posts = postService.findAll();
        List<Board> boards = boardService.findAll();
        model.addAttribute("posts",posts);
        model.addAttribute("boards",boards);
        model.addAttribute("url",url);
        return "front/index";
    }
}
