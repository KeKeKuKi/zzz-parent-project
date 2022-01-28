package per.zzz.auth.service;


import per.zzz.auth.dto.permission.PermissionDTO;
import per.zzz.auth.dto.rolePermission.RolePermissionDTO;
import per.zzz.auth.entity.Permission;
import per.zzz.auth.entity.RolePermission;

import java.util.List;

/**
 * 服务接口
 *
 * @author 阿忠
 * @since 2022-01-17 10:20:30
 * @description 自动创建
 */
public interface RolePermissionService {

    Boolean add(RolePermissionDTO dto);

    Boolean del(RolePermissionDTO dto);

    List<RolePermission> list(RolePermissionDTO dto);
}
