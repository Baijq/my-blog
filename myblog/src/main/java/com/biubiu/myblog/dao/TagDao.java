package com.biubiu.myblog.dao;

import com.biubiu.myblog.entity.Tag;

import java.util.List;

public interface TagDao {

    /**
     * 新增标签
     *
     * @param tag
     */
    void saveTag(Tag tag);

    /**
     * 根据tag id更改标签
     *
     * @param tag
     */
    void updateTagName(Tag tag);

    /**
     * 根据tag id删除标签
     *
     * @param id
     */
    void deleteTag(Integer id);

    /**
     * 根据id查询标签
     *
     * @param tagId
     * @return
     */
    Tag findTagById(Integer tagId);


    /**
     * 查询博文的所有标签
     *
     * @param blogId
     * @return
     */
    List<Tag> findTagByBlogId(Integer blogId);

    /**
     * 根据博文id删除标签
     *
     * @param blogId
     */
    void deleteTagByBlogId(Integer blogId);

    /**
     * 根据tagId删除blog_tag 的记录
     *
     * @param tagId
     */
    void deleteBlogTagByTagId(Integer tagId);

    /**
     * 根据标签名查询标签
     *
     * @return
     */
    Tag findTagByTagName(String tagName);

    /**
     * 查询所有标签
     * @return
     */
    List<Tag> findAll();
}
