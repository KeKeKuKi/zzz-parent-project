package per.zzz.security.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/1/6 19:47
 */
@Data
public class SecurityUser implements UserDetails {

    private String username;

    private String password;

    private Integer enabled;

    private Integer locked;

    private List<String> permissions;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 待权限引入
        return permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return locked.equals(0);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled.equals(0);
    }
}
