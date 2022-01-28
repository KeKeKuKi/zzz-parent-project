package per.zzz.auth.mapper;

import per.zzz.auth.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (t_role)数据Mapper
 *
 * @author 阿忠
 * @since 2022-01-21 16:34:50
 * @description 自动创建
*/
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> listByUserId(Long userId);
}
