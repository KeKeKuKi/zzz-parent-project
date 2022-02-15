package per.zzz.blog.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import per.zzz.blog.dao.CategoryDao;
import per.zzz.blog.service.CategoryService;

import javax.annotation.Resource;

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
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private final CategoryDao categoryDao;

}
