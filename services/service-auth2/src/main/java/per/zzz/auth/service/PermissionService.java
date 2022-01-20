package per.zzz.auth.service;


import per.zzz.auth.entity.Permission;

import java.util.List;

/**
 * 服务接口
 *
 * @author 阿忠
 * @since 2022-01-17 10:20:30
 * @description 自动创建
 */
public interface PermissionService {
    List<Permission> listByUserId(Long userId);
}
