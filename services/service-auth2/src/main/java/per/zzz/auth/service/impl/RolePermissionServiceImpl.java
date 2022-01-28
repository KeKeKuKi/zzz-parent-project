package per.zzz.auth.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import per.zzz.auth.dao.RolePermissionDao;
import per.zzz.auth.dto.rolePermission.RolePermissionDTO;
import per.zzz.auth.entity.RolePermission;
import per.zzz.auth.service.RolePermissionService;
import per.zzz.base.utils.BeanCopyUtils;
import per.zzz.mybatis.utils.QueryWrapperBuilder;

import java.util.Date;
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
public class RolePermissionServiceImpl implements RolePermissionService {
    private final RolePermissionDao rolePermissionDao;

    QueryWrapperBuilder<RolePermission> buildQuery(RolePermissionDTO dto){
        return QueryWrapperBuilder.<RolePermission>wrapper()
                .eq(RolePermission::getRid, dto.getRid())
                .eq(RolePermission::getMid, dto.getMid());
    }

    @Override
    public Boolean add(RolePermissionDTO dto) {
        List<RolePermission> list = list(dto);
        if(!CollectionUtils.isEmpty(list)){
            return true;
        }
        RolePermission rolePermission = BeanCopyUtils.copy(dto, new RolePermission());
        rolePermission.setCreatedTime(new Date());
        rolePermission.setModifyTime(new Date());
        return rolePermissionDao.save(rolePermission);
    }

    @Override
    public List<RolePermission> list(RolePermissionDTO dto){
        return rolePermissionDao.list(buildQuery(dto));
    }

    @Override
    public Boolean del(RolePermissionDTO dto) {
        List<RolePermission> list = list(dto);
        list.forEach(rolePermissionDao::removeById);
        return true;
    }
}
