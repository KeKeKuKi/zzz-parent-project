package per.zzz.auth.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import per.zzz.auth.dto.permission.PermissionDTO;
import per.zzz.auth.entity.Permission;
import per.zzz.auth.service.PermissionService;
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
 * @since 2022-01-21 16:34:49
 * @description 自动创建
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/permission")
public class PermissionController {
    private final PermissionService permissionService;

    @PostMapping("/page")
    Result<PageData<PermissionDTO>> page(@RequestBody PageRequest<PermissionDTO> pageRequest) {
        IPage<Permission> pageData = permissionService.page(pageRequest);
        return Result.success(PageData.of(pageData.getTotal(), (i) -> BeanCopyUtils.copy(i, new PermissionDTO()), pageData.getRecords()));
    }

    @PostMapping("/list")
    Result<List<PermissionDTO>> list(@RequestBody PermissionDTO queryDTO) {
        List<Permission> pageData = permissionService.list(queryDTO);
        return Result.success(pageData.stream().map(i -> BeanCopyUtils.copy(i, new PermissionDTO())).collect(Collectors.toList()));
    }

    @GetMapping("/getById/{id}")
    Result<PermissionDTO> getById(@PathVariable("id") Integer id){
        Permission byId = permissionService.findById(id);
        return Result.success(byId != null ? BeanCopyUtils.copy(byId, new PermissionDTO()) : null);
    }

    @GetMapping("/del/{id}")
    Result<Boolean> del(@PathVariable("id") Integer id){
        return Result.success(permissionService.del(id));
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
