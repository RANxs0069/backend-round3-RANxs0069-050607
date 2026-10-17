package org.example.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
//工具类，用于生成密码散列和判断输入密码的散列是否与保存的散列相同
public class SecurityConfig {
    @Bean//把方法交给Spring保管，遇到需要PasswordEncoder对象的地方自动调用方法注入对象
    public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
