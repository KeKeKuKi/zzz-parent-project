package per.zzz.blog.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import per.zzz.mybatis.utils.PageRequest;
import per.zzz.blog.dto.ArticleDTO;

/**
 * 服务接口
 *
 * @author 阿忠
 * @since 2021-12-01 18:42:12
 */
public interface ArticleService {

    Page<ArticleDTO> page(PageRequest<ArticleDTO> pageRequest);

    ArticleDTO body(Integer id);

    Boolean updateById(ArticleDTO articleDTO);

    Boolean save(ArticleDTO articleDTO);

    Boolean putOn(Integer id);

    Boolean putOff(Integer id);

    Boolean pass(Integer id);

    Boolean refuse(Integer id);

    Boolean submitReview(Integer id);

    Page<ArticleDTO> pagePutOn(PageRequest<ArticleDTO> pageRequest);

    Boolean del(Integer id);
}
