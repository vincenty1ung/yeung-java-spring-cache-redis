package com.example.demo.service;

import com.example.demo.eo.BookBO;

/**
 * @author 杨戬
 * @className IBookService
 * @email yangb@chaosource.com
 * @date 2019/6/6 11:54
 */
public interface IBookService {
    /**
     * 根据书名获取书籍信息
     *
     * @param name 书名
     * @return
     */
    BookBO get(String name);

    /**
     * 根据书名进行删除
     *
     * @param name 书名
     */
    void delete(String name);

    /**
     * 添加书籍
     *
     * @param bookBO 书名
     */
    BookBO add(BookBO bookBO);
}
