package per.zzz.auth.service;


import per.zzz.auth.dto.userRole.UserRoleDTO;
import per.zzz.auth.entity.UserRole;

import java.util.List;

/**
 * 服务接口
 *
 * @author 阿忠
 * @since 2022-01-17 10:20:30
 * @description 自动创建
 */
public interface UserRoleService {

    Boolean add(UserRoleDTO dto);

    List<UserRole> list(UserRoleDTO dto);

    Boolean del(UserRoleDTO dto);
}
