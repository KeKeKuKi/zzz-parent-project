package per.zzz.mybatis.utils;

import com.baomidou.mybatisplus.core.conditions.AbstractLambdaWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Objects;

/**
 * 功能同LambdaWrapper
 * 区别在于该实现对传入的值进行了校验
 * - 字符串isNotBlank
 * - 数组 != null && length > 0
 * - 集合 isNotEmpty
 * - 其他对象 != null
 *
 * @author zzz
 * @since 2021/7/1 9:13 上午
 */
public class QueryWrapperBuilder<T> extends AbstractLambdaWrapper<T, QueryWrapperBuilder<T>> {

    public QueryWrapperBuilder() {
        super.setEntity(null);
        super.initNeed();
    }

    public static <T> QueryWrapperBuilder<T> wrapper() {
        return new QueryWrapperBuilder<>();
    }

    @Override
    protected QueryWrapperBuilder<T> instance() {
        return this;
    }

    @Override
    public QueryWrapperBuilder<T> eq(SFunction<T, ?> column, Object val) {
        if (check(val)) {
            return super.eq(column, val);
        }
        return this;
    }

    @Override
    public QueryWrapperBuilder<T> between(SFunction<T, ?> column, Object val1, Object val2) {
        if (check(val1) && check(val2)) {
            return super.between(column, val1, val2);
        }
        return this;
    }

    @Override
    public QueryWrapperBuilder<T> le(SFunction<T, ?> column, Object val) {
        if (check(val)) {
            return super.le(column, val);
        }
        return this;
    }

    @Override
    public QueryWrapperBuilder<T> lt(SFunction<T, ?> column, Object val) {
        if (check(val)) {
            return super.lt(column, val);
        }
        return this;
    }

    @Override
    public QueryWrapperBuilder<T> gt(SFunction<T, ?> column, Object val) {
        if (check(val)) {
            return super.gt(column, val);
        }
        return this;
    }

    @Override
    public QueryWrapperBuilder<T> ge(SFunction<T, ?> column, Object val) {
        if (check(val)) {
            return super.ge(column, val);
        }
        return this;
    }

    @Override
    public QueryWrapperBuilder<T> like(SFunction<T, ?> column, Object val) {
        if (check(val)) {
            return super.like(column, val);
        }
        return this;
    }

    @Override
    public QueryWrapperBuilder<T> likeLeft(SFunction<T, ?> column, Object val) {
        if (check(val)) {
            return super.likeLeft(column, val);
        }
        return this;
    }

    @Override
    public QueryWrapperBuilder<T> likeRight(SFunction<T, ?> column, Object val) {
        if (check(val)) {
            return super.likeRight(column, val);
        }
        return this;
    }

    @Override
    public QueryWrapperBuilder<T> notLike(SFunction<T, ?> column, Object val) {
        if (check(val)) {
            return super.notLike(column, val);
        }
        return this;
    }

    @Override
    public QueryWrapperBuilder<T> in(SFunction<T, ?> column, Object... values) {
        if (values != null && values.length > 0) {
            return super.in(column, values);
        }
        return this;
    }

    @Override
    public QueryWrapperBuilder<T> in(SFunction<T, ?> column, Collection<?> coll) {
        if (CollectionUtils.isNotEmpty(coll)) {
            return super.in(column, coll);
        }
        return this;
    }

    @Override
    public QueryWrapperBuilder<T> isNull(SFunction<T, ?> column) {
        if (check(column)) {
            return super.isNull(column);
        }
        return this;
    }

    @Override
    public QueryWrapperBuilder<T> isNotNull(SFunction<T, ?> column) {
        if (check(column)) {
            return super.isNotNull(column);
        }
        return this;
    }

    @Override
    public QueryWrapperBuilder<T> notIn(SFunction<T, ?> column, Object... value) {
        if (value != null && value.length > 0) {
            return super.notIn(column, value);
        }
        return this;
    }

    @Override
    public QueryWrapperBuilder<T> notIn(SFunction<T, ?> column, Collection<?> coll) {
        if (CollectionUtils.isNotEmpty(coll)) {
            return super.notIn(column, coll);
        }
        return this;
    }

    @Override
    public QueryWrapperBuilder<T> eq(boolean condition, SFunction<T, ?> column, Object val) {
        if (check(val)) {
            return super.eq(condition, column, val);
        }
        return this;
    }

    private boolean check(Object val) {
        if (val instanceof String) {
            return StringUtils.isNotBlank((String) val);
        }
        return Objects.nonNull(val);
    }
}
