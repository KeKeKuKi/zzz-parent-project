package per.zzz.base.utils;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author @paynyu@51share.com
 * @version 1.0
 * @date 2019/11/14 17:36
 */
public class PageData<T> implements Serializable {
    private Long totalCount;
    private List<T> data;

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PageData{" +
                "totalCount=" + totalCount +
                ", data=" + data +
                '}';
    }

    public static <T, R> PageData<T> of(Long totalCount, Function<R, T> function, List<R> data) {
        PageData<T> pageData = new PageData<>();
        pageData.setTotalCount(totalCount);
        pageData.setData(data.stream().map(function).collect(Collectors.toList()));
        return pageData;
    }

    public static <T, R> PageData<T> ofByList(Long totalCount, Function<List<R>, List<T>> function, List<R> data) {
        PageData<T> pageData = new PageData<>();
        pageData.setTotalCount(totalCount);
        pageData.setData(function.apply(data));
        return pageData;
    }
}
