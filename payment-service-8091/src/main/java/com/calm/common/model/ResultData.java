package com.calm.common.model;

import com.calm.enmus.CodeEnums;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@JsonPropertyOrder({"code", "message", "data"})
@Data(staticConstructor = "of")
@Accessors(chain = true)
@AllArgsConstructor(staticName = "of")
public class ResultData<T> implements Serializable {
    private String code;
    private String msg;
    private T data;

    public static <T> ResultData<T> success(T data, String msg) {
        return ResultData.of(CodeEnums.SUCCESS.code, msg, data);
    }

    public static <T> ResultData<T> success(T data) {
        return ResultData.of(CodeEnums.SUCCESS.code, CodeEnums.SUCCESS.msg, data);
    }

    public static <T> ResultData<T> success(String msg) {
        return ResultData.of(CodeEnums.SUCCESS.code, msg, null);
    }

    public static <T> ResultData<T> success() {
        return ResultData.of(CodeEnums.SUCCESS.code, CodeEnums.SUCCESS.msg, null);
    }


    public static <T> ResultData<T> error(T data, String msg) {
        return ResultData.of(CodeEnums.ERROR.code, msg, data);
    }

    public static <T> ResultData<T> error(T data) {
        return ResultData.of(CodeEnums.ERROR.code, CodeEnums.ERROR.msg, data);
    }

    public static <T> ResultData<T> error(String msg) {
        return ResultData.of(CodeEnums.ERROR.code, msg, null);
    }

    public static <T> ResultData<T> error() {
        return ResultData.of(CodeEnums.ERROR.code, CodeEnums.ERROR.msg, null);
    }
}
