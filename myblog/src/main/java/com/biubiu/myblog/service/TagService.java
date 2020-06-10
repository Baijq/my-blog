package com.biubiu.myblog.service;

import com.biubiu.myblog.dao.BlogDao;
import com.biubiu.myblog.dao.TagDao;
import com.biubiu.myblog.entity.Tag;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * TagService
 *
 * @Author wbbaijq
 * @Date 2020/5/5 1:52
 */
@Service
public class TagService {

    @Resource
    private TagDao tagDao;
    @Resource
    private BlogDao blogDao;

    /**
     * 新增标签
     *
     * @param tagName
     */
    public void saveTag(String tagName) {
        if (tagDao.findTagByTagName(tagName) != null) {
            throw new RuntimeException("标签重复");
        }
        Tag tag = new Tag();
        tag.setName(tagName);
        tagDao.saveTag(tag);
    }

    /**
     * 删除标签
     *
     * @param tagId
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteTagById(Integer tagId) {
        Tag tag = tagDao.findTagById(tagId);
        //查询此标签下是否有博文
        if (blogDao.findBlogCountByTagId(tagId) > 0) {
            throw new RuntimeException("此标签关联了博客");
        }
        tagDao.deleteTag(tagId);
    }

    /**
     * 更改标签
     *
     * @param tagId
     * @param tagName
     */
    public void updateTag(Integer tagId, String tagName) {
        Tag tag = tagDao.findTagById(tagId);
        tag.setName(tagName);
        tagDao.updateTagName(tag);
    }


    /**
     * 查询所有标签
     * @return
     */
    public List<Tag> findAllTag() {
        return tagDao.findAll();
    }
}

