package com.biubiu.myblog.dao;

import com.biubiu.myblog.entity.DictInfo;

import java.util.List;

/**
 * 数据字典
 *
 * @author wbbaijq
 */
public interface DictInfoDao {

    List<DictInfo> queryAll();

    DictInfo queryByName(String name);

    int addDictInfo(DictInfo dictInfo);

    int deleteById(int id);

}
