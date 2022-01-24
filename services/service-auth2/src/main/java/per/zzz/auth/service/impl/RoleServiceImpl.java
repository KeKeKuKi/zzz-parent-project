package per.zzz.auth.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import per.zzz.auth.dao.RoleDao;
import per.zzz.auth.dto.role.RoleDTO;
import per.zzz.auth.entity.Role;
import per.zzz.auth.service.RoleService;
import per.zzz.base.utils.BeanCopyUtils;
import per.zzz.base.utils.PageRequest;
import per.zzz.base.utils.QueryWrapperBuilder;

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

    QueryWrapperBuilder<Role> buildQuery(RoleDTO queryParam){
        return QueryWrapperBuilder.<Role>wrapper()
                .eq(Role::getCode, queryParam.getCode())
                .eq(Role::getName, queryParam.getName());
    }

    @Override
    public IPage<Role> page(PageRequest<RoleDTO> pageRequest) {
        return roleDao.page(new Page<>(pageRequest.getPageNumber(), pageRequest.getPageSize()), buildQuery(pageRequest.getQueryParam()));
    }

    @Override
    public List<Role> list(RoleDTO queryDTO) {
        return roleDao.list(buildQuery(queryDTO));
    }

    @Override
    public Role findById(Integer id) {
        return roleDao.getById(id);
    }

    @Override
    public Boolean add(RoleDTO dto) {
        return roleDao.save(BeanCopyUtils.copy(dto, new Role()));
    }

    @Override
    public boolean update(RoleDTO dto) {
        return roleDao.updateById(BeanCopyUtils.copy(dto, new Role()));
    }


}
