package com.example.websiteTemplates.common.security.userAuth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.websiteTemplates.common.security.userAuth.model.SysUser;
import com.example.websiteTemplates.common.tools.web.ResponseResult;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Aerlany
 * @since 2024-04-15
 */
public interface ISysUserService extends IService<SysUser> {
    //登录
    ResponseResult Login(SysUser user);
    //注销
    ResponseResult logout();
    //注册
    ResponseResult register(SysUser user);

    //管理员添加账号
    ResponseResult addUser(SysUser user);

    ResponseResult getByPage(int currentPage, int pageSize);

    //批量删除
    ResponseResult deleteAll(Integer[] projectIDs);
}
