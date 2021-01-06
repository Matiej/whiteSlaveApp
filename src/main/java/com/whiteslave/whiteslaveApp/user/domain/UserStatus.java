package com.whiteslave.whiteslaveApp.user.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserStatus implements Serializable {

    private String status;

    public UserStatus(String status) {
        this.status = status;
    }
}
