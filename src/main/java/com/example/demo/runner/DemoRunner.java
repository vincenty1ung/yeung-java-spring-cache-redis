package com.example.demo.runner;

import com.example.demo.eo.BookBO;
import com.example.demo.service.IBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author 杨戬
 * @className DemoRunner
 * @email yangb@chaosource.com
 * @date 2019/6/6 11:59
 */
@Slf4j
@Order(15)
@Component
public class DemoRunner implements CommandLineRunner {
    private final static String BOOK_NAME_YI = "一万个理由";
    private final static String BOOK_NAME_SAN = "三万个理由";
    @Autowired
    private IBookService bookService;

    @Override
    public void run(String... args) throws Exception {
        //添加 一万个理由到 缓存
        BookBO bookBOYi = new BookBO(10000L, BOOK_NAME_YI);
        BookBO bookBOSAN= new BookBO(30000L, BOOK_NAME_SAN);

        bookService.add(bookBOYi);

        //获取当前书籍(方法已睡三秒) 当有缓存直接取数据
        BookBO bookBOa = bookService.get(BOOK_NAME_YI);
        log.info("{}", bookBOa);
        //获取当前书籍(方法已睡三秒) 当有缓存直接取数据
        bookBOa = bookService.get(BOOK_NAME_YI);
        log.info("{}", bookBOa);
        //获取当前书籍(方法已睡三秒) 当有缓存直接取数据
        bookBOa = bookService.get(BOOK_NAME_YI);
        log.info("{}", bookBOa);

        //从缓存中删除
        bookService.delete(BOOK_NAME_YI);

        //在此获取 判断是否有延时
        bookBOa = bookService.get(BOOK_NAME_YI);
        log.info("{}", bookBOa);

        //添加 三万个理由到 缓存
        bookService.add(bookBOSAN);

        //获取当前书籍(方法已睡三秒) 当有缓存直接取数据
        BookBO bookBOb = bookService.get(BOOK_NAME_SAN);
        log.info("{}", bookBOb);
        //获取当前书籍(方法已睡三秒) 当有缓存直接取数据
        bookBOb = bookService.get(BOOK_NAME_SAN);
        log.info("{}", bookBOb);
        //获取当前书籍(方法已睡三秒) 当有缓存直接取数据
        bookBOb = bookService.get(BOOK_NAME_SAN);
        log.info("{}", bookBOb);
    }
}