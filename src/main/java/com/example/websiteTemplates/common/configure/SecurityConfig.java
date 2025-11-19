package com.example.websiteTemplates.common.configure;

import com.example.websiteTemplates.common.security.Handler.AccessDeniedHandlerImpl;
import com.example.websiteTemplates.common.security.Handler.AuthenticationEntryPointImpl;
import com.example.websiteTemplates.common.security.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/*
    这是SpringBoot Security 配置文件
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    // 异常处理器
    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPoint;
    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandler;

    //改变密码校验策略为BCryptPasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //重写 configure 方法实现接口放行
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                // 前后端分类项目不通过session获取securityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                // 接口放行
                .authorizeRequests()
                .antMatchers("/user/login").anonymous()
                .antMatchers("/user/register").anonymous()

                // swagger放行
                .antMatchers("/swagger-ui/**").anonymous()
                .antMatchers("/swagger-resources/**").anonymous()
                .antMatchers("/v2/api-docs").anonymous()
                .antMatchers("/v3/api-docs").anonymous()
                .antMatchers("/webjars/**").anonymous()

                //文件相关接口放行
                .antMatchers("/staticResource/**").anonymous()

                //接口权限配置
                .antMatchers("/projectInformation/**").hasAuthority("admin")

                // 其他接口不放行
                .anyRequest().authenticated();


        //配置自定义过滤器位置
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //配置异常处理器
        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);

        // 允许跨域
        http.cors();
    }

    // 在容器中暴露 AuthenticationManager 类型以在 接口实现类中调用
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
