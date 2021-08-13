package com.demo.module.page;


import com.demo.module.util.ServletUtils;
import com.demo.module.util.SqlUtil;
import com.github.pagehelper.PageHelper;

/**
 * <p> @Title PageStart
 * <p> @Description 分页工具
 *
 * @author ACGkaka
 * @date 2021/8/13 8:39
 */
public class PageStart<E> {
    /**
     * 查询参数
     */
    private E params;

    public E getParams() {
        return params;
    }

    public void setParams(E params) {
        this.params = params;
    }

    /**
     * 使用PAGE-HELP方式分页
     */
    public static void startPage()
    {
        PageParam pageParam = buildPageRequest();
        Integer pageNum = pageParam.getPageNum();
        Integer pageSize = pageParam.getPageSize();
        if (null != pageNum && null != pageSize)
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageParam.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    /**
     * 使用PAGE-HELP方式分页
     */
    public static void startPage(PageParam pageParam)
    {
        Integer pageNum = pageParam.getPageNum();
        Integer pageSize = pageParam.getPageSize();
        if (null != pageNum && null != pageSize)
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageParam.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }


    /**
     * 封装分页对象
     */
    private static PageParam buildPageRequest() {
        PageParam pageParam = new PageParam();
        pageParam.setPageNum(ServletUtils.getParameterToInt(PageConstants.PAGE_NUM));
        pageParam.setPageSize(ServletUtils.getParameterToInt(PageConstants.PAGE_SIZE));
        pageParam.setSortField(ServletUtils.getParameter(PageConstants.SORT_FIELD));
        pageParam.setSortOrder(ServletUtils.getParameter(PageConstants.SORT_ORDER));
        return pageParam;
    }

}