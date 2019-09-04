package com.uncle.spring.cache.redis.service.impl;

import com.uncle.spring.cache.redis.eo.BookBO;
import com.uncle.spring.cache.redis.service.IBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author 杨戬
 * @email yangb@chaosource.com
 * @date 2019/6/6 11:56
 */
@Service
@Slf4j
public class BookServiceImpl implements IBookService {
    @Override
    @Cacheable(cacheNames = "txt:books",key = "#name")
    //@Cacheable(cacheNames = {"product1","product2"})// 默认key为参数，多个参数SimpleKey [arg1,arg2]
    //@Cacheable(cacheNames = "product",key = "#root.methodName+'['+#id+']'")
    //@Cacheable(cacheNames = "product",keyGenerator = "myKeyGenerator")
    //@Cacheable(cacheNames = "product",key = "#root.methodName+'['+#id+']'",condition="#a0>10",unless = "#a0==11") //或者condition="#id>10")
    public BookBO get(String name) {
        sleep();
        return new BookBO(16L, name + "无缓存");
    }

    @Override
    @CacheEvict(cacheNames = "txt:books", key = "#name")
    public void delete(String name) {
        log.info("删除当前书籍[{}]", name);
    }

    @Override
    @CachePut(value = "txt:books",key = "#result.name" ,condition = "#result!=null")
    public BookBO add(BookBO bo) {
        log.info("添加当前书籍[{}-添加缓存]", bo);
        return bo;
    }

    private void sleep() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
