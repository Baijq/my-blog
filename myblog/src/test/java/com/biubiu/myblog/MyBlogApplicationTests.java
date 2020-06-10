package com.biubiu.myblog;

import com.biubiu.myblog.dao.DictInfoDao;
import com.biubiu.myblog.entity.DictInfo;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

//@SpringBootTest
class MyBlogApplicationTests {

    @Resource
    DictInfoDao dictInfoDao;

    @Test
    void contextLoads() {
        DictInfo dictInfo = new DictInfo();
        dictInfo.setName("IMG_ACCESS_PATH");
        dictInfo.setValue("/img/*");
        dictInfo.setInfo("访问图片时的路径");
        dictInfo.setCode("/blog/uploadImg");

        int i = dictInfoDao.addDictInfo(dictInfo);

        System.out.println(i);
    }

    @Test
    public void test() {
        String msg = "heHHkk;;KK  百晓吧";
        System.out.println(msg.toLowerCase());
    }
}
