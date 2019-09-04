package com.uncle.spring.cache.redis.eo;

import lombok.*;

import java.io.Serializable;

/**
 * @author 杨戬
 * @email yangb@chaosource.com
 * @date 2019/6/6 11:52
 */

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookBO implements Serializable {
    /**
     * 总页数
     */
    private Long totalPage;
    /**
     * 书名
     */
    private String name;

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
