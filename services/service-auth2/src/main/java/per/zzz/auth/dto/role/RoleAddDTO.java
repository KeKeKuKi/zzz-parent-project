package per.zzz.auth.dto.role;

import lombok.Data;
import per.zzz.auth.entity.Permission;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/1/27 13:42
 */
@Data
public class RoleAddDTO {
    @NotNull
    private String name;

    @NotNull
    private String code;

    private List<Integer> permissionIds;
}
