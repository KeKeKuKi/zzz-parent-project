package per.zzz.blog.entity;

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
 * (me_user)实体类
 *
 * @author 阿忠
 * @since 2021-12-10 15:17:27
 * @description 自动创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("me_user")
public class User extends Model<User> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
	private Long id;
    /**
     * account
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
     * nickname
     */
    private String name;
    /**
     * password
     */
    private String password;

    /**
     * status
     */
    private String status;

}
