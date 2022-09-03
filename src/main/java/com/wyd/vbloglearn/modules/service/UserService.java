package com.wyd.vbloglearn.modules.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyd.vbloglearn.modules.model.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wyd
 * @since 2022-08-31 22:09:07
 */
public interface UserService extends IService<User> {

    //通过用户名获取用户
    User getUserByUsername(String username);

    //获取用户信息
    UserDetails loadUserByUsername(String username);
}
