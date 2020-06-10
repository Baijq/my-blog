package com.biubiu.myblog.service;

import com.biubiu.myblog.dao.UserDao;
import com.biubiu.myblog.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * UserService
 *
 * @Author wbbaijq
 * @Date 2020/5/5 2:08
 */
@Service
public class UserService {

    @Resource
    private UserDao userDao;

    public List<User> findAll() {
        return userDao.findAll();
    }


    public User findById(Integer id) {
        return userDao.findById(id);
    }


    public List<User> find(User user) {
        return userDao.find(user);
    }


    public User save(User user) {
        userDao.save(user);
        if (user.getId() != null) {
            return userDao.findById(user.getId());
        }
        return null;
    }


    public void remove(Integer id) {
        userDao.remove(id);
    }


    public void delete(Integer id) {
        userDao.delete(id);
    }


    public User update(User user) {
        userDao.update(user);
        if (user.getId() != null) {
            return userDao.findById(user.getId());
        }
        return null;
    }

}
