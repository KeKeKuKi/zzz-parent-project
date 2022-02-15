package per.zzz.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import per.zzz.base.utils.Result;
import per.zzz.blog.dto.ArticleDTO;
import per.zzz.blog.service.ArticleService;
import per.zzz.mybatis.utils.PageRequest;

import javax.annotation.Resource;

/**
 * 服务控制器
 *
 * @author 阿忠
 * @since 2021-12-01 18:42:12
 * @description 自动创建
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/article")
public class ArticleController{
    @Resource
    private final ArticleService articleService;

    @PostMapping("/page")
    Result<Page<ArticleDTO>> page(@RequestBody PageRequest<ArticleDTO> pageRequest){
        Page<ArticleDTO> page = articleService.page(pageRequest);
        return Result.success(page);
    }

    @PostMapping("/pagePutOn")
    Result<Page<ArticleDTO>> pagePutOn(@RequestBody PageRequest<ArticleDTO> pageRequest){
        Page<ArticleDTO> page = articleService.pagePutOn(pageRequest);
        return Result.success(page);
    }

    @GetMapping("/body/{id}")
    Result<ArticleDTO> body(@PathVariable("id") Integer id){
        return Result.success(articleService.body(id));
    }

    @PostMapping("/updateById")
    Result<Boolean> updateById(@RequestBody ArticleDTO articleDTO){
        return Result.success(articleService.updateById(articleDTO));
    }

    @PostMapping("/save")
    Result<Boolean> save(@RequestBody ArticleDTO articleDTO){
        return Result.success(articleService.save(articleDTO));
    }

    @GetMapping("/putOn/{id}")
    Result<Boolean> putOn(@PathVariable("id") Integer id){
        return Result.success(articleService.putOn(id));
    }

    @GetMapping("/putOff/{id}")
    Result<Boolean> putOff(@PathVariable("id") Integer id){
        return Result.success(articleService.putOff(id));
    }

    @GetMapping("/pass/{id}")
    Result<Boolean> pass(@PathVariable("id") Integer id){
        return Result.success(articleService.pass(id));
    }

    @GetMapping("/refuse/{id}")
    Result<Boolean> refuse(@PathVariable("id") Integer id){
        return Result.success(articleService.refuse(id));
    }

    @GetMapping("/submitReview/{id}")
    Result<Boolean> submitReview(@PathVariable("id") Integer id){
        return Result.success(articleService.submitReview(id));
    }

    @GetMapping("/del/{id}")
    Result<Boolean> del(@PathVariable("id") Integer id){
        return Result.success(articleService.del(id));
    }

}
