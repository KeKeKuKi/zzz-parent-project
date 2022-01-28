package per.zzz.auth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import per.zzz.auth.dto.user.UserAddDTO;
import per.zzz.auth.dto.user.UserDTO;
import per.zzz.auth.dto.user.UserQueryDTO;
import per.zzz.auth.entity.User;
import per.zzz.mybatis.utils.PageRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 服务接口
 *
 * @author 阿忠
 * @since 2022-01-17 10:20:30
 * @description 自动创建
 */
public interface UserService {

    UserDTO info(String token);

    IPage<User> page(PageRequest<UserQueryDTO> pageRequest);

    List<User> list(UserQueryDTO queryDTO);

    User findById(Long id);

    Boolean add(UserAddDTO dto);

    Boolean update(UserDTO dto);

    Boolean logout(HttpServletRequest request);
}
