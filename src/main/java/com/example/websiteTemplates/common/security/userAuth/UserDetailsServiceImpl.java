package com.example.websiteTemplates.common.security.userAuth;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.websiteTemplates.common.security.userAuth.mapper.SysUserMapper;
import com.example.websiteTemplates.common.security.userAuth.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


// SpringSecurity在进行用户认证时会自动调用 UserDetailsService 中的 loadUserByUsername 方法
// 该方法通过实现Security暴露出的UserDetailsService接口实现用户信息的认证
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUserName, username);
        SysUser user = userMapper.selectOne(wrapper);
        //如果查询不到数据就通过抛出异常来给出提示
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户不存在或密码错误");
        }
        //TODO 根据用户查询权限信息 添加到LoginUser中
        List<String> list = new ArrayList<>(Arrays.asList("test", "admin"));
        //封装成UserDetails对象返回
        return new LoginUser(user, list);
    }
}
