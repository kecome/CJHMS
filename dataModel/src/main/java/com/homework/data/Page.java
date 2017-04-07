package com.homework.data;

import java.util.List;

/**
 * 分页类
 *
 * @author
 * @create 2017-03-31 下午 19:24
 **/
public class Page<T> {
    private long total;
    private List<T> items;

    public long getTotal() {
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

}
