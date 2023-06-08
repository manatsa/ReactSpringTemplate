package org.zimnat.lionloader.business.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

/**
 * @author :: codemaster
 * created on :: 23/5/2023
 * Package Name :: org.zimnat.lionloader.business.domain
 */

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity{

    private String name;

    private String description;

    private double price;

    private String tags;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
}
