package com.charles.zlbbs.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class PostImgVO {
    private Integer errno;
    private List<DataItem> data;

    @Data
    public static class DataItem {
        private String url;
        private String alt;
        private String href;
    }
}
