package per.zzz.blog.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.Getter;

@Getter
public enum ArticleStatus implements IEnum<Integer> {
    RELEASING(1, "发布中"),
    OFF_SHELF(2, "下架"),
    REVIEWING(3, "审核中"),
    SAVING(4, "暂存");

    private final Integer value;
    private final String desc;

    ArticleStatus(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
