package com.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * <p> @Title User
 * <p> @Description 用户实体
 *
 * @author ACGkaka
 * @date 2021/5/21 17:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "my_index")
public class User {

    /**
     * 主键
     * 【@Id注解表明该字段是文档id】
     */
    @Id
    private String id;

    /**
     * 姓名
     * 【注解：表示该字段内容是一个文本并作为一个整体不可分，默认建立索引】
     */
    @Field(type= FieldType.Keyword)
    private String name;

    /**
     * 年龄
     * 【注解：表示该字段内容是一个整数类型并作为一个整体不可分，默认建立】
     */
    @Field(type= FieldType.Integer)
    private Integer age;
}
