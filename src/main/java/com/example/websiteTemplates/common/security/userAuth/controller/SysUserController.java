package com.example.websiteTemplates.common.security.userAuth.controller;


import com.example.websiteTemplates.common.security.userAuth.model.SysUser;
import com.example.websiteTemplates.common.security.userAuth.service.ISysUserService;
import com.example.websiteTemplates.common.tools.web.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Aerlany
 * @since 2024-04-15
 */
@RestController
@Api(tags = "用户接口")
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private ISysUserService service;

    @ApiOperation("登录接口")
    @PostMapping("/login")
    public ResponseResult Login(@RequestBody SysUser user) {
        return service.Login(user);
    }

    @ApiOperation("注销接口")
    @PostMapping("/logout")
    public ResponseResult Logout() {
        return service.logout();
    }

    @ApiOperation("注册接口")
    @PostMapping("/register")
    public ResponseResult register(@RequestBody SysUser user) {
        return service.register(user);
    }

    @ApiOperation("分页查询")
    @GetMapping("/getByPage/{currentPage}/{pageSize}")
    public ResponseResult getByPage(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        return service.getByPage(currentPage, pageSize);
    }

    @ApiOperation("管理员添加")
    @PutMapping("/addUser")
    public ResponseResult addUser(@RequestBody SysUser user) {
        return service.addUser(user);
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/deleteAll")
    public ResponseResult deleteAll(@RequestBody Integer[] projectIDs) {
        return service.deleteAll(projectIDs);
    }

    @ApiOperation("修改")
    @PutMapping("/modify")
    public ResponseResult modify(@RequestBody SysUser user) {
        return new ResponseResult<>(200, service.updateById(user));
    }
}
