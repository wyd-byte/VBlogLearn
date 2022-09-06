package com.wyd.vbloglearn.modules.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wyd.vbloglearn.modules.dto.UserLoginParam;
import com.wyd.vbloglearn.modules.dto.UserParam;
import com.wyd.vbloglearn.modules.model.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 用户管理Service
 * </p>
 *
 * @author wyd
 * @since 2022-08-31 22:09:07
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户名获取后台用户
     */
    User getUserByUsername(String username);




    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

    String login(String username, String password);

    /**
     * 获取缓存服务
     */

}
