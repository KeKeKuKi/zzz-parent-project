package per.zzz.auth.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import per.zzz.auth.dao.PermissionDao;
import per.zzz.auth.dao.RolePermissionDao;
import per.zzz.auth.dao.UserRoleDao;
import per.zzz.auth.dto.permission.PermissionDTO;
import per.zzz.auth.entity.Permission;
import per.zzz.auth.entity.RolePermission;
import per.zzz.auth.entity.UserRole;
import per.zzz.auth.mapper.PermissionMapper;
import per.zzz.auth.service.PermissionService;
import per.zzz.base.utils.BeanCopyUtils;
import per.zzz.mybatis.utils.PageRequest;
import per.zzz.mybatis.utils.QueryWrapperBuilder;

import java.util.ArrayList;
import java.util.Date;
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

    private final PermissionMapper permissionMapper;

    private QueryWrapperBuilder<Permission> buildQuery(PermissionDTO queryParam){
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
        if (CollectionUtils.isEmpty(rolePermissions)){
            return new ArrayList<>();
        }
        return permissionDao.list(QueryWrapperBuilder.<Permission>wrapper()
                .in(Permission::getId, rolePermissions.stream().map(RolePermission::getMid).collect(Collectors.toList())));
    }

    @Override
    public IPage<Permission> page(PageRequest<PermissionDTO> pageRequest) {
        return permissionDao.page(new Page<>(pageRequest.getPage(), pageRequest.getSize()), buildQuery(pageRequest.getQueryParam()));
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
    public Permission findByValue(String value) {
        return permissionMapper.findByValue(value);
    }

    @Override
    public Boolean add(PermissionDTO dto) {
        Permission byValue = permissionMapper.findByValue(dto.getValue());
        if(byValue != null){
            log.warn("该权限已存在:" + dto.getValue());
            return false;
        }

        dto.setType(1);
        dto.setCreatedTime(new Date());
        return permissionDao.save(BeanCopyUtils.copy(dto, new Permission()));
    }

    @Override
    public Boolean update(PermissionDTO dto) {
        dto.setModifyTime(new Date());
        return permissionDao.updateById(BeanCopyUtils.copy(dto, new Permission()));
    }

    @Override
    public List<Permission> listByRoleId(Integer roleId) {
        return permissionDao.listByRoleId(roleId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean del(Integer id) {
        QueryWrapperBuilder<RolePermission> wrapper = QueryWrapperBuilder.<RolePermission>wrapper().eq(RolePermission::getMid, id);
        rolePermissionDao.remove(wrapper);
        permissionDao.removeById(id);
        return true;
    }
}
