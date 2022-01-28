package per.zzz.auth.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import per.zzz.auth.entity.Role;
import per.zzz.auth.mapper.RoleMapper;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (t_role)数据DAO
 *
 * @author 阿忠
 * @since 2022-01-17 10:20:30
 * @description 自动创建
 */
@Slf4j
@Repository
public class RoleDao extends ServiceImpl<RoleMapper, Role> {
    @Resource
    private RoleMapper roleMapper;

    public List<Role> listByUserId(Long userId) {
        return roleMapper.listByUserId(userId);
    }
}
