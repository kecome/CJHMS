package com.homework.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * model公共属性类
 *
 * @author xuke
 * @create 2017-04-06 下午 18:10
 **/
public class BaseModel {
    /**
     * id号
     */
    private Long id;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date created;
    /**
     * 修改时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
