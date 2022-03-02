package per.zzz.jpaTest.service;

import per.zzz.jpaTest.entity.User;

import java.util.List;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/3/2 15:35
 */
public interface UserService {
    List<User> list(User user);
}
