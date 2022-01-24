package per.zzz.auth.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import per.zzz.auth.dao.UserDao;
import per.zzz.auth.dto.user.UserAddDTO;
import per.zzz.auth.dto.user.UserDTO;
import per.zzz.auth.dto.user.UserQueryDTO;
import per.zzz.auth.entity.User;
import per.zzz.auth.service.UserService;
import per.zzz.base.utils.BeanCopyUtils;
import per.zzz.base.utils.PageRequest;
import per.zzz.base.utils.QueryWrapperBuilder;
import per.zzz.security.security.TokenService;

import java.util.Collections;
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
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    private final TokenService tokenService;

    private  QueryWrapperBuilder<User> buildQuery(UserQueryDTO queryParam){
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
        copy.setRoles(Collections.singletonList("admin"));
        return copy;
    }

    @Override
    public IPage<User> page(PageRequest<UserQueryDTO> pageRequest) {
        return userDao.page(new Page<>(pageRequest.getPageNumber(), pageRequest.getPageSize()), buildQuery(pageRequest.getQueryParam()));
    }

    @Override
    public List<User> list(UserQueryDTO queryDTO) {
        return userDao.list(buildQuery(queryDTO));
    }

    @Override
    public User findById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public Boolean add(UserAddDTO dto) {
        User user = BeanCopyUtils.copy(dto, new User());
        user.setCreateDate(new Date());
        user.setEnabled(1);
        user.setLocked(0);
        user.setStatus(1);
        return userDao.save(user);
    }

    @Override
    public Boolean update(UserDTO dto) {
        return userDao.updateById(BeanCopyUtils.copy(dto, new User()));
    }
}
