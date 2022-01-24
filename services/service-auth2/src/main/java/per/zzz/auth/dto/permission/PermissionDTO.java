package per.zzz.auth.dto.permission;

import lombok.Data;

import java.util.Date;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/1/24 15:05
 */
@Data
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
