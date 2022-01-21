package per.zzz.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.zzz.auth.dto.UserDTO;
import per.zzz.auth.mapper.UserMapper;
import per.zzz.auth.service.UserService;
import per.zzz.base.utils.Result;

import javax.annotation.Resource;

/**
 * 服务控制器
 *
 * @author 阿忠
 * @since 2022-01-21 16:34:50
 * @description 自动创建
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private final UserService userService;

    @GetMapping("/info")
    public Result<UserDTO> info(String token){
        return Result.success(userService.info(token));
    }

}
