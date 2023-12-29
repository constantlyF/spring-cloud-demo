package com.calm.enmus;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CodeEnums {
    SUCCESS("1", "success"),
    ERROR("0", "error");
    public final String code;
    public final String msg;
}
