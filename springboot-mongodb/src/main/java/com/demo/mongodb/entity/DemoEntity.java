package com.demo.mongodb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * <p> @Title DemoEntity
 * <p> @Description Demo 实体
 *
 * @author ACGkaka
 * @date 2021/4/23 14:16
 */
@Data
@Document(collection = "demo_collection")
public class DemoEntity implements Serializable {

    @Id
    private Long id;

    private String title;

    private String description;
}
