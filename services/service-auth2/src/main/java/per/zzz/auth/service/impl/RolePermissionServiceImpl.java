package per.zzz.auth.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import per.zzz.auth.dao.RolePermissionDao;
import per.zzz.auth.service.RolePermissionService;

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
public class RolePermissionServiceImpl implements RolePermissionService {
    private final RolePermissionDao rolePermissionDao;

}