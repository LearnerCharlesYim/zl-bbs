package com.charles.zlbbs.controller;

import cn.hutool.core.util.IdUtil;
import com.charles.zlbbs.domain.anno.ImageRequired;
import com.charles.zlbbs.domain.dto.R;
import com.charles.zlbbs.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@Validated
public class UploadFileController {

    @Value("${qiniu.url}")
    private String url;

    @PostMapping("/avatar/upload")
    public R uploadAvatar(@ImageRequired MultipartFile image) throws IOException {
        String oldName = image.getOriginalFilename();
        String newName = IdUtil.simpleUUID();
        newName += oldName.substring(oldName.lastIndexOf("."));
        QiniuUtils.uploadToQiniu(image.getBytes(), newName);
        Map<String, String> map = new HashMap<>();
        map.put("avatar", url + newName);
        return R.ok(map);
    }

}
