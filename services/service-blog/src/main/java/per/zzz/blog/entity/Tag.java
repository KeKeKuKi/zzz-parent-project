package per.zzz.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (me_tag)实体类
 *
 * @author 阿忠
 * @since 2021-12-01 18:42:12
 * @description 自动创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("me_tag")
public class Tag extends Model<Tag> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
	private Integer id;
    /**
     * avatar
     */
    private String avatar;
    /**
     * tagname
     */
    private String tagname;

}
