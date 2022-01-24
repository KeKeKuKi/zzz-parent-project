package per.zzz.auth.dto.role;

import lombok.Data;

import java.util.Date;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/1/24 13:52
 */
@Data
public class RoleDTO {
    private Integer id;
    /**
     * name
     */
    private String name;
    /**
     * code
     */
    private String code;
    /**
     * createdTime
     */
    private Date createdTime;
    /**
     * modifyTime
     */
    private Date modifyTime;
}
