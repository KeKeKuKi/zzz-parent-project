package per.zzz.blog.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import per.zzz.blog.dao.UserDao;
import per.zzz.blog.dto.UserDTO;
import per.zzz.blog.entity.User;
import per.zzz.blog.service.UserService;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * 服务接口实现
 *
 * @author 阿忠
 * @since 2021-12-10 15:17:27
 * @description 自动创建
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private final UserDao userDao;

    @Override
    public String login(User user) {
        if("admin".equals(user.getUsername()) && "166056".equals(user.getPassword())){
            return "zzz-tocken";
        }
        return null;
    }

    @Override
    public UserDTO info() {
        UserDTO user = new UserDTO();
        user.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        user.setName("Admin");
        user.setRoles(Collections.singletonList("admin"));
        return user;
    }
}
