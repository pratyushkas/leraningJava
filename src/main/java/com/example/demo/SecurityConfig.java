package com.example.demo;

import java.time.LocalDateTime;
import java.util.Date;

public enum SecurityConfig {
    SEC_MAP("name","protocal"),
    SAM_DATE("LocalDateTime", LocalDateTime.now().toString())
    ;

    public String getProtocal() {
        return protocal;
    }

    public String getName() {
        return name;
    }

    private final String name;
    private final String protocal;
    SecurityConfig(String name, String protocal) {
        this.name = name;
        this.protocal = protocal;
    }
}
