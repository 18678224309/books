package com.jtfu.myenum;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

public enum  RoleEnum implements IEnum<Integer> {

    User(1,"普通用户"),Admin(0,"管理员");

    private int value;

    private String label;

    RoleEnum(final int value,final String label) {
        this.value = value;
        this.label = label;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }
}
