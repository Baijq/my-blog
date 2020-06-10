package com.biubiu.myblog.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * AddBlogRequest
 *
 * @author wbbaijq
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class AddBlogRequest extends BaseRequest {

    private static final long serialVersionUID = 5061559808186987146L;

    @ApiModelProperty(value = "博客标题", required = true, dataType = "String")
    @NotBlank(message = "博客标题不能为空")
    private String title;

    @ApiModelProperty(value = "博客内容", required = true, dataType = "String")
    @NotBlank(message = "博客内容不能为空")
    private String context;

    @ApiModelProperty(value = "博客标签", dataType = "Integer")
    private Integer[] tags;

    @ApiModelProperty(value = "列表页图片地址", dataType = "String")
    private String picUrl;

}

