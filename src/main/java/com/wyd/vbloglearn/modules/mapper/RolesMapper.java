package com.wyd.vbloglearn.modules.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyd.vbloglearn.modules.model.Roles;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wyd
 * @since 2022-08-31 22:09:07
 */
public interface RolesMapper extends BaseMapper<Roles> {

    List<Roles> getRolesByUserId(Integer userId);
}
