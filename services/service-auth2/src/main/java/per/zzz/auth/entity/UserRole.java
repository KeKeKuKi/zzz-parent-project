package per.zzz.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * (t_user_role)实体类
 *
 * @author 阿忠
 * @since 2022-01-17 10:20:30
 * @description 自动创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_user_role")
public class UserRole extends Model<UserRole> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
	private Integer id;
    /**
     * uid
     */
    private Integer uid;
    /**
     * rid
     */
    private Integer rid;
    /**
     * createdTime
     */
    private Date createdTime;
    /**
     * modifyTime
     */
    private Date modifyTime;

}