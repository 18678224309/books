package com.jtfu.gen.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 为方便layui加载数据
 */
public class LayUiObj {
    /*{
        "code": 0,
            "msg": "",
            "count": 1000,
            "data": [{
        "id": 10000,
                "username": "user-0",
                "sex": "女",
                "city": "城市-0",
                "sign": "签名-0",
                "experience": 255,
                "logins": 24,
                "wealth": 82830700,
                "classify": "作家",
                "score": 57
    }]
    }*/
    private int code;
    private String msg="";
    private int count;
    private List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }
}
