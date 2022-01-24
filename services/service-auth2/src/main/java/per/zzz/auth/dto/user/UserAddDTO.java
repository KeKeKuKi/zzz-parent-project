package per.zzz.auth.dto.user;

import lombok.Data;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/1/24 13:29
 */
@Data
public class UserAddDTO {

    private String username;

    private String avatar;

    private String email;

    private String mobilePhoneNumber;

    private String name;

    private String password;

}
