package com.biubiu.myblog.config;

import com.biubiu.myblog.dao.DictInfoDao;
import com.biubiu.myblog.entity.DictInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据字典缓存
 *
 * @author wbbaijq
 */
//@Component
public class DictInfoCache {

    private static final Logger log = LoggerFactory.getLogger(DictInfoCache.class);

    public static Map<String, DictInfo> dictCache = new HashMap<>();

//    @Resource
    private DictInfoDao dictInfoDao;

    //@PostConstruct
    public void init() {
        log.info("系统启动中...加载DictInfoCache");
        List<DictInfo> dictInfos = dictInfoDao.queryAll();
        for (DictInfo dictInfo: dictInfos) {
            dictCache.put(dictInfo.getName(), dictInfo);
        }
    }

    public Map<String, DictInfo> getDictInfoCache() {
        return dictCache;
    }

    public String getValue(String key) {
        if (!dictCache.containsKey(key)) {
            return null;
        }
        return dictCache.get(key).getValue();
    }

}
