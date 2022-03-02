package per.zzz.jpaTest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.zzz.jpaTest.entity.User;
import per.zzz.jpaTest.service.UserService;

import java.util.List;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/3/2 15:42
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/list")
    List<User> list(){
        return userService.list(new User());
    }
}
