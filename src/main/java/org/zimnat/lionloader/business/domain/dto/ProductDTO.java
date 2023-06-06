package org.zimnat.lionloader.business.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zimnat.lionloader.business.domain.Category;

/**
 * @author :: codemaster
 * created on :: 23/5/2023
 * Package Name :: org.zimnat.lionloader.business.domain.dto
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private String name;

    private String description;

    private double price;

    private String categoryId;

    private Category category;

    private Boolean active;
}
