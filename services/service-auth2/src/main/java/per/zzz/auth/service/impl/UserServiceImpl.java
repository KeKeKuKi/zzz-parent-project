package per.zzz.auth.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import per.zzz.auth.dao.UserDao;
import per.zzz.auth.dto.UserDTO;
import per.zzz.auth.entity.User;
import per.zzz.auth.service.UserService;
import per.zzz.base.utils.BeanCopyUtils;
import per.zzz.base.utils.QueryWrapperBuilder;
import per.zzz.security.security.TokenService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;

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
    @Resource
    private final UserDao userDao;

    @Resource
    private final TokenService tokenService;

    @Override
    public UserDTO info(String token) {
        String userInfo = tokenService.getUserInfo(token);
        QueryWrapperBuilder<User> eq = QueryWrapperBuilder.<User>wrapper().eq(User::getUsername, userInfo);
        UserDTO copy = BeanCopyUtils.copy(userDao.getOne(eq), new UserDTO());
        copy.setRoles(Collections.singletonList("admin"));
        return copy;
    }
}
