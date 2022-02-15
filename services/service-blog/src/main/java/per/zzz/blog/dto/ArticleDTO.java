package per.zzz.blog.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import per.zzz.base.utils.TimeUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * (me_article)实体类
 *
 * @author 阿忠
 * @since 2021-12-01 18:42:12
 * @description 自动创建
 */
@Data
public class ArticleDTO extends Model<ArticleDTO> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	private Integer id;

    /**
     * userId
     */
    private Integer userId;

    /**
     * 状态
     */
    private Integer status;

    /**
     * createDate
     */
    @JSONField(format = TimeUtils.FORMAT_NORMAL)
    private Date createDate;
    /**
     * summary
     */
    private String summary;
    /**
     * title
     */
    private String title;
    /**
     * viewCounts
     */
    private Integer viewCounts;
    /**
     * weight
     */
    private Integer weight;
    /**
     * authorId
     */
    private Long authorId;
    /**
     * bodyId
     */
    private Long bodyId;
    /**
     * categoryId
     */
    private Integer categoryId;
    /**
     * 评论数
     */
    private Integer commentsCount;

    /**
     * 横幅图
     */
    private String banner;

    private String words;

    private ArticleBodyDTO articleBodyDTO;

    /**
     * category
     */
    private String category;

}
