package per.zzz.auth.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import per.zzz.auth.dao.PermissionDao;
import per.zzz.auth.dao.RolePermissionDao;
import per.zzz.auth.dao.UserRoleDao;
import per.zzz.auth.entity.Permission;
import per.zzz.auth.entity.RolePermission;
import per.zzz.auth.entity.UserRole;
import per.zzz.auth.service.PermissionService;
import per.zzz.base.utils.QueryWrapperBuilder;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 服务接口实现
 *
 * @author 阿忠
 * @since 2022-01-17 10:20:30
 * @description 自动创建
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private final PermissionDao permissionDao;
    @Resource
    private final UserRoleDao userRoleDao;
    @Resource
    private final RolePermissionDao rolePermissionDao;

    @Override
    public List<Permission> listByUserId(Long userId) {
        List<UserRole> list = userRoleDao.list(QueryWrapperBuilder.<UserRole>wrapper().eq(UserRole::getUid, userId));
        List<RolePermission> rolePermissions = rolePermissionDao.list(QueryWrapperBuilder.<RolePermission>wrapper()
                .in(RolePermission::getRid, list.stream().map(UserRole::getRid).collect(Collectors.toList())));
        return permissionDao.list(QueryWrapperBuilder.<Permission>wrapper()
                .in(Permission::getId, rolePermissions.stream().map(RolePermission::getRid).collect(Collectors.toList())));
    }
}