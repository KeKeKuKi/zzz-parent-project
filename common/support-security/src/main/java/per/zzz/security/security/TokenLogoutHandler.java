package per.zzz.security.security;


import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import per.zzz.sdr.service.CacheService;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/1/14 11:56
 */
@Component
public class TokenLogoutHandler implements LogoutHandler {

    private final CacheService cacheService;

    private final TokenService tokenService;

    public TokenLogoutHandler(CacheService cacheService, TokenService tokenService) {
        this.cacheService = cacheService;
        this.tokenService = tokenService;
    }


    @Override
    public void logout(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Authentication authentication) {
        String token = request.getHeader("token");

        if(token != null){
            tokenService.removeToken(token);

            String userName = tokenService.getUserInfo(token);
            cacheService.del(userName);
        }

    }
}
