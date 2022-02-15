package per.zzz.blog.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import per.zzz.base.utils.Result;
import per.zzz.oss.common.TencentCosCommonResult;
import per.zzz.oss.utils.CosFileUtils;

import javax.annotation.Resource;

@RestController
@RequestMapping("/oss")
public class OssController {
    @Resource
    private CosFileUtils cosFileUtils;

    @PostMapping("/uploadImage")
    public Result<String> upload(@RequestBody MultipartFile multipartFile) {
        try {
            TencentCosCommonResult tencentOssCommonResult = cosFileUtils.fileUpload(multipartFile);
            return Result.success(tencentOssCommonResult.getResultUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success(null);
    }
}
