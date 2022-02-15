package per.zzz.oss.utils;


import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectResult;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import per.zzz.oss.common.TencentCosCommonResult;
import per.zzz.oss.config.TencentCosConfig;


import java.io.IOException;
import java.io.InputStream;

/**
 * date 2021/10/28 11:46
 *
 * @author 阿忠 2669918628@qq.com
 */
@AllArgsConstructor
public class CosFileUtils {

    private final TencentCosConfig tencentCosConfig;

    private final COSClient cosClient;

    // 文件流上传
    public TencentCosCommonResult fileUpload(InputStream inputStream, String fileName, Long size) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(size);
        fileName = System.currentTimeMillis() + fileName;
        PutObjectResult putObjectResult =
                cosClient.putObject(tencentCosConfig.getBucket(), fileName, inputStream, objectMetadata);
        String resUrl = cosClient.getClientConfig().getHttpProtocol().name()
                + "://"
                + tencentCosConfig.getBucket()
                + ".cos."
                + cosClient.getClientConfig().getRegion().getRegionName()
                + ".myqcloud.com/"
                + fileName;
        return !StringUtils.isEmpty(putObjectResult.getETag()) ?
                TencentCosCommonResult.ofSuccess(resUrl) : TencentCosCommonResult.ofFail(resUrl);
    }

    // WEB文件上传
    public TencentCosCommonResult fileUpload(MultipartFile multipartFile) throws IOException {
        return fileUpload(multipartFile.getInputStream(), multipartFile.getOriginalFilename(), multipartFile.getSize());
    }
}
