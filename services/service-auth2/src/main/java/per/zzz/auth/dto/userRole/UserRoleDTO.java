package per.zzz.auth.dto.userRole;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/1/25 13:44
 */
@Data
public class UserRoleDTO {

    @NotNull
    private Integer uid;

    @NotNull
    private Integer rid;
}
