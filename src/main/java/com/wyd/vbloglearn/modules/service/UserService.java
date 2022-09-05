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
     * 注册功能
     */
    User register(UserParam userParam);

    /**
     * 登录功能
     */
    String login(UserLoginParam userLoginParam);

    /**
     * 刷新token的功能
     *
     * @param oldToken 旧的token
     */
    String refreshToken(String oldToken);

    /**
     * 根据用户名或昵称分页查询用户
     */
    Page<User> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 修改指定用户信息
     */

    /**
     * 删除指定用户
     */

    /**
     * 修改用户角色关系
     */

    /**
     * 获取用户对于角色
     */


    /**
     * 获取指定用户的可访问资源
     */

    /**
     * 修改密码
     */


    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 获取缓存服务
     */

}
