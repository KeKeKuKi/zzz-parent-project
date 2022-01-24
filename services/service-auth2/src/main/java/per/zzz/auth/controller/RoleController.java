package per.zzz.auth.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;
import per.zzz.auth.dto.role.RoleDTO;
import per.zzz.auth.entity.Role;
import per.zzz.auth.service.RoleService;
import per.zzz.base.utils.BeanCopyUtils;
import per.zzz.base.utils.PageRequest;
import per.zzz.base.utils.Result;

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


    @PostMapping("/page")
    Result<PageImpl<RoleDTO>> page(@RequestBody PageRequest<RoleDTO> pageRequest) {
        IPage<Role> pageData = roleService.page(pageRequest);
        PageImpl<RoleDTO> page = new PageImpl<>(
                pageData.getRecords().stream().map(i -> BeanCopyUtils.copy(i, new RoleDTO())).collect(Collectors.toList()),
                pageRequest,
                pageData.getTotal()
        );
        return Result.success(page);
    }

    @PostMapping("/list")
    Result<List<RoleDTO>> page(@RequestBody RoleDTO queryDTO) {
        List<Role> pageData = roleService.list(queryDTO);
        return Result.success(pageData.stream().map(i -> BeanCopyUtils.copy(i, new RoleDTO())).collect(Collectors.toList()));
    }

    @GetMapping("/getById/{id}")
    Result<RoleDTO> getById(@PathVariable("id") Integer id){
        Role byId = roleService.findById(id);
        return Result.success(byId != null ? BeanCopyUtils.copy(byId, new RoleDTO()) : null);
    }

    @PostMapping("/add")
    Result<Boolean> add(@RequestBody RoleDTO dto){
        return Result.success(roleService.add(dto));
    }

    @PostMapping("/update")
    Result<Boolean> update(@RequestBody RoleDTO dto){
        return Result.success(roleService.update(dto));
    }

}
