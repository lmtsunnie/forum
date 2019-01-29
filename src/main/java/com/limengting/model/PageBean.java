package com.limengting.model;
import lombok.Data;

import java.util.List;

@Data
public class PageBean<T> {

    private int allPage;
    private int curPage;

    private List<T> list;

    public PageBean() {}

    public PageBean(int allPage, int curPage) {
        this.allPage = allPage;
        this.curPage = curPage;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "allPage=" + allPage +
                ", curPage=" + curPage +
                ", list=" + list +
                '}';
    }
}

