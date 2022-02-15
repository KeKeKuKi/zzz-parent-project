package per.zzz.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
@NoArgsConstructor
@Accessors(chain = true)
@TableName("me_article")
public class Article extends Model<Article> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
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

}
