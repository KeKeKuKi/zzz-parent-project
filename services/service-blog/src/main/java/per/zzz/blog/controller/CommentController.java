package per.zzz.blog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.zzz.blog.service.CommentService;

import javax.annotation.Resource;

/**
 * 服务控制器
 *
 * @author 阿忠
 * @since 2021-12-01 18:42:12
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private final CommentService commentService;

}
