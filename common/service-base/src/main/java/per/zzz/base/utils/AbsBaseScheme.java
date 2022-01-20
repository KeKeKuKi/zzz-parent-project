package per.zzz.base.utils;

import java.io.Serializable;

/**
 * Time: 2020/6/10
 * 协议基类
 */
public class AbsBaseScheme implements Serializable {
    private static final long serialVersionUID = 1L;

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
