package per.zzz.auth.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;
import per.zzz.auth.dto.permission.PermissionDTO;
import per.zzz.auth.dto.role.RoleDTO;
import per.zzz.auth.entity.Permission;
import per.zzz.auth.entity.Role;
import per.zzz.auth.mapper.PermissionMapper;
import per.zzz.auth.service.PermissionService;
import per.zzz.base.utils.BeanCopyUtils;
import per.zzz.base.utils.PageRequest;
import per.zzz.base.utils.Result;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 服务控制器
 *
 * @author 阿忠
 * @since 2022-01-21 16:34:49
 * @description 自动创建
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/permission")
public class PermissionController {
    private PermissionService permissionService;

    @PostMapping("/page")
    Result<PageImpl<PermissionDTO>> page(@RequestBody PageRequest<PermissionDTO> pageRequest) {
        IPage<Permission> pageData = permissionService.page(pageRequest);
        PageImpl<PermissionDTO> page = new PageImpl<>(
                pageData.getRecords().stream().map(i -> BeanCopyUtils.copy(i, new PermissionDTO())).collect(Collectors.toList()),
                pageRequest,
                pageData.getTotal()
        );
        return Result.success(page);
    }

    @PostMapping("/list")
    Result<List<PermissionDTO>> page(@RequestBody PermissionDTO queryDTO) {
        List<Permission> pageData = permissionService.list(queryDTO);
        return Result.success(pageData.stream().map(i -> BeanCopyUtils.copy(i, new PermissionDTO())).collect(Collectors.toList()));
    }

    @GetMapping("/getById/{id}")
    Result<PermissionDTO> getById(@PathVariable("id") Integer id){
        Permission byId = permissionService.findById(id);
        return Result.success(byId != null ? BeanCopyUtils.copy(byId, new PermissionDTO()) : null);
    }

    @PostMapping("/add")
    Result<Boolean> add(@RequestBody PermissionDTO dto){
        return Result.success(permissionService.add(dto));
    }

    @PostMapping("/update")
    Result<Boolean> update(@RequestBody PermissionDTO dto){
        return Result.success(permissionService.update(dto));
    }

}
