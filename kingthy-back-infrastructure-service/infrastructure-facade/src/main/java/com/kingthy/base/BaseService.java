package com.kingthy.base;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 17:04 on 2017/8/2.
 * @Modified by:
 */
public interface BaseService<T>{

    int insert(T t);

    int updateByUuid(T t);

    List<T> selectAll();

    T selectByUuid(String uuid);

    int selectCount(T t);

    List<T> select(T var1);

    T selectOne(T var1);

    PageInfo<T> queryPage(Integer pageNum, Integer pageSize, T t);
}
