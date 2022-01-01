package com.charles.zlbbs.controller;

import cn.hutool.core.util.IdUtil;
import com.charles.zlbbs.domain.anno.ImageRequired;
import com.charles.zlbbs.domain.dto.R;
import com.charles.zlbbs.domain.vo.PostImgVO;
import com.charles.zlbbs.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@Validated
public class UploadFileController {

    @Value("${qiniu.url}")
    private String url;

    @PostMapping("/avatar/upload")
    public R uploadAvatar(@ImageRequired MultipartFile image) throws IOException {
        String newName = uploadFile(image);
        Map<String, String> map = new HashMap<>();
        map.put("avatar", url + newName);
        return R.ok(map);
    }

    /**
     * WangEditor上传图片
     * @param image
     * @return
     * @throws IOException
     */
    @PostMapping("/post/image/upload")
    public PostImgVO uploadPostImg(@ImageRequired MultipartFile image) throws IOException {
        String newName = uploadFile(image);
        PostImgVO result = new PostImgVO();
        PostImgVO.DataItem dataItem = new PostImgVO.DataItem();
        dataItem.setUrl(url + newName);
        dataItem.setAlt(image.getOriginalFilename());
        result.setErrno(0);
        result.setData(Collections.singletonList(dataItem));
        return result;
    }

    private String uploadFile(MultipartFile file) throws IOException {
        String oldName = file.getOriginalFilename();
        String newName = IdUtil.simpleUUID();
        newName += oldName.substring(oldName.lastIndexOf("."));
        QiniuUtils.uploadToQiniu(file.getBytes(), newName);
        return newName;
    }
}
