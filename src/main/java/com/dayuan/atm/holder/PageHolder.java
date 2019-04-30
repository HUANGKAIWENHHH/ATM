package com.dayuan.atm.holder;

public class PageHolder<T> {

    //返回行数
    public static final int PAGENUM = 5;
    //现在所处的页数
    private int currentPage;
    //总页数
    private int totalPage;
    //总条数
    private int totalDatas;

    //数据对象
    private T data;

    public PageHolder(int totalDatas, int currentPage) {
        this.totalDatas = totalDatas;
        this.currentPage = currentPage;
    }

    public static PageHolder build(int currentPage, int totalDatas) {
        PageHolder pageHolder = new PageHolder(totalDatas, currentPage);
        pageHolder.totalPage = totalDatas % PAGENUM == 0 ? totalDatas / PAGENUM : totalDatas / PAGENUM + 1;

        return pageHolder;
    }

    //计算偏移量，索引，从第几条数据开始返回
    public int offset() {
        return (currentPage - 1) * PAGENUM;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getTotalPage() {
        return totalPage;
    }
}
