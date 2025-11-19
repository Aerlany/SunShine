package com.example.websiteTemplates.common.security.userAuth.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.websiteTemplates.common.security.userAuth.LoginUser;
import com.example.websiteTemplates.common.security.userAuth.mapper.SysUserMapper;
import com.example.websiteTemplates.common.security.userAuth.model.SysUser;
import com.example.websiteTemplates.common.security.userAuth.service.ISysUserService;
import com.example.websiteTemplates.common.tools.JwtUtil;
import com.example.websiteTemplates.common.tools.Redis.RedisCache;
import com.example.websiteTemplates.common.tools.web.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Aerlany
 * @since 2024-04-15
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisCache redisCache;

    // 用户登录接口
    @Override
    public ResponseResult Login(SysUser user) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        // 从容器中拿出 AuthenticationManager 实现 用户认证
        // 调用 authenticationManager 中的认证方法
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 相当于调用 UserDetailsServiceImpl 中的 loadUserByUsername 方法进行用户名和密码的校验

        // 认证没通过,给出对应的提示
        if (Objects.isNull(authenticate)) {
            return new ResponseResult<>(200, "登录失败");
//            throw new RuntimeException("登录失败");
        }

        //认证通过生成JWT,将其存入ResponseResult传给前端
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        SysUser userInfo = loginUser.getUser();
        String userId = userInfo.getId().toString();
        String jwt = JwtUtil.createJWT(userId);

        //将JWT保存到redis中
        redisCache.setCacheObject("sys_login_user_" + userId, loginUser);
        return new ResponseResult<>(200, true, jwt, userInfo);
    }

    @Override
    public ResponseResult logout() {
        //在SecurityContextHolder中取出当前用户的ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long id = loginUser.getUser().getId();

        redisCache.deleteObject("login" + id);
        return new ResponseResult<>(200, true, "Logout success", "");
    }

    //注册
    @Override
    public ResponseResult register(SysUser user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            user.setPassword(passwordEncoder.encode("000000"));
        }

        sysUserMapper.insert(user);
        return new ResponseResult(200, true, "注册成功", "");
    }

    //管理员添加账号
    @Override
    public ResponseResult addUser(SysUser user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode("000000"));
        try {
            sysUserMapper.insert(user);
        } catch (Exception e) {
            return new ResponseResult(500, false, "添加出错", "");
        }
        return new ResponseResult(200, true, "添加成功", "");
    }

    //分页查询用户信息
    @Override
    public ResponseResult getByPage(int currentPage, int pageSize) {
        Page<SysUser> userPage = new Page<>(currentPage, pageSize);
        IPage<SysUser> userIPage = sysUserMapper.selectPage(userPage, null);
        return new ResponseResult(200, true, String.valueOf(userIPage.getTotal()), userIPage.getRecords());
    }

    //批量删除
    @Transactional
    @Override
    public ResponseResult deleteAll(Integer[] projectIDs) {
        for (Integer id : projectIDs) {
            sysUserMapper.deleteById(id);
        }
        return new ResponseResult(200, true);
    }
}
