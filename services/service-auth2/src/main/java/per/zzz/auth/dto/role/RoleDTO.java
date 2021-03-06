package per.zzz.auth.dto.role;

import lombok.Data;
import per.zzz.auth.entity.Permission;

import java.util.Date;
import java.util.List;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/1/24 13:52
 */
@Data
public class RoleDTO {
    private Integer id;

    private String name;

    private String code;

    private Date createdTime;

    private Date modifyTime;

    private List<Permission> permissions;

}
