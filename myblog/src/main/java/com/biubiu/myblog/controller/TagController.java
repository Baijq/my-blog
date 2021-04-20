package com.biubiu.myblog.controller;

import com.biubiu.myblog.entity.StatusCode;
import com.biubiu.myblog.service.TagService;
import com.biubiu.myblog.util.FormatUtil;
import com.biubiu.myblog.util.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Api(tags = "标签api")
@RestController
@RequestMapping("/api/tags")
@CrossOrigin
public class TagController {

    @Resource
    private TagService tagService;

    /**
     * 新增一个标签
     *
     * @param tagName 标签名
     * @return
     */
    @ApiOperation(value = "新增标签")
    @PostMapping
    public RestResponse newTag(String tagName) {
        if (!FormatUtil.checkStringNull(tagName)) {
            return RestResponse.failure("参数异常").setCode(StatusCode.ERROR);
        }
        tagService.saveTag(tagName);
        return RestResponse.success("新增成功").setCode(StatusCode.OK);
    }

    /**
     * 删除一个标签
     *
     * @param tagId 标签id
     * @return
     */
    @ApiOperation(value = "删除标签")
    @DeleteMapping("/{tagId}")
    public RestResponse deleteTag(@PathVariable Integer tagId) {
        if (!FormatUtil.checkObjectNull(tagId)) {
            return RestResponse.failure("参数异常").setCode(StatusCode.ERROR);
        }
        tagService.deleteTagById(tagId);
        return RestResponse.success("删除成功").setCode(StatusCode.OK);
    }


    /**
     * 修改一个标签
     *
     * @param tagId   标签id
     * @param tagName 新标签名
     * @return
     */
    @ApiOperation(value = "修改标签")
    @PutMapping
    public RestResponse updateTag(Integer tagId, String tagName) {
        if (!FormatUtil.checkObjectNull(tagId)) {
            return RestResponse.failure("参数异常").setCode(StatusCode.ERROR);
        }
        if (!FormatUtil.checkStringNull(tagName)) {
            return RestResponse.failure("参数异常").setCode(StatusCode.ERROR);
        }
        tagService.updateTag(tagId, tagName);
        return RestResponse.success();
    }


    /**
     * 获取所有标签
     * @return
     */
    @ApiOperation(value = "获取所有标签")
    @GetMapping
    public RestResponse findTagByUserId() {
        return RestResponse.success("查询成功").setCode(StatusCode.OK)
                .setData(tagService.findAllTag());
    }


}
