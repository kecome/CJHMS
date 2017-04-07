package com.homework.param;

import java.util.Map;

/**
 * 查询参数基础信息类
 *
 * @author xuke
 * @create 2017-04-06 下午 14:18
 **/
public class BaseParam {
    /**
     * 记录开始位置
     */
    private int offset = 1;
    /**
     * 查询记录数
     */
    private int limit = 10;
    /**
     *排序
     */
    private Map<String, String> order;

    /**
     * 是否分页
     */
    private boolean isPage;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public boolean getIsPage() {
        return isPage;
    }

    public void setIsPage(boolean page) {
        isPage = page;
    }

    public Map<String, String> getOrder() {
        return order;
    }

    public void setOrder(Map<String, String> order) {
        this.order = order;
    }

}
