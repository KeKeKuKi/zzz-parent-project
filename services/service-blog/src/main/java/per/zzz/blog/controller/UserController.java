package per.zzz.blog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import per.zzz.base.utils.Result;
import per.zzz.blog.dto.UserDTO;
import per.zzz.blog.entity.User;
import per.zzz.blog.service.UserService;

import javax.annotation.Resource;

/**
 * 服务控制器
 *
 * @author 阿忠
 * @since 2021-12-10 15:17:27
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController{
    private final UserService userService;

    @PostMapping("login")
    Result<String> login(@RequestBody User user){
        return Result.success(userService.login(user));
    }

    @GetMapping("/info")
    Result<UserDTO> info(){
        return Result.success(userService.info());
    }

}
