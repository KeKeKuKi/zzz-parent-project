package per.zzz.auth.dto.user;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import per.zzz.auth.entity.Role;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (t_user)实体类
 *
 * @author 阿忠
 * @since 2022-01-17 10:20:30
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

    //*******************************************************************

    private List<Role> roles;

    private List<Integer> roleIds;

    private List<String> permissions;

}
