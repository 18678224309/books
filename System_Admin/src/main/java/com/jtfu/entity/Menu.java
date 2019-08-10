package com.jtfu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author jtfu
 * @since 2019-08-10
 */
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String menuname;

    private Integer parentid;

    private Integer del;

    /**
     * 0:菜单 1:按钮 
     */
    private Integer type;

    /**
     * 权限标记
     */
    private String perm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }
    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }
    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public String getPerm() {
        return perm;
    }

    public void setPerm(String perm) {
        this.perm = perm;
    }

    @Override
    public String toString() {
        return "Menu{" +
            "id=" + id +
            ", menuname=" + menuname +
            ", parentid=" + parentid +
            ", del=" + del +
            ", type=" + type +
            ", perm=" + perm +
        "}";
    }
}
