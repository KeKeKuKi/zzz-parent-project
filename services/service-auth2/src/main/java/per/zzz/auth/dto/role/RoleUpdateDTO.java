package per.zzz.auth.dto.role;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/1/27 13:42
 */
@Data
public class RoleUpdateDTO {
    @NotNull
    private Integer id;

    private String name;

    private String code;

    private List<Integer> permissionIds;
}
