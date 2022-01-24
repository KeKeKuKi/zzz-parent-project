package per.zzz.auth.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;
import per.zzz.auth.dto.user.UserAddDTO;
import per.zzz.auth.dto.user.UserDTO;
import per.zzz.auth.dto.user.UserQueryDTO;
import per.zzz.auth.entity.User;
import per.zzz.auth.service.UserService;
import per.zzz.base.utils.BeanCopyUtils;
import per.zzz.base.utils.PageData;
import per.zzz.base.utils.PageRequest;
import per.zzz.base.utils.Result;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 服务控制器
 *
 * @author 阿忠
 * @description 自动创建
 * @since 2022-01-21 16:34:50
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/info")
    public Result<UserDTO> info(String token) {
        return Result.success(userService.info(token));
    }

    @PostMapping("/page")
    Result<PageData<UserDTO>> page(@RequestBody PageRequest<UserQueryDTO> pageRequest) {
        IPage<User> pageData = userService.page(pageRequest);
        PageData<UserDTO> pageData1 = new PageData<>();
        pageData1.setData(pageData.getRecords().stream().map(i -> BeanCopyUtils.copy(i, new UserDTO())).collect(Collectors.toList()));
        pageData1.setTotalCount(pageData.getTotal());
        return Result.success(pageData1);
    }

    @PostMapping("/list")
    Result<List<UserDTO>> page(@RequestBody UserQueryDTO queryDTO) {
        List<User> pageData = userService.list(queryDTO);
        return Result.success(pageData.stream().map(i -> BeanCopyUtils.copy(i, new UserDTO())).collect(Collectors.toList()));
    }

    @GetMapping("/getById/{id}")
    Result<UserDTO> getById(@PathVariable("id") Long id){
        User byId = userService.findById(id);
        return Result.success(byId != null ? BeanCopyUtils.copy(byId, new UserDTO()) : null);
    }

    @PostMapping("/add")
    Result<Boolean> add(@RequestBody UserAddDTO dto){
        return Result.success(userService.add(dto));
    }

    @PostMapping("/update")
    Result<Boolean> update(@RequestBody UserDTO dto){
        return Result.success(userService.update(dto));
    }

}
