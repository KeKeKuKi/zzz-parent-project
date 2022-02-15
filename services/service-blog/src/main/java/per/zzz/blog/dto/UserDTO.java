package per.zzz.blog.dto;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (me_user)实体类
 *
 * @author 阿忠
 * @since 2021-12-10 15:17:27
 * @description 自动创建
 */
@Data
public class UserDTO extends Model<UserDTO> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
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

    private List<String> roles;

}
