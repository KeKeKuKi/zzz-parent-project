package per.zzz.auth.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import per.zzz.auth.dto.role.RoleDTO;
import per.zzz.auth.entity.Role;
import per.zzz.auth.entity.User;
import per.zzz.base.utils.PageRequest;

import java.util.List;

/**
 * 服务接口
 *
 * @author 阿忠
 * @since 2022-01-17 10:20:30
 * @description 自动创建
 */
public interface RoleService {

    IPage<Role> page(PageRequest<RoleDTO> pageRequest);

    List<Role> list(RoleDTO queryDTO);

    Role findById(Integer id);

    Boolean add(RoleDTO dto);

    boolean update(RoleDTO dto);
}
