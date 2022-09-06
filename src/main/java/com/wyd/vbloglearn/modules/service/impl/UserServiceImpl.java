package com.wyd.vbloglearn.modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyd.vbloglearn.domain.MyUserDetails;
import com.wyd.vbloglearn.modules.mapper.RolesMapper;
import com.wyd.vbloglearn.modules.mapper.UserMapper;
import com.wyd.vbloglearn.modules.model.Roles;
import com.wyd.vbloglearn.modules.model.User;
import com.wyd.vbloglearn.modules.service.UserService;
import com.wyd.vbloglearn.security.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wyd
 * @since 2022-08-31 22:09:07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RolesMapper rolesMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public User getUserByUsername(String username) {
        // 构建mp查询构造器
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(User::getUsername, username);
        List<User> userList = list(wrapper);
        if (userList != null && userList.size() > 0) {
            User user = userList.get(0);
            return user;
        }
        return null;
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        //获取用户信息,构造出springsecurity需要的用户类
        User user = getUserByUsername(username);
        if (user != null) {
            List<Roles> rolesList = getRolesListByUserId(user.getId());
            return new MyUserDetails(user, rolesList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public String login(String username, String password) {
        UserDetails userDetails = loadUserByUsername(username);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            System.out.println("密码不正确");
        }
        if (!userDetails.isEnabled()) {
            System.out.println("账户已禁用");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.generateUserToken(userDetails);
        return token;
    }

    private List<Roles> getRolesListByUserId(Integer userId) {
        List<Roles> userRoles = rolesMapper.getRolesByUserId(userId);
        return userRoles;
    }


}
