package org.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//把返回结果包装成实体类，统一返回内容规范
@Data
@NoArgsConstructor//让lombok提供无参构造方法
@AllArgsConstructor//让lombok提供全参构造方法
public class Result<T> {//使用泛型
    private Integer code;
    private String msg;
    private T data;
    //带数据返回成功响应
    public static <E> Result<E> success(E data) {
        return new Result<>(200, "响应成功", data);
    }
    //不带数据返回成功操作
    public static  Result<?> success(String msg) {return new Result<>(200, msg, null);}
    //不带数据返回失败
    public static Result<?> error(Integer code, String msg){
        return new Result<>(code, msg, null);
    }
}
