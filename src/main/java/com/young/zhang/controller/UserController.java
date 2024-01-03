package com.young.zhang.controller;


import ch.qos.logback.core.net.SyslogOutputStream;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.young.zhang.pojo.Result;
import com.young.zhang.pojo.User;
import com.young.zhang.service.UserService;
import com.young.zhang.utils.PreCheckString;
import com.young.zhang.utils.ThreadLocalUtil;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Young.Zhang
 * @since 2023-12-17
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;
    private PreCheckString preCheckString = new PreCheckString();

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        String preCheck = preCheckString.checkLongthLess(username, 5) + preCheckString.checkLongthLess(password, 5)
                + preCheckString.checkLongthGreater(username, 16) + preCheckString.checkLongthGreater(password, 16);
        if ("".equals(preCheck)) {
            //查询用户重名
            User user = userService.findByUserName(username);
            if (user == null) {
                //注册
                userService.register(username, password);
                return Result.success();
            } else {
                //重名
                return Result.erro("User name already existed ~");
            }

        }else {
            return Result.erro(preCheck);
        }

    }

    @PostMapping("/login")
    public Result login(@Pattern(regexp="^\\S{5,16}$") String username, @Pattern(regexp="^\\S{5,16}$") String password) {
        //user exist or not
        User user = userService.findByUserName(username);
        if (user == null) {
            return Result.erro("Please check user name and retry.");
        }
        //password correct or not
        if (user.getPassword().equals(DigestUtil.md5Hex(password))) {
            Map userMap = new HashMap<>();
            userMap.put("username", user.getUsername());
            userMap.put("id", user.getId());
            userMap.put(JWTPayload.EXPIRES_AT, DateTime.now().offsetNew(DateField.MINUTE, 20));
            byte[] key = new byte[]{'Y'};

            String token = JWTUtil.createToken(userMap, key);
            return Result.success(token);

        }
        return Result.erro("Please check your password and retry.");
    }

    @GetMapping("/userinfo")
    public Result userinfo(/*@RequestHeader(name = "Authorization") String token*/) {
        JWT jwt = ThreadLocalUtil.get();
        JWTPayload payload = jwt.getPayload();
        String username = (String) payload.getClaim("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("update")
    public Result update(@RequestBody @Validated(User.Update.class) User user) {
        try {
            userService.update(user);
            return Result.success();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Result.erro("Something error while updating user information, please contact IT for more details.");
        }
    }

    @PatchMapping("/updatePic")
    public Result updatePic(@RequestParam @NotEmpty @URL String avatarUrl) {
        userService.updatePic(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> updatePwdMap) {
        String cur_pwd = (String) updatePwdMap.get("cur_pwd");
        String new_pwd = (String) updatePwdMap.get("new_pwd");
        String re_pwd = (String) updatePwdMap.get("re_pwd");
        if (!StringUtils.hasLength(cur_pwd) ||
                !StringUtils.hasLength(new_pwd) ||
                !StringUtils.hasLength(re_pwd)) {
            return Result.erro("there's exist empty value, please check and retry.");
        }

        if (!new_pwd.equals(re_pwd)) {
            return Result.erro("The repeat password should be same as new password, please check and retry.");
        }

        JWT jwt = ThreadLocalUtil.get();
        Object username = jwt.getPayload().getClaim("username");
        User userByName = userService.findByUserName(username.toString());
        if (!userByName.getPassword().equals(DigestUtil.md5Hex(cur_pwd))) {
            return Result.erro("The current password is not right, please check and retry.");
        }
        userService.updatePwd(new_pwd);
        return Result.success();
    }


}

