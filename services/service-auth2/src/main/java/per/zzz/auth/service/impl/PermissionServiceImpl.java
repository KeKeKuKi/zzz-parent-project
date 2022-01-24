package per.zzz.auth.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import per.zzz.auth.dao.PermissionDao;
import per.zzz.auth.dao.RolePermissionDao;
import per.zzz.auth.dao.UserRoleDao;
import per.zzz.auth.dto.permission.PermissionDTO;
import per.zzz.auth.entity.Permission;
import per.zzz.auth.entity.RolePermission;
import per.zzz.auth.entity.UserRole;
import per.zzz.auth.service.PermissionService;
import per.zzz.base.utils.BeanCopyUtils;
import per.zzz.base.utils.PageRequest;
import per.zzz.base.utils.QueryWrapperBuilder;

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

    private final PermissionDao permissionDao;

    private final UserRoleDao userRoleDao;

    private final RolePermissionDao rolePermissionDao;

    private  QueryWrapperBuilder<Permission> buildQuery(PermissionDTO queryParam){
        return QueryWrapperBuilder.<Permission>wrapper()
                .eq(Permission::getName, queryParam.getName())
                .eq(Permission::getPath, queryParam.getPath())
                .eq(Permission::getType, queryParam.getType())
                .eq(Permission::getValue, queryParam.getValue());
    }

    @Override
    public List<Permission> listByUserId(Long userId) {
        List<UserRole> list = userRoleDao.list(QueryWrapperBuilder.<UserRole>wrapper().eq(UserRole::getUid, userId));
        List<RolePermission> rolePermissions = rolePermissionDao.list(QueryWrapperBuilder.<RolePermission>wrapper()
                .in(RolePermission::getRid, list.stream().map(UserRole::getRid).collect(Collectors.toList())));
        return permissionDao.list(QueryWrapperBuilder.<Permission>wrapper()
                .in(Permission::getId, rolePermissions.stream().map(RolePermission::getRid).collect(Collectors.toList())));
    }

    @Override
    public IPage<Permission> page(PageRequest<PermissionDTO> pageRequest) {
        return permissionDao.page(new Page<>(pageRequest.getPageNumber(), pageRequest.getPageSize()), buildQuery(pageRequest.getQueryParam()));
    }

    @Override
    public List<Permission> list(PermissionDTO queryDTO) {
        return permissionDao.list(buildQuery(queryDTO));
    }

    @Override
    public Permission findById(Integer id) {
        return permissionDao.getById(id);
    }

    @Override
    public Boolean add(PermissionDTO dto) {
        return permissionDao.save(BeanCopyUtils.copy(dto, new Permission()));
    }

    @Override
    public Boolean update(PermissionDTO dto) {
        return permissionDao.updateById(BeanCopyUtils.copy(dto, new Permission()));
    }
}
