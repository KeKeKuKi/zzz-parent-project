package per.zzz.auth.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import per.zzz.auth.dto.role.RoleAddDTO;
import per.zzz.auth.dto.role.RoleDTO;
import per.zzz.auth.dto.role.RoleUpdateDTO;
import per.zzz.auth.entity.Role;
import per.zzz.mybatis.utils.PageRequest;

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

    List<Role> listByUserId(Long userId);

    Role findById(Integer id);

    Boolean add(RoleAddDTO dto);

    boolean update(RoleUpdateDTO dto);

    Boolean del(Integer id);
}
