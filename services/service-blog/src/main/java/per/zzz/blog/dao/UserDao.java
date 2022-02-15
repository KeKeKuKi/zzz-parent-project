package per.zzz.blog.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import per.zzz.blog.entity.User;
import per.zzz.blog.mapper.UserMapper;

/**
 * (me_user)数据DAO
 *
 * @author 阿忠
 * @since 2021-12-10 15:17:27
 * @description 自动创建
 */
@Slf4j
@Repository
public class UserDao extends ServiceImpl<UserMapper, User> {

}
