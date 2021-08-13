package com.demo.module.page;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


/**
 * 分页实体类
 *
 * @author ACGkaka
 * @since 2021-06-18 16:49:55
 */
@Data
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = -275582248840137389L;

    /**
     * 总数
     */
    private Long count;

    /**
     * 状态码
     */
    private int code;

    /**
     * 当前页结果集
     */
    private List<T> data;

    public PageResult(Long count, List<T> data) {
        this.count = count;
        this.data = data;
        this.code = 200;
    }

    public PageResult(PageInfo<T> pageInfo) {
        this.count = pageInfo.getTotal();
        this.data = pageInfo.getList();
        this.code = 200;
    }
}
