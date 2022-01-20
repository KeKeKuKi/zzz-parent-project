package per.zzz.auth.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import per.zzz.auth.dao.UserDao;
import per.zzz.auth.entity.Permission;
import per.zzz.auth.entity.User;
import per.zzz.auth.service.PermissionService;
import per.zzz.base.utils.BeanCopyUtils;
import per.zzz.base.utils.QueryWrapperBuilder;
import per.zzz.security.entity.SecurityUser;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/1/17 10:22
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserDao userDao;

    private PermissionService permissionService;

    public UserDetailsServiceImpl(UserDao userDao, PermissionService permissionService) {
        this.userDao = userDao;
        this.permissionService = permissionService;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User one = userDao.getOne(QueryWrapperBuilder.<User>wrapper().eq(User::getUsername, userName));
        Assert.isTrue(one != null, "账户不存在");
        List<Permission> permissions = permissionService.listByUserId(one.getId());
        final SecurityUser copy = BeanCopyUtils.copy(one, new SecurityUser());
        copy.setPermissions(permissions.stream().map(Permission::getValue).collect(Collectors.toList()));
        return copy;
    }
}
