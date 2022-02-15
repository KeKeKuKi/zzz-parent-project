package per.zzz.blog.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import per.zzz.blog.entity.Tag;
import per.zzz.blog.mapper.TagMapper;

/**
 * (me_tag)数据DAO
 *
 * @author 阿忠
 * @since 2021-12-01 18:42:12
 * @description 自动创建
 */
@Slf4j
@Repository
public class TagDao extends ServiceImpl<TagMapper, Tag> {

}
