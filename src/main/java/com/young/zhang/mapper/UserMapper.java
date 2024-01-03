package com.young.zhang.mapper;

import com.young.zhang.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Young.Zhang
 * @since 2023-12-17
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where username = #{userName}")
    User findByUserName(String userName);

    @Insert("insert into user (username, password, create_time, update_time) " +
            "values (#{userName}, #{md5Password}, now(), now())")
    void register(String userName, String md5Password);

    @Update("update user set nickname = #{nickname}, email = #{email}, update_time = now() where username = #{username}")
    void update(User user);

    @Update("update user set user_pic = #{avatarUrl}, update_time = now() where id = #{id}")
    void updatePic(String avatarUrl, Integer id);

    @Update("update user set password = #{md5Hex}, update_time = now() where id = #{id}")
    void updatePwd(String md5Hex, Integer id);
}
