package com.example.websiteTemplates.common.security.userAuth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.example.websiteTemplates.common.security.userAuth.model.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Aerlany
 * @since 2024-04-15
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}
