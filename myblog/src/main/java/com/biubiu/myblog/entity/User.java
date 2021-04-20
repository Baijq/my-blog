package com.biubiu.myblog.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 */
@Data
@ApiModel("用户")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class User implements Serializable {

    /**
     * user(36) => 1436499(10)
     */
    private static final long serialVersionUID = 1436499L;


    @ApiModelProperty(value = "用户id", dataType = "Integer")
    private Integer id;

    @ApiModelProperty(value = "用户名", dataType = "String")
    private String username;

    @ApiModelProperty(value = "昵称", dataType = "String")
    private String nickname;

    @ApiModelProperty(value = "姓名", dataType = "String")
    private String realName;

    @ApiModelProperty(value = "密码", dataType = "String")
    private String password;

    @ApiModelProperty(value = "头像", dataType = "String")
    private String avatar;

    @ApiModelProperty(value = "邮箱", dataType = "String")
    private String mail;

    @ApiModelProperty(value = "电话", dataType = "String")
    private String phone;

    @ApiModelProperty(value = "用户状态", dataType = "Integer")
    private Integer state;

    @ApiModelProperty(value = "打赏码路径", dataType = "String")
    private String reward;

    @ApiModelProperty(value = "创建时间", dataType = "Date")
    private Date createTime;

    @ApiModelProperty(value = "更新时间", dataType = "Date")
    private Date updateTime;
}