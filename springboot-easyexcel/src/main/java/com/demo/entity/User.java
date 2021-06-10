package com.demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.demo.config.LocalDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * <p> @Title User
 * <p> @Description 用户实体
 *
 * @author zhj
 * @date 2021/4/25 17:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
// 表头高度设置为20
@HeadRowHeight(20)
// 表头字体大小设置为12
@HeadFontStyle(fontHeightInPoints = 12)
public class User {

    /** 用户名. */
    @ColumnWidth(20)
    @ExcelProperty("用户名")
    private String username;

    /**
     * 密码
     *
     * index = 1 强制读取第2个，如果value和index同时设置，value不生效，index生效
     * 注意：导入时必须将单元格格式设置为文本格式
     */
    @ColumnWidth(20)
    @ExcelProperty(value = "密码", index = 1)
    private String password;

    /**
     * 创建时间
     */
    @ColumnWidth(25)
    @ExcelProperty(value="创建时间", converter = LocalDateTimeConverter.class)
    private LocalDateTime createTime;

}

