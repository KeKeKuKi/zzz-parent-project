package per.zzz.mybatis.utils;

import lombok.Data;


/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/1/24 10:23
 */
@Data
public class PageRequest<T> {
    private static final long serialVersionUID = 1232825578694716871L;

    private int page;
    private int size;
    private String sort;
    private Boolean asc;
    private T queryParam;

}
