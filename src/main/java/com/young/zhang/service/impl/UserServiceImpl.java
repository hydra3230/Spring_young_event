package com.young.zhang.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.jwt.JWT;
import com.young.zhang.pojo.User;
import com.young.zhang.mapper.UserMapper;
import com.young.zhang.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.young.zhang.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Young.Zhang
 * @since 2023-12-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String userName) {
        User user = userMapper.findByUserName(userName);
        return user;
    }

    @Override
    public void register(String userName, String password) {
        String md5Password = DigestUtil.md5Hex(password);
        userMapper.register(userName, md5Password);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void updatePic(String avatarUrl) {
        JWT jwt = ThreadLocalUtil.get();
        Object id = jwt.getPayload().getClaim("id");
        userMapper.updatePic(avatarUrl, Integer.parseInt(id.toString()));
    }

    @Override
    public void updatePwd(String new_pwd) {
        JWT jwt = ThreadLocalUtil.get();
        Object id =jwt.getPayload().getClaim("id");
        userMapper.updatePwd(DigestUtil.md5Hex(new_pwd), Integer.parseInt(id.toString()));
    }


}
