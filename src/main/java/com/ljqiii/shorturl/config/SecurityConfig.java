package com.ljqiii.shorturl.config;

import com.ljqiii.shorturl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${secert_key}")
    private String KEY = "ljqiii";

    @Autowired
    LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private UserService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder); // 设置密码加密方式
        return authenticationProvider;
    }

    /**
     * 自定义配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers(HttpMethod.POST, "/admin/**", "/admin/deleteshorturl").hasAnyRole("admin");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/admin/**").hasAnyRole("admin");

        http.authorizeRequests().antMatchers(HttpMethod.POST, "/login").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/login").permitAll();

        //基于 Form 表单登录验证
        http.formLogin().loginPage("/login").failureUrl("/login-error")
                .successHandler(loginSuccessHandler)
                .and().rememberMe().key(KEY)
                .and().exceptionHandling().accessDeniedPage("/403");
        //监控
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/actuator/**").hasIpAddress("127.0.0.1");
        http.authorizeRequests().antMatchers("/druid/**").hasIpAddress("127.0.0.1");

        //注销登录
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true);

        http.headers().frameOptions().sameOrigin();

        //在测试和上传文件时禁用 csrf
        http.csrf().ignoringAntMatchers("/uploadimg", "/test**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());

    }
}
