package com.homework.data;

import java.util.List;

/**
 * 分页类
 *
 * @author
 * @create 2017-03-31 下午 19:24
 **/
public class Page<T> {
    private Integer total;
    private List<T> items;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
