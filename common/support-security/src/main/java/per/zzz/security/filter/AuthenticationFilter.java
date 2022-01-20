package per.zzz.security.filter;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import per.zzz.sdr.service.CacheService;
import per.zzz.security.entity.SecurityUser;
import per.zzz.security.security.TokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/1/7 14:44
 */
@Component
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private TokenService tokenService;

    private CacheService cacheService;

    private AuthenticationManager authenticationManager;

    public AuthenticationFilter(TokenService tokenService, CacheService cacheService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.cacheService = cacheService;
        this.authenticationManager = authenticationManager;

        this.setPostOnly(false);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("AuthenticationFilter --- 用户授权失败");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityUser securityUser = (SecurityUser) authResult.getPrincipal();

        String token = tokenService.creatToken(securityUser.getUsername());

        cacheService.hSet("user-per.zzz.auth.auth",securityUser.getUsername(), JSONArray.toJSONString(securityUser.getAuthorities()));

        response.setHeader("token", token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(402);
    }




}
