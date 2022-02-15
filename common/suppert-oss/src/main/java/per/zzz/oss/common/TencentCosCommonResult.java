package per.zzz.oss.common;

import lombok.Builder;
import lombok.Data;

/**
 * @author TripleZ
 */
@Builder
@Data
public class TencentCosCommonResult {
    private Boolean success;

    private String message;

    private String resultUrl;

    public static TencentCosCommonResult ofSuccess(String resultUrl){
        return TencentCosCommonResult.builder().resultUrl(resultUrl).message("success").success(true).build();
    }

    public static TencentCosCommonResult ofFail(String message){
        return TencentCosCommonResult.builder().resultUrl(null).message(message).success(false).build();
    }
}
