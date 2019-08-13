package com.jtfu.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonValue;
import com.jtfu.myenum.RoleEnum;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author jtfu
 * @since 2019-08-10
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 后期mycat做数据库拆分标记
     */
    private Integer mycatid;

    /**
     * 用户名
     */
    private String name;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色id
     */
    private RoleEnum role;

    /**
     * 删除标记： 1表示删除
     */
    private Integer del;

    /**
     * 创建时间
     */
    @TableField(select=false)
    private LocalDateTime addtime;

    /**
     * 更新时间
     */
    @TableField(select=false)
    private LocalDateTime lasttime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getMycatid() {
        return mycatid;
    }

    public void setMycatid(Integer mycatid) {
        this.mycatid = mycatid;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
    public LocalDateTime getAddtime() {
        return addtime;
    }

    public void setAddtime(LocalDateTime addtime) {
        this.addtime = addtime;
    }
    public LocalDateTime getLasttime() {
        return lasttime;
    }

    public void setLasttime(LocalDateTime lasttime) {
        this.lasttime = lasttime;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", mycatid=" + mycatid +
            ", name=" + name +
            ", username=" + username +
            ", password=" + password +
            ", role=" + role +
            ", del=" + del +
            ", addtime=" + addtime +
            ", lasttime=" + lasttime +
        "}";
    }
}
