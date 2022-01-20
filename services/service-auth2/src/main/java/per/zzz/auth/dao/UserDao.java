package per.zzz.auth.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import per.zzz.auth.entity.User;
import per.zzz.auth.mapper.UserMapper;

/**
 * (t_user)数据DAO
 *
 * @author 阿忠
 * @since 2022-01-17 10:20:30
 * @description 自动创建
 */
@Slf4j
@Repository
public class UserDao extends ServiceImpl<UserMapper, User> {

}