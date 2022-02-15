package per.zzz.blog.service;


import per.zzz.blog.dto.UserDTO;
import per.zzz.blog.entity.User;

/**
 * 服务接口
 *
 * @author 阿忠
 * @since 2021-12-10 15:17:27
 * @description 自动创建
 */
public interface UserService {

    String login(User user);

    UserDTO info();
}
