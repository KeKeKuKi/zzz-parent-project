package per.zzz.security.filter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import per.zzz.base.utils.Result;
import per.zzz.sdr.service.CacheService;
import per.zzz.security.core.SecurityContext;
import per.zzz.security.core.SecurityContextHolder;
import per.zzz.security.entity.SecurityUser;
import per.zzz.security.security.TokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/1/7 14:44
 */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final TokenService tokenService;

    private final CacheService cacheService;

    private final AuthenticationManager authenticationManager;

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
            SecurityUser user = new ObjectMapper().readValue(request.getInputStream(), SecurityUser.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("AuthenticationFilter --- 用户授权失败");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        SecurityUser securityUser = (SecurityUser) authResult.getPrincipal();
        String token = tokenService.creatToken(securityUser.getUsername());
        List<String> collect = securityUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        cacheService.hSet("user-permissions",securityUser.getUsername(), JSONArray.toJSONString(collect));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);
        response.getWriter().write(JSONObject.toJSONString(Result.success(jsonObject)));
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");
        SecurityContextHolder.set(SecurityContext.builder().permissions(new HashSet<>(collect)).token(token).build());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(JSONObject.toJSONString(Result.fail(failed.getMessage())));
        response.setContentType("application/json;charset=UTF-8");
    }




}
