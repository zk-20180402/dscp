package com.sinohealth.dscp.util;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @Auther: Administrator
 * @Date: 2018/5/23
 * @Description: 分页帮助类
 */
public class PageUtil implements Pageable {

    //页面传递数据
    private String sortAttribute;   //排序列

    //如需要，这里可以新添加需要处理的分页对象

    public PageUtil() {
    }

    @Override
    public int getPageNumber() {
        return 0;
    }

    @Override
    public int getPageSize() {
        return 0;
    }

    @Override
    public int getOffset() {
        return 0;
    }

    @Override
    public Sort getSort() {
        return null;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }
}
