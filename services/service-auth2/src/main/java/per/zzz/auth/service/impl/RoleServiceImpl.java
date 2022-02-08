package per.zzz.auth.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import per.zzz.auth.dao.RoleDao;
import per.zzz.auth.dto.role.RoleAddDTO;
import per.zzz.auth.dto.role.RoleDTO;
import per.zzz.auth.dto.role.RoleUpdateDTO;
import per.zzz.auth.dto.rolePermission.RolePermissionDTO;
import per.zzz.auth.entity.Role;
import per.zzz.auth.entity.RolePermission;
import per.zzz.auth.service.RolePermissionService;
import per.zzz.auth.service.RoleService;
import per.zzz.base.utils.BeanCopyUtils;
import per.zzz.mybatis.utils.PageRequest;
import per.zzz.mybatis.utils.QueryWrapperBuilder;

import java.util.List;

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
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    private final RolePermissionService rolePermissionService;

    QueryWrapperBuilder<Role> buildQuery(RoleDTO queryParam){
        return QueryWrapperBuilder.<Role>wrapper()
                .eq(Role::getCode, queryParam.getCode())
                .eq(Role::getName, queryParam.getName());
    }

    @Override
    public IPage<Role> page(PageRequest<RoleDTO> pageRequest) {
        return roleDao.page(new Page<>(pageRequest.getPage(), pageRequest.getSize()), buildQuery(pageRequest.getQueryParam()));
    }

    @Override
    public List<Role> list(RoleDTO queryDTO) {
        return roleDao.list(buildQuery(queryDTO));
    }

    @Override
    public List<Role> listByUserId(Long userId) {
        return roleDao.listByUserId(userId);
    }

    @Override
    public Role findById(Integer id) {
        return roleDao.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean add(RoleAddDTO dto) {
        Role copy = BeanCopyUtils.copy(dto, new Role());
        roleDao.save(copy);
        if(!CollectionUtils.isEmpty(dto.getPermissionIds())){
            dto.getPermissionIds().forEach(i -> {
                RolePermissionDTO rolePermission = new RolePermissionDTO();
                rolePermission.setMid(i);
                rolePermission.setRid(copy.getId());
                rolePermissionService.add(rolePermission);
            });
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean update(RoleUpdateDTO dto) {
        rolePermissionService.del(RolePermissionDTO.builder().rid(dto.getId()).build());
        dto.getPermissionIds().forEach(i -> rolePermissionService.add(RolePermissionDTO.builder().rid(dto.getId()).mid(i).build()));
        return roleDao.updateById(BeanCopyUtils.copy(dto, new Role()));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean del(Integer id) {
        rolePermissionService.del(RolePermissionDTO.builder().rid(id).build());
        roleDao.removeById(id);
        return true;
    }
}
