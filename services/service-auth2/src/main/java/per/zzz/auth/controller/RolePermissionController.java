package per.zzz.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.zzz.auth.dto.rolePermission.RolePermissionDTO;
import per.zzz.auth.dto.userRole.UserRoleDTO;
import per.zzz.auth.mapper.RolePermissionMapper;
import per.zzz.auth.service.RolePermissionService;
import per.zzz.base.utils.Result;

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
@RequestMapping("/rolePermission")
public class RolePermissionController {
    private final RolePermissionService rolePermissionService;

    @Validated
    @PostMapping("/add")
    Result<Boolean> add(@RequestBody RolePermissionDTO dto){
        return Result.success(rolePermissionService.add(dto));
    }

    @Validated
    @PostMapping("/del")
    Result<Boolean> del(@RequestBody RolePermissionDTO dto){
        return Result.success(rolePermissionService.del(dto));
    }

}
