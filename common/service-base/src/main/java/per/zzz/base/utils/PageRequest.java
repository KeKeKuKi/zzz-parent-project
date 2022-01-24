package per.zzz.base.utils;

import lombok.Data;
import org.springframework.data.domain.Sort;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/1/24 10:23
 */
public class PageRequest<T> extends org.springframework.data.domain.PageRequest {

    private T queryParam;

    public PageRequest(){
        super(1, 10, Sort.unsorted());
    }

    public T getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(T queryParam) {
        this.queryParam = queryParam;
    }
}
