package com.demo.module.page;

import com.demo.module.util.StringUtils;
import lombok.Data;

/**
 * 分页数据
 *
 * @author ACGkaka
 */
@Data
public class PageParam {

    /**
     * 当前页码
     */
    private Integer pageNum;

    /**
     * 每页显示记录数
     */
    private Integer pageSize;

    /**
     * 排序列
     */
    private String sortField;

    /**
     * 排序类型 "desc" 或者 "asc".
     */
    private String sortOrder;

    /**
     * 获取排序字段
     * 
     * @return 排序字段
     */
    public String getOrderBy() {
        if (sortField == null || sortField.length() == 0) {
            return "";
        }
        return StringUtils.toUnderScoreCase(sortField) + " " + sortOrder;
    }

}
