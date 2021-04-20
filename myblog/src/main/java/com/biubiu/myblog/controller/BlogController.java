package com.biubiu.myblog.controller;

import com.biubiu.myblog.controller.request.AddBlogRequest;
import com.biubiu.myblog.entity.StatusCode;
import com.biubiu.myblog.service.BlogService;
import com.biubiu.myblog.util.FormatUtil;
import com.biubiu.myblog.util.RequestUtil;
import com.biubiu.myblog.util.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

/**
 * BlogController
 *
 * @Author wbbaijq
 * @Date 2020/5/4 22:16
 */

@Api(tags = "博文api")
@RestController
@RequestMapping("/api/blogs")
@CrossOrigin
public class BlogController {

    @Resource
    private BlogService blogService;

    private static final String IMAGE_JPG = ".jpg";
    private static final String IMAGE_PNG = ".png";

    @ApiOperation(value = "上传图片")
    @PostMapping("/uploadImg")
    public RestResponse uploadImg(MultipartFile file, HttpServletRequest request) {
        if (!FormatUtil.checkObjectNull(file)) {
            return RestResponse.failure("参数错误").setCode(StatusCode.ERROR);
        }
        String fileFormat = FormatUtil.getFileFormat(file.getOriginalFilename());

        if (null == fileFormat) {
            return RestResponse.failure("图片缺少格式").setCode(StatusCode.ERROR);
        }
        //图片只支持 jpg/png
        if (!IMAGE_JPG.equals(fileFormat.toLowerCase()) && !IMAGE_PNG.equals(fileFormat.toLowerCase())) {
            return RestResponse.failure("图片缺少格式").setCode(StatusCode.ERROR);
        }
        String host = RequestUtil.getIpAndPort(request);

        try{
            String url = host + blogService.saveImg(file);
            return RestResponse.success("上传成功").setData(url);
        } catch (IOException ioe) {
            return RestResponse.failure("上传失败" + ioe.getMessage());
        }
    }

    /**
     * 保存博文，博文内容由前端md编辑器生成
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "发布博文")
    @PostMapping
    public RestResponse newBlog(@RequestBody @Valid AddBlogRequest request) {
        try {
            blogService.saveBlog(request);
            return RestResponse.success("发布成功").setCode(StatusCode.OK);
        } catch (Exception e) {
            return RestResponse.failure("非法操作");
        }
    }

    /**
     * 根据id查询博文
     * @param blogId 博客Id
     * @param isHistory 是否历史查看过
     * @return
     */
    @ApiOperation(value = "根据id查询博文")
    @GetMapping("/{blogId}")
    public RestResponse findBlogById(@PathVariable Integer blogId, Boolean isHistory) {
        if (!FormatUtil.checkObjectNull(blogId)) {
            return RestResponse.failure("参数错误");
        }
        return RestResponse.success("查询成功")
                .setCode(StatusCode.OK)
                .setData(blogService.findBlogById(blogId, isHistory == null ? true : false));
    }

    @ApiOperation(value = "查询全部博文")
    @GetMapping
    public RestResponse findBlogs() {
        return RestResponse.success("查询成功")
                .setCode(StatusCode.OK)
                .setData(blogService.findAllBlog());
    }

    /**
     * 首页分页查询
     * 查询的范围在 最近10条博客 内
     *
     * @param page      页码
     * @param showCount 显示条数
     * @return
     */
//    @ApiOperation(value = "首页分页查询博文", notes = "页数+显示数量")
//    @GetMapping("/list")
//    public RestResponse homeBlog() {
//        try {
//            List<Blog> list = blogService.findHomeBlog(page, showCount);
//            return RestResponse.success("查询成功")
//                    .setCode(StatusCode.OK)
//                    .setData(new PageInfo(list));
//        } catch (Exception e) {
//            return RestResponse.failure("服务异常").setCode(StatusCode.SERVICEERROR);
//        }


//    }


}
