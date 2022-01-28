package per.zzz.auth.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import per.zzz.auth.dao.UserDao;
import per.zzz.auth.dao.UserRoleDao;
import per.zzz.auth.dto.user.UserAddDTO;
import per.zzz.auth.dto.user.UserDTO;
import per.zzz.auth.dto.user.UserQueryDTO;
import per.zzz.auth.dto.userRole.UserRoleDTO;
import per.zzz.auth.entity.Role;
import per.zzz.auth.entity.User;
import per.zzz.auth.entity.UserRole;
import per.zzz.auth.service.UserRoleService;
import per.zzz.auth.service.UserService;
import per.zzz.base.utils.BeanCopyUtils;
import per.zzz.mybatis.utils.PageRequest;
import per.zzz.mybatis.utils.QueryWrapperBuilder;
import per.zzz.sdr.service.CacheService;
import per.zzz.security.security.TokenService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 服务接口实现
 *
 * @author 阿忠
 * @description 自动创建
 * @since 2022-01-17 10:20:30
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    private final TokenService tokenService;

    private final UserRoleService userRoleService;

    private final CacheService cacheService;

    private QueryWrapperBuilder<User> buildQuery(UserQueryDTO queryParam) {
        return QueryWrapperBuilder.<User>wrapper()
                .eq(User::getUsername, queryParam.getUsername())
                .lt(User::getCreateDate, queryParam.getCreateDateEnd())
                .gt(User::getCreateDate, queryParam.getCreateDateStart())
                .eq(User::getStatus, queryParam.getStatus())
                .eq(User::getMobilePhoneNumber, queryParam.getMobilePhoneNumber())
                .orderByAsc(User::getCreateDate);
    }

    @Override
    public UserDTO info(String token) {
        String userInfo = tokenService.getUserInfo(token);
        QueryWrapperBuilder<User> eq = QueryWrapperBuilder.<User>wrapper().eq(User::getUsername, userInfo);
        UserDTO copy = BeanCopyUtils.copy(userDao.getOne(eq), new UserDTO());
        copy.setRoles(Collections.singletonList(new Role()));
        copy.setPermissions(JSONArray.parseArray(cacheService.hGet("user-permissions", userInfo), String.class));
        return copy;
    }

    @Override
    public IPage<User> page(PageRequest<UserQueryDTO> pageRequest) {
        return userDao.page(new Page<>(pageRequest.getPage(), pageRequest.getSize()), buildQuery(pageRequest.getQueryParam()));
    }

    @Override
    public List<User> list(UserQueryDTO queryDTO) {
        return userDao.list(buildQuery(queryDTO));
    }

    @Override
    public User findById(Long id) {
        return userDao.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean add(UserAddDTO dto) {
        List<User> list = list(UserQueryDTO.builder().username(dto.getUsername()).build());
        Assert.isTrue(CollectionUtils.isEmpty(list), "账户名已存在");
        User user = BeanCopyUtils.copy(dto, new User());
        user.setCreateDate(new Date());
        user.setEnabled(1);
        user.setLocked(0);
        user.setStatus(1);
        userDao.save(user);

        Assert.isTrue(!CollectionUtils.isEmpty(dto.getRoleIds()), "必须设定至少一个角色");
        dto.getRoleIds().forEach(i -> {
            UserRoleDTO userRoleDTO = new UserRoleDTO();
            userRoleDTO.setUid(user.getId().intValue());
            userRoleDTO.setRid(i);
            userRoleService.add(userRoleDTO);
        });
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean update(UserDTO dto) {
        Assert.isTrue(!CollectionUtils.isEmpty(dto.getRoleIds()), "用户至少有一个角色");
        UserRoleDTO userRole = new UserRoleDTO();
        userRole.setUid(dto.getId().intValue());
        userRoleService.del(userRole);

        dto.getRoleIds().forEach(i -> {
            UserRoleDTO item = new UserRoleDTO();
            item.setUid(dto.getId().intValue());
            item.setRid(i);
            userRoleService.add(item);
        });

        return userDao.updateById(BeanCopyUtils.copy(dto, new User()));
    }

    @Override
    public Boolean logout(HttpServletRequest request) {
        String token = request.getHeader("token");

        if(token != null){
            tokenService.removeToken(token);
            String userName = tokenService.getUserInfo(token);
            cacheService.hDel("user-permissions", userName);
        }
        return true;
    }
}
