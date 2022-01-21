package per.zzz.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.DefaultLoginPageConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import per.zzz.sdr.service.CacheService;
import per.zzz.security.filter.AuthenticationFilter;
import per.zzz.security.filter.TokenFilter;
import per.zzz.security.security.DefaultPasswordEncoder;
import per.zzz.security.security.TokenService;
import per.zzz.security.security.UnAuthEntryPoints;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/1/7 15:08
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final TokenService tokenService;

    private final CacheService cacheService;

    public SecurityConfig(UserDetailsService userDetailsService, TokenService tokenService, CacheService cacheService) {
        this.userDetailsService = userDetailsService;
        this.tokenService = tokenService;
        this.cacheService = cacheService;
    }

    @Override
    @Bean
    @Primary
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new DefaultPasswordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.exceptionHandling().accessDeniedPage("/403.html")
                .authenticationEntryPoint(new UnAuthEntryPoints()); // 自定义403


        http.formLogin()
//                .loginPage("/") // 登录页地址
                .loginProcessingUrl("/login") // 登录接口地址
//                .defaultSuccessUrl("/").permitAll() // 主页地址
//                .and().logout().logoutUrl("/user/logout")
                .and().authorizeRequests()
                .antMatchers("/","/login/","/user/logout").permitAll()// 路径白名单
//                .antMatchers("/user/list").hasAuthority("admin")
//                .antMatchers("/user/log").hasAnyAuthority("admin","user")
                .anyRequest().authenticated()
                .and().csrf().disable()
                .addFilter(new TokenFilter(authenticationManager(), cacheService, tokenService))
                .addFilter(new AuthenticationFilter(tokenService, cacheService, authenticationManager()));
    }
}
