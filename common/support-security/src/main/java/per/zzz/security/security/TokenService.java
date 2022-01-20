package per.zzz.security.security;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/1/14 11:40
 */
@Component
public class TokenService {
    // token有效期
    private static final long TOKEN_EXPIRATION = 24 * 60 * 60 * 1000;

    // 密钥
    private static final String TOKEN_SIGN_KEY = "zzz-token-20220114";

    public String creatToken(String userName){
        return Jwts.builder().setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, TOKEN_SIGN_KEY)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }

    public String getUserInfo(String token){
        return Jwts.parser().setSigningKey(TOKEN_SIGN_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public Boolean removeToken(String token){
        return true;
    }
}
