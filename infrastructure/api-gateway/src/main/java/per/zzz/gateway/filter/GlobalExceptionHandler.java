package per.zzz.gateway.filter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import per.zzz.base.utils.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/1/26 13:22
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler
    public Result<Object> exceptionHandler(HttpServletRequest req, Exception e) {
        return Result.fail(e.getMessage());
    }
}
