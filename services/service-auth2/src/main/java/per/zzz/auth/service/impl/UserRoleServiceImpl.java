package per.zzz.auth.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import per.zzz.auth.dao.UserRoleDao;
import per.zzz.auth.dto.userRole.UserRoleDTO;
import per.zzz.auth.entity.UserRole;
import per.zzz.auth.service.UserRoleService;
import per.zzz.base.utils.BeanCopyUtils;
import per.zzz.mybatis.utils.QueryWrapperBuilder;

import javax.annotation.Resource;
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
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    private final UserRoleDao userRoleDao;

    QueryWrapperBuilder<UserRole> buildQuery(UserRoleDTO dto){
        return QueryWrapperBuilder.<UserRole>wrapper()
                .eq(UserRole::getRid, dto.getRid())
                .eq(UserRole::getUid, dto.getUid());
    }


    @Override
    public Boolean add(UserRoleDTO dto) {
        List<UserRole> list = list(dto);
        Assert.isTrue(CollectionUtils.isEmpty(list), "该用户已有该角色");
        UserRole userRole = BeanCopyUtils.copy(dto, new UserRole());
        userRole.setCreatedTime(new Date());
        userRole.setModifyTime(new Date());
        return userRoleDao.save(userRole);
    }

    public List<UserRole> list(UserRoleDTO dto){
        return userRoleDao.list(buildQuery(dto));
    }

    @Override
    public Boolean del(UserRoleDTO dto) {
        List<UserRole> list = list(dto);
        if(CollectionUtils.isEmpty(list)){
            return true;
        }
        list.forEach(userRoleDao::removeById);
        return true;
    }
}
