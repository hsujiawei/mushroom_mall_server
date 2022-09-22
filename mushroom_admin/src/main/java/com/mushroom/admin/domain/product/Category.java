package com.mushroom.admin.domain.product;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "admin_product_categories")     // 数据库中只有两种类型的表，以 admin_开头的管理员表、以 consumer 开头的顾客表
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;                 // 分类 ID
    private Integer parentId;           // 父级分类 ID, 0 表示一级分类
    private String name;                // 分类名称
    private byte level;                 // 分类级别: 0 表示一级，1 表示二级
    private int productCount;           // 该分类下所拥有的商品的数量
    private String productUnit;         // 商品单位，例如件、个等
    private byte navStatus;             // 是否显示在导航菜单中，0：不显示、1：显示
    private byte showStatus;            // 显示状态，0：不显示、1：显示
    private byte sort;                  // 排序，数值越大优先级越高，例如 10 的优先级大于 8
    private String icon;                // 图标
    private String keywords;            // 关键字
    private String description;         // 分类描述

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Category category = (Category) o;
        return id != null && Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
// 不推荐在 JPA 的实体中使用 Lombok 的 @Data 注解，可能会导致严重的性能和内存消耗问题
// 多级分类一般使用的都是一张表，然后使用一个 parentId 字段来进行维护
