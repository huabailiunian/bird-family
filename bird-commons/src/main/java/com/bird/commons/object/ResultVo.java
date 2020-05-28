package com.bird.commons.object;

import com.bird.commons.consts.Const;
import lombok.Data;

/**
 * @author master
 * @date 2020-04-23 13:29
 */
@Data
public class ResultVo<T> {

    private Integer code;

    private String message;

    private T data;

    private ResultVo() {
    }

    public ResultVo(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultVo(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResultVo<T> success(T data) {
        ResultVo<T> result = new ResultVo<>();
        result.code = 200;
        result.message = Const.EMPTY_STRING;
        result.data = data;
        return result;
    }
}
