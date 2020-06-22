package com.vn.bgshop.boardgameshop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;


/*
 *** CSRF token
        Nếu chúng ta sử dụng annotation @EnableWebSecurity và template engine Thymeleaf
        thì form login mặc định sẽ có thêm CSRF token, nên chúng ta không cần phải cấu hình gì thêm.
 *** CSRF là gì? (Cách gọi khác "session riding", "XSRF")
        Là cách hacker mượn quyền trái phép của user để tấn công ứng dụng
 *** Cách thực hiện:
        * Gửi một mã độc hoặc 1 link đến trang của User
        * VD: <img height="0" width="0" src="http://www.webapp.com/userinfo?addPhone=0123">
        * User ấn vào khi vẫn đang ở phiên làm việc của mình nên được link trên sẽ hoạt động
 *** Cách phòng chống:
        * USER: Đừng nghịch linh tinh :))
        * SERVER:
            * Lựa chọn việc dùng GET POST
            * Sử dụng captcha, thông báo xác nhân
            * Sử dụng token
            * Sử dụng cookie riêng cho admin
            * Kiểm tra REFERRER
            * Kiểm tra ID
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

   /*
    *** passwordEncoder la mot interface dam nhan viec ma hoa mat khau
    *** BCryptPasswordEncoder la mot implementation thuc thi no
    *** BCrypt là thuật toán băm ký tự đạt chuẩn và được khuyên dùng cùng với (SCrypt và 1 cái tên dài dài)
    */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(this.passwordEncoder());
    }

    /*
     *** antMatchers(): Khai bao url cua request
     *** permitAll(): cho phép tất cả các user đều được phép truy cập.
     *** hasRole(roleName): chỉ cho phép các user có GrantedAuthority là ROLE_roleName mới được phép truy cập
     *** access("hasAnyRole('roleName','roleName')") : Chi can rolename co trong hasAnyRole la duoc phep truy cap
     */
    protected void configure(HttpSecurity http) throws Exception {
        //Day la cach viet tat
        http.
                authorizeRequests()
                    /*.antMatchers("/register","/","home").permitAll()*/
                    /*.antMatchers("/").access("hasAnyRole('ADMIN','USER')")*/
                    .antMatchers("/cart").hasRole("USER")
                    .antMatchers("/edit-information/**").hasRole("USER")
                    .antMatchers("/admin/employee").hasRole("ADMIN_MANAGER")
                    .antMatchers("/admin/**").hasRole("ADMIN_STAFF")
                    .and()
                .formLogin()
                    .loginPage("/home/login")
                    .loginProcessingUrl("/j_spring_security_check")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/",true)
                    .failureUrl("/home/login-fail")
                    .and()
                .exceptionHandling()
                    .accessDeniedPage("/403")
        ;
    }

    @Bean
    public HttpFirewall defaultHttpFirewall() {
        return new DefaultHttpFirewall();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //@formatter:off
        super.configure(web);
        web.httpFirewall(defaultHttpFirewall());
    }
}
