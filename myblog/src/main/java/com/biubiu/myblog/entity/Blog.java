package com.biubiu.myblog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 博文
 */
@Data
@Accessors(chain = true)
@ToString(exclude = "body")
public class Blog implements Serializable {

    private static final long serialVersionUID = 541312L;

    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String body;

    /**
     * 列表页图片
     */
    private String imgUrl;
    /**
     * 评论数
     */
    private Integer discussCount;

    /**
     * 浏览数
     */
    private Integer blogViews;

    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date time;
    /**
     * 博文状态--0删除 1正常
     */
    private Integer state;

    /**
     * 所属用户
     */
    private User user;
    /**
     * 博文对应的标签
     */
    private List<Tag> tags;

}
