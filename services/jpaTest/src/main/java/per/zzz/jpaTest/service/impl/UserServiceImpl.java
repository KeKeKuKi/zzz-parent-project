package per.zzz.jpaTest.service.impl;

import org.springframework.stereotype.Service;
import per.zzz.jpaTest.dao.UserRepository;
import per.zzz.jpaTest.entity.User;
import per.zzz.jpaTest.service.UserService;

import java.util.List;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/3/2 15:35
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> list(User user) {
        return userRepository.findAll();
    }
}
