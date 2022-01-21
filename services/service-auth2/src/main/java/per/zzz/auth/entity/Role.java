package per.zzz.auth.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * (t_role)实体类
 *
 * @author 阿忠
 * @since 2022-01-21 16:34:50
 * @description 自动创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_role")
public class Role extends Model<Role> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
	private Integer id;
    /**
     * name
     */
    private String name;
    /**
     * code
     */
    private String code;
    /**
     * createdTime
     */
    private Date createdTime;
    /**
     * modifyTime
     */
    private Date modifyTime;

}