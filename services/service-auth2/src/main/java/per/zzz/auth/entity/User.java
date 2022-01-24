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
 * (t_user)实体类
 *
 * @author 阿忠
 * @since 2022-01-21 16:34:50
 * @description 自动创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_user")
public class User extends Model<User> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
	private Long id;
    /**
     * username
     */
    private String username;
    /**
     * avatar
     */
    private String avatar;
    /**
     * createDate
     */
    private Date createDate;
    /**
     * email
     */
    private String email;
    /**
     * lastLogin
     */
    private Date lastLogin;
    /**
     * mobilePhoneNumber
     */
    private String mobilePhoneNumber;
    /**
     * name
     */
    private String name;
    /**
     * password
     */
    private String password;
    /**
     * status
     */
    private Integer status;
    /**
     * locked
     */
    private Integer locked;
    /**
     * enabled
     */
    private Integer enabled;

}
