package com.wyd.vbloglearn.modules.controller;


import com.wyd.vbloglearn.common.api.CommonResult;
import com.wyd.vbloglearn.domain.MyUserDetails;
import com.wyd.vbloglearn.modules.dto.UserLoginParam;
import com.wyd.vbloglearn.modules.model.User;
import com.wyd.vbloglearn.modules.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wyd
 * @since 2022-08-31 22:09:07
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = "UserController")
@Tag(name = "UserController", description = "用户后台管理")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    /**
     * 获取当前登录用户
     *
     * @param principal
     * @return
     */
    private User getCurrentUser(Principal principal) {
        if (principal == null) {
            return null;
        }

        String username = principal.getName();
        User user = userService.getUserByUsername(username);
        return user;
    }

    @ApiOperation("用户登录返回token")
    @PostMapping("/login")
    public CommonResult login(@RequestBody UserLoginParam userLoginParam) {
        String token = userService.login(userLoginParam.getUsername(), userLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        HashMap<String, String> tokenMap = new HashMap<>();
        tokenMap.put("tokenHead", "Bearer");
        tokenMap.put("token", token);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation("获取用户当前邮箱")
    @GetMapping("/currentUserEmail")
    // principal包含在Security封装的request当中
    public CommonResult currentUserEmail(Principal principal) {
        return CommonResult.success(getCurrentUser(principal).getEmail());
    }

    @ApiOperation("获取当前用户id")
    @GetMapping("/currentUserId")
    public CommonResult currentUserId(Principal principal) {
        return CommonResult.success(getCurrentUser(principal).getId());
    }

    @ApiOperation("获取当前用户名")
    @GetMapping("/currentUserName")
    public CommonResult currentUserName(Principal principal) {
        return CommonResult.success(getCurrentUser(principal).getUsername());
    }

    @ApiOperation("判断当前用户是否是超级管理员")
    @GetMapping("/isAdmin")
    public CommonResult isAdmin() {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) myUserDetails.getAuthorities();
        log.info("权限列表为:{}", authorities);
//        System.out.println(authorities.get(0).getAuthority() instanceof String);//true
        for (GrantedAuthority authority :
                authorities) {
            if (authority.getAuthority().contains("超级管理员")) {
                return CommonResult.success(true, "是超级管理员");
            }
        }
        return CommonResult.failed("不是超级管理员");
    }

    @ApiOperation("更新用户邮箱")
    @PutMapping("/updateUserEmail")
    public CommonResult updateUserEmail(@RequestBody String newEmail, Principal principal) {
        User currentUser = getCurrentUser(principal);
        currentUser.setEmail(newEmail);
        Boolean success = userService.updateUserEmail(currentUser);
        if (success) {
            return CommonResult.success(currentUser.getEmail());
        }
        return CommonResult.failed("更新失败");
    }
}

