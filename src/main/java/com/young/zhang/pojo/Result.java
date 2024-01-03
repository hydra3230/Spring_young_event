package com.young.zhang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.servlet.tags.EditorAwareTag;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    //快速返回操作成功相应结果
    public static <E> Result<E> success(E data) {
        return new Result<>(0, "操作成功", data);
    }

    public static Result success() {
        return new Result<>(0, "操作成功", null);
    }

    public static Result erro(String message) {
        return new Result<>(1, message, null);
    }


}
