package per.zzz.oauth2.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import per.zzz.security.entity.SecurityUser;

import java.util.Collections;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/1/17 10:22
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SecurityUser copy = new SecurityUser();
        copy.setUsername(userName);
        copy.setPassword(new BCryptPasswordEncoder().encode("123456"));
        copy.setPermissions(Collections.singletonList("admin"));
        copy.setEnabled(0);
        copy.setLocked(0);
        return copy;
    }
}
