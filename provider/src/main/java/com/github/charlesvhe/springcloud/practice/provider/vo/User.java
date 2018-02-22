package com.github.charlesvhe.springcloud.practice.provider.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by charles on 2017/5/25.
 */
@Data
@NoArgsConstructor
public class User {
    private Long id;
    private String account;
    private String password;

    public User(Long id, String account, String password) {
        this();
        this.id = id;
        this.account = account;
        this.password = password;
    }
}
