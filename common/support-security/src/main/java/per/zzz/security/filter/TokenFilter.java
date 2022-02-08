package per.zzz.security.filter;

import com.alibaba.fastjson.JSONArray;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import per.zzz.sdr.service.CacheService;
import per.zzz.security.security.TokenService;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/1/14 16:41
 */
public class TokenFilter extends BasicAuthenticationFilter {
    private final CacheService cacheService;

    private final TokenService tokenService;

    public TokenFilter(AuthenticationManager authenticationManager, CacheService cacheService, TokenService tokenService) {
        super(authenticationManager);
        this.cacheService = cacheService;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        if (authentication != null){
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request,response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        // 请求头获取token
        String token = request.getHeader("token");
        if (token == null){
            return null;
        }
        String userName = tokenService.getUserInfo(token);
        String s = cacheService.hGet("user-permissions", userName);
        List<String> permissions = JSONArray.parseArray(s, String.class);
        return new UsernamePasswordAuthenticationToken(userName, token, permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }

}
