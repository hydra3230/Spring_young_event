package com.young.zhang.service;

import com.young.zhang.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Young.Zhang
 * @since 2023-12-17
 */
public interface UserService extends IService<User> {

    User findByUserName(String userName);

    void register(String userName, String password);

    void update(User user);

    void updatePic(String avatarUrl);

    void updatePwd(String toString);
}
