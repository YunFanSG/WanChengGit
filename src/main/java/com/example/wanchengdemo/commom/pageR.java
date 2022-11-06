package com.example.wanchengdemo.commom;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public class pageR {

    private long current = 1L;
    private long size = 10L;

    public pageR() {
    }
    public pageR(long current, long size) {
        this.current = current;
        this.size = size;
    }

    public long getCurrent() {
        return this.current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public <T> Page<T> toPage() {
        return new Page(this.current, this.size);
    }

    public <T> Page<T> toPage(Class<T> clazz) {
        return new Page(this.current, this.size);
    }

    public String toString() {
        return "PageEntity{current=" + this.current + ", size=" + this.size + '}';
    }










}
