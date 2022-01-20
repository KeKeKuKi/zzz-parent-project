package per.zzz.base.utils;

@SuppressWarnings("unused")
public final class Result<T> extends AbsBaseScheme {
    /**
     * 操作结果数据，如果本身返回boolean值，也写在这
     */
    protected T data;

    public Result() {
    }

    /**
     * 成功，EnumCode等于SuccessCode,返回对于的数据
     */
    public Result(T data) {
        this(CommonCode.SUCCESS, data);
    }

    /**
     * 失败，设置对于的EnumCode和数据，数据不是必须的，看业务需要
     */
    public Result(EnumCode enumCode, T data) {
        if (enumCode == null) {
            throw new IllegalArgumentException("EnumCode is null");
        }
        setCode(enumCode.getCode());
        setMsg(enumCode.getMsg());
        this.data = data;
    }

    /**
     * 失败，设置对于的EnumCode和数据，数据不是必须的，看业务需要
     */
    public Result(Exception ex) {
        if (ex == null) {
            throw new IllegalArgumentException("EnumCode is null");
        }
        setCode(CommonCode.EXCEPTION.getCode());
        setMsg(ex.getMessage());
    }

    /**
     * 判断是否业务成功，处理没有抛出异常，并且code为SUCCESS_CODE
     *
     */
    public boolean isCodeSuccess() {
        return CommonCode.SUCCESS.getCode().equals(this.getCode());
    }

    /**
     * 用于类型转换，从一种类型转换为令一种类型
     *
     */
    public <R> Result<R> translate(R r) {
        Result<R> result = new Result<>(r);
        result.setCode(getCode());
        result.setMsg(getMsg());
        return result;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> error(BusinessError error) {
        Result<T> result = new Result<>();
        result.setCode(error.getCode().toString());
        result.setMsg(error.getError());
        return result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
