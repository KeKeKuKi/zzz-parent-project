package per.zzz.blog.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import per.zzz.blog.dto.UserDTO;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/2/16 17:37
 */
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //解析出头中的token
        String token = httpServletRequest.getHeader("json-token");
        if(token!=null){
            String json = token;
            //将token转成json对象
            JSONObject jsonObject = JSON.parseObject(json);
            //用户身份信息
            UserDTO userDTO = new UserDTO();
            String principal = jsonObject.getString("principal");
            userDTO.setUsername(principal);
//            UserDTO userDTO = JSON.parseObject(jsonObject.getString("principal"), UserDTO.class);
            //用户权限
            JSONArray authoritiesArray = jsonObject.getJSONArray("authorities");
            String[] authorities = authoritiesArray.toArray(new String[authoritiesArray.size()]);
            //将用户信息和权限填充 到用户身份token对象中
            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(userDTO,null, AuthorityUtils.createAuthorityList(authorities));
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            //将authenticationToken填充到安全上下文
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);


        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);

    }
}
