package org.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
//给UserService类返回数据进行封装
public class UserServiceResult {
    private Integer code;
    private String msg;
    private User data;
}
