package per.zzz.blog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.zzz.base.utils.BeanCopyUtils;
import per.zzz.blog.dao.ArticleBodyDao;
import per.zzz.blog.dao.ArticleDao;
import per.zzz.blog.dao.CategoryDao;
import per.zzz.blog.dto.ArticleBodyDTO;
import per.zzz.blog.dto.ArticleDTO;
import per.zzz.blog.entity.Article;
import per.zzz.blog.entity.ArticleBody;
import per.zzz.blog.service.ArticleService;
import per.zzz.mybatis.utils.QueryWrapperBuilder;

import javax.annotation.Resource;
import java.util.Date;
import java.util.stream.Collectors;

import per.zzz.mybatis.utils.PageRequest;

/**
 * 服务接口实现
 *
 * @author 阿忠
 * @since 2021-12-01 18:42:12
 * @description 自动创建
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private final ArticleDao articleDao;

    @Resource
    private final ArticleBodyDao articleBodyDao;

    @Resource
    private final CategoryDao categoryDao;

    QueryWrapperBuilder<Article> buildQuery(ArticleDTO dto){
        return  QueryWrapperBuilder.<Article>wrapper()
                .eq(Article::getAuthorId, dto.getAuthorId())
                .eq(Article::getCategoryId, dto.getCategoryId())
                .eq(Article::getSummary, dto.getSummary())
                .eq(Article::getWeight, dto.getWeight())
                .eq(Article::getStatus, dto.getStatus())
                .like(Article::getTitle, dto.getWords())
                .like(Article::getSummary, dto.getWords());
    }

    @Override
    public Page<ArticleDTO> page(PageRequest<ArticleDTO> pageRequest) {
        ArticleDTO queryParams = pageRequest.getQueryParam();
        Page<Article> page = new Page<>(pageRequest.getPage(), pageRequest.getSize());
        IPage<Article> page1 = articleDao.page(page, buildQuery(queryParams));
        page.setTotal(page1.getTotal());
        page.setRecords(page1.getRecords());

        Page<ArticleDTO> res = new Page<>(pageRequest.getPage(), pageRequest.getSize());
        res.setTotal(page.getTotal());
        res.setRecords(page.getRecords().stream().map(i -> BeanCopyUtils.copy(i, new ArticleDTO())).collect(Collectors.toList()));
        return res;
    }

    @Override
    public Page<ArticleDTO> pagePutOn(PageRequest<ArticleDTO> pageRequest) {
        pageRequest.getQueryParam().setStatus(1);
        return page(pageRequest);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean del(Integer id) {
        Article byId = articleDao.getById(id);
        Long bodyId = byId.getBodyId();
        // 删除文章
        boolean b = articleDao.removeById(id);

        // 尝试删除唯一内容
        try {
            articleBodyDao.removeById(bodyId);
        }catch (Exception e){
            log.info("外键不得删除！");
        }
        return b;
    }

    @Override
    public ArticleDTO body(Integer id) {
        ArticleDTO articleDTO = BeanCopyUtils.copy(articleDao.getById(id), new ArticleDTO());
        articleDTO.setArticleBodyDTO(BeanCopyUtils.copy(articleBodyDao.getById(articleDTO.getBodyId()), new ArticleBodyDTO()));
        articleDTO.setCategory(categoryDao.getById(articleDTO.getCategoryId()).getCategoryname());
        return articleDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateById(ArticleDTO articleDTO) {
        articleDTO.setStatus(3);
        boolean b = articleBodyDao.updateById(BeanCopyUtils.copy(articleDTO.getArticleBodyDTO(), new ArticleBody()));
        return articleDao.updateById(BeanCopyUtils.copy(articleDTO, new Article())) && b;
    }

    @Override
    public Boolean save(ArticleDTO articleDTO) {
        articleDTO.setStatus(4);
        articleDTO.setCreateDate(new Date());
        articleDTO.setWeight(50);
        articleDTO.setCommentsCount(0);
        articleDTO.setAuthorId(1L);
        articleDTO.setViewCounts(0);
        ArticleBody body = BeanCopyUtils.copy(articleDTO.getArticleBodyDTO(), new ArticleBody());
        boolean b = articleBodyDao.save(body);
        articleDTO.setBodyId(body.getId());
        return articleDao.save(BeanCopyUtils.copy(articleDTO, new Article())) && b;
    }

    @Override
    public Boolean putOn(Integer id) {
        Article byId = articleDao.getById(id);
        byId.setStatus(3);
        return articleDao.updateById(byId);
    }

    @Override
    public Boolean putOff(Integer id) {
        Article byId = articleDao.getById(id);
        byId.setStatus(2);
        return articleDao.updateById(byId);
    }

    @Override
    public Boolean pass(Integer id) {
        Article byId = articleDao.getById(id);
        byId.setStatus(1);
        return articleDao.updateById(byId);
    }

    @Override
    public Boolean refuse(Integer id) {
        Article byId = articleDao.getById(id);
        byId.setStatus(4);
        return articleDao.updateById(byId);
    }

    @Override
    public Boolean submitReview(Integer id) {
        Article byId = articleDao.getById(id);
        byId.setStatus(3);
        return articleDao.updateById(byId);
    }

}
