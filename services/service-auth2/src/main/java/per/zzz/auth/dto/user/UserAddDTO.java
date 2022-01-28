package per.zzz.auth.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/1/24 13:29
 */
@Data
public class UserAddDTO {

    @NotNull
    private String username;

    private String avatar;

    private String email;

    private String mobilePhoneNumber;

    private String name;

    @NotNull
    private String password;

    private List<Integer> roleIds;

}
