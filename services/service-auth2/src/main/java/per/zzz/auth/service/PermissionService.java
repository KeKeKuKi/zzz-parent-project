package per.zzz.auth.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import per.zzz.auth.dto.permission.PermissionDTO;
import per.zzz.auth.entity.Permission;
import per.zzz.auth.entity.Role;
import per.zzz.base.utils.PageRequest;

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

    IPage<Permission> page(PageRequest<PermissionDTO> pageRequest);

    List<Permission> list(PermissionDTO queryDTO);

    Permission findById(Integer id);

    Boolean add(PermissionDTO dto);

    Boolean update(PermissionDTO dto);
}
