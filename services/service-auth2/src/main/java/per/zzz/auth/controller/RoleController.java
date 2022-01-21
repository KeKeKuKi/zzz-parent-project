package per.zzz.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.zzz.auth.mapper.RoleMapper;

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
@RequestMapping("/role")
public class RoleController {
    private final RoleMapper roleMapper;

}
