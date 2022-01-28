package per.zzz.auth.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import per.zzz.auth.dto.permission.PermissionDTO;
import per.zzz.auth.dto.role.RoleAddDTO;
import per.zzz.auth.dto.role.RoleDTO;
import per.zzz.auth.dto.role.RoleUpdateDTO;
import per.zzz.auth.entity.Role;
import per.zzz.auth.service.PermissionService;
import per.zzz.auth.service.RoleService;
import per.zzz.base.utils.BeanCopyUtils;
import per.zzz.base.utils.Result;
import per.zzz.mybatis.utils.PageData;
import per.zzz.mybatis.utils.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

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
    private final RoleService roleService;

    private final PermissionService permissionService;


    @PostMapping("/page")
    Result<PageData<RoleDTO>> page(@RequestBody PageRequest<RoleDTO> pageRequest) {
        IPage<Role> pageData = roleService.page(pageRequest);
        return Result.success(PageData.of(pageData.getTotal(), (i) -> {
            RoleDTO copy = BeanCopyUtils.copy(i, new RoleDTO());
            copy.setPermissions( permissionService.listByRoleId(i.getId()));
            return copy;
        }, pageData.getRecords()));
    }

    @PostMapping("/list")
    Result<List<RoleDTO>> list(@RequestBody RoleDTO queryDTO) {
        List<Role> pageData = roleService.list(queryDTO);
        return Result.success(pageData.stream().map(i -> BeanCopyUtils.copy(i, new RoleDTO())).collect(Collectors.toList()));
    }

    @GetMapping("/getById/{id}")
    Result<RoleDTO> getById(@PathVariable("id") Integer id){
        Role byId = roleService.findById(id);
        return Result.success(byId != null ? BeanCopyUtils.copy(byId, new RoleDTO()) : null);
    }

    @PostMapping("/add")
    Result<Boolean> add(@RequestBody RoleAddDTO dto){
        return Result.success(roleService.add(dto));
    }

    @PostMapping("/update")
    Result<Boolean> update(@RequestBody RoleUpdateDTO dto){
        return Result.success(roleService.update(dto));
    }

}
