package per.zzz.auth.dto.user;

import lombok.Data;

import java.util.Date;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/1/24 10:20
 */
@Data
public class UserQueryDTO {
    private String username;

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

    /**
     * createDate
     */
    private Date createDateStart;

    /**
     * createDate
     */
    private Date createDateEnd;
}
