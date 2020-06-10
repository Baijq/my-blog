package com.biubiu.myblog.service;

import com.biubiu.myblog.config.DictInfoCache;
import com.biubiu.myblog.constant.StringResouce;
import com.biubiu.myblog.controller.request.AddBlogRequest;
import com.biubiu.myblog.dao.BlogDao;
import com.biubiu.myblog.dao.TagDao;
import com.biubiu.myblog.dao.UserDao;
import com.biubiu.myblog.entity.Blog;
import com.biubiu.myblog.entity.Tag;
import com.biubiu.myblog.entity.User;
import com.biubiu.myblog.util.DateUtil;
import com.biubiu.myblog.util.FormatUtil;
import com.biubiu.myblog.util.UUIDUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * BlogService
 *
 * @Author wbbaijq
 * @Date 2020/5/4 22:18
 */
@Service
public class BlogService {

    @Resource
    private TagDao tagDao;
    @Resource
    private BlogDao blogDao;
    @Resource
    private UserDao userDao;
    @Resource
    private DictInfoCache dictInfoCache;

    /**
     * 返回的首页博客列表内容的最大字符数
     */
    private static final Integer MAX_BODY_CHAR_COUNT = 150;

    /**
     * 保存图片,返回url
     *
     * @param file
     * @return
     * @throws IOException
     */
    public String saveImg(MultipartFile file) throws IOException {
        //获取图片格式/后缀
        String format = FormatUtil.getFileFormat(file.getOriginalFilename());
        //获取图片保存路径
        String savePath = dictInfoCache.getValue(StringResouce.IMG_LOCAL_LOCATION);
        //存储已满
        if (!FormatUtil.checkStringNull(savePath)) {
            throw new IOException("存储已满 请联系管理员");
        }
        //保存图片
        String fileName = UUIDUtil.generateUUID() + format;
        File diskFile = new File(savePath + "/" + fileName);
        file.transferTo(diskFile);
        //将硬盘路径转换为url，返回
        return dictInfoCache.getValue(StringResouce.IMG_ACCESS_PATH).replaceAll("\\*", "") + fileName;
    }

    /**
     * 保存博文
     * @param request
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveBlog(AddBlogRequest request) {
        //根据请求的tagId去查询
        List<Tag> tags = new ArrayList<>();
        if (request.getTags() != null && request.getTags().length > 0) {
            Integer[] tagIds = request.getTags();
            for (Integer tagId : tagIds) {
                Tag tag = tagDao.findTagById(tagId);
                if (tag == null) {
                    throw new RuntimeException();
                }
                tags.add(tag);
            }
        }

        //浏览量 评论数 正常状态
        Blog blog = new Blog().setBlogViews(0).setDiscussCount(0).setState(1)
            .setTitle(request.getTitle())
            .setBody(request.getContext())
            //列表页图片 没传的话用默认的
            .setImgUrl(StringUtils.isEmpty(request.getPicUrl()) ? "https://picsum.photos/id/501/300/200" : request.getPicUrl())
            .setTime(DateUtil.getCurrentDate())
            .setUser(userDao.getDefaultUser())
            .setTags(tags);
        blogDao.saveBlog(blog);

        if (!CollectionUtils.isEmpty(tags)) {
            for (Tag tag : tags) {
                //保存该博文的标签
                blogDao.saveBlogTag(blog.getId(), tag.getId());
            }
        }
    }

    /**
     * 根据id查询博文以及博文标签
     * 正常状态
     *
     * @param blogId
     * @return
     */
    public Blog findBlogById(Integer blogId, boolean isHistory) {
        Blog blog = blogDao.findBlogById(blogId);
        if (blog == null) {
            throw new RuntimeException("博客不存在");
        }
        blog.setTags(tagDao.findTagByBlogId(blogId));
        //历史查看过
        if (isHistory) {
            // 直接返回 浏览量不增加
            return blog;
        } else {
            // 浏览量 + 1
            blog.setBlogViews(blog.getBlogViews() + 1);
            blogDao.updateBlog(blog);
        }

        return blog;
    }


    /**
     * 查询该用户的博客
     * 正常状态
     *
     * @return
     */
    public List<Blog> findBlogByUser(Integer page, Integer showCount) {
        User user = new User();
        List<Blog> blogs = blogDao.findBlogByUserId(user.getId(), (page - 1) * showCount, showCount);

        for (Blog blog : blogs) {
            blog.setTags(tagDao.findTagByBlogId(blog.getId()));
        }
        return blogs;
    }

    /**
     * 查询主页博客
     * 正常状态
     *
     * @param page      页码
     * @param showCount 显示条数
     * @return
     */
    public List<Blog> findHomeBlog(Integer page, Integer showCount) {
        // mysql 分页中的开始位置
        int start = (page - 1) * showCount;

        // 返回的blog列表
        List<Blog> blogs = new LinkedList<>();
        // 开始位置大于缓存数量 即查询范围不在缓存内 查询mysql 且不设置缓存
        blogs.addAll(blogDao.findHomeBlog(start, showCount));
        for (Blog blog : blogs) {
            blog.setTags(tagDao.findTagByBlogId(blog.getId()));
        }

        // 截取前150个字符 减少网络io
        for (Blog blog : blogs) {
            String body = blog.getBody();
            if (body.length() > BlogService.MAX_BODY_CHAR_COUNT) {
                blog.setBody(body.substring(0, BlogService.MAX_BODY_CHAR_COUNT));
            }
        }

        return blogs;
    }

    /**
     * 查询热门博文
     * 正常状态
     *
     * @return
     */
    public List<Blog> findHotBlog() {
        return blogDao.findHotBlog(6);
    }

    /**
     * 搜索博文
     * 正常状态
     *
     * @param searchText
     * @return
     */
    public List<Blog> searchBlog(String searchText, Integer page, Integer showCount) {
        List<Blog> blogs = blogDao.searchBlog(searchText, (page - 1) * showCount, showCount);
        for (Blog blog : blogs) {
            blog.setTags(tagDao.findTagByBlogId(blog.getId()));
        }
        return blogs;
    }


    /**
     * 查询所有博文 分页
     * 正常状态
     *
     * @return
     */
    public List<Blog> findAllBlog(Integer page, Integer showCount) {
        return blogDao.findAllblog((page - 1) * showCount, showCount);
    }

    /**
     * 查询所有博文
     * 正常状态
     *
     * @return
     */
    public List<Blog> findAllBlog() {
        return blogDao.findAllblogs();
    }

    /**
     * 修改博文
     *
     * @param blogId
     * @param blogTitle
     * @param blogBody
     * @param tagIds
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateBlog(Integer blogId, String blogTitle, String blogBody, Integer[] tagIds) throws JsonProcessingException {
        User user = new User();
        Blog blog = blogDao.findBlogById(blogId);
        if (!user.getId().equals(blog.getUser().getId())) {
            throw new RuntimeException("无权限修改");
        }

        blog.setTitle(blogTitle);
        blog.setBody(blogBody);
        blogDao.updateBlog(blog);
        //删除原有标签
        tagDao.deleteTagByBlogId(blog.getId());
        //保存新标签
        for (Integer tagId : tagIds) {
            //保存该博文的标签
            blogDao.saveBlogTag(blog.getId(), tagId);
        }

    }


    /**
     * 用户删除博文
     *
     * @param blogId
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteBlog(Integer blogId) throws JsonProcessingException {
        User user = new User();
        Blog blog = blogDao.findBlogById(blogId);

        if (!user.getId().equals(blog.getUser().getId())) {
            throw new RuntimeException("无权限删除");
        }


        //更改博客状态
        blog.setState(0);
        blogDao.updateBlog(blog);

        //级联删除blog_tag
        tagDao.deleteTagByBlogId(blogId);

    }

    /**
     * 管理员删除博文
     *
     * @param blogId
     */
    @Transactional(rollbackFor = Exception.class)
    public void adminDeleteBlog(Integer blogId) throws JsonProcessingException {

        Blog blog = new Blog();
        blog.setId(blogId);
        blog.setState(0);
        //更改博客状态
        blogDao.updateBlog(blog);
        //级联删除blog_tag
        tagDao.deleteTagByBlogId(blogId);

    }

    /**
     * 搜索博文
     * 所有状态
     *
     * @param searchText 搜索内容
     * @param page
     * @param showCount
     * @return
     */
    public List<Blog> searchAllBlog(String searchText, Integer page, Integer showCount) {
        List<Blog> blogs = blogDao.searchAllBlog(searchText, (page - 1) * showCount, showCount);
        return blogs;
    }

    /**
     * 按月份归档博客
     * 正常状态
     *
     * @return
     */
    public List<Map> statisticalBlogByMonth() throws IOException {
        return blogDao.statisticalBlogByMonth(6);
    }

    /**
     * 查询博客记录数
     * 所有状态
     *
     * @return
     */
    public Long getAllBlogCount() {
        return blogDao.getAllBlogCount();
    }
}
