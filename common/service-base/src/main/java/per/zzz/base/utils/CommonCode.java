package per.zzz.base.utils;

/**
 * To change this template use File | Settings | File Templates.
 */
public enum CommonCode implements EnumCode {

    SUCCESS("成功"),

    FAILURE("失败"),

    NULL_RESULT("查询结果为空"),

    INVALID_PARAM("参数错误"),

    EXCEPTION("系统异常"),

    REPEAT_REQUEST("重复的请求"),

    LOGIN_NAME_EXIST("登录名存在"),

    USER_LOCKED("用户处于锁定状态"),

    HAS_CHILD_RESOURCE("包含子资源节点"),

    THE_FIRST("已经是第一个，不能上移"),

    THE_END("已经是最后一个，不能下移")
    ;
    private String msg;

    private CommonCode(String msg) {
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
