package per.zzz.auth.dto.permission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/1/24 15:05
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDTO {
    private Integer id;
    /**
     * name
     */
    private String name;
    /**
     * path
     */
    private String path;
    /**
     * type
     */
    private Integer type;
    /**
     * value
     */
    private String value;
    /**
     * component
     */
    private String component;
    /**
     * icon
     */
    private String icon;
    /**
     * createdTime
     */
    private Date createdTime;
    /**
     * modifyTime
     */
    private Date modifyTime;
}
