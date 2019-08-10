package com.jtfu.myenum;

import com.baomidou.mybatisplus.core.enums.IEnum;

public enum  RoleEnum implements IEnum {

    User(1,"普通用户"),Admin(0,"管理员");

    private int value;
    private String label;

    RoleEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}
