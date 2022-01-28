package per.zzz.auth.dto.rolePermission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/1/25 17:23
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RolePermissionDTO {
    @NotNull
    private Integer rid;

    @NotNull
    private Integer mid;
}
