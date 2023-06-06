package org.zimnat.lionloader.business.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zimnat.lionloader.business.domain.Category;
import org.zimnat.lionloader.business.domain.Industry;
import org.zimnat.lionloader.business.domain.Product;
import org.zimnat.lionloader.business.domain.User;
import org.zimnat.lionloader.business.domain.dto.ProductDTO;
import org.zimnat.lionloader.business.repos.ProductRepo;
import org.zimnat.lionloader.business.services.ProductService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author :: codemaster
 * created on :: 23/5/2023
 * Package Name :: org.zimnat.lionloader.business.services.impl
 */


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Transactional
    @Override
    public Product save(Product product, User user) {
        if(product!=null){
            product.setId(UUID.randomUUID().toString());
            product.setCreatedBy(user);
            product.setDateCreated(new Date());

            return productRepo.save(product);
        }
        return null;
    }

    @Transactional
    @Override
    public Product update(String id, ProductDTO productDTO, User user) {
        if(id!=null && !id.isEmpty()){
            Product target=productRepo.getById(id);
            if(target!=null){
                target.setPrice(productDTO.getPrice());
                target.setDescription(productDTO.getDescription());
                target.setName(productDTO.getName());
                target.setCategory(productDTO.getCategory());
                target.setModifiedBy(user);
                target.setDateModified(new Date());
                target.setActive(productDTO.getActive());
            }
        }
        return null;
    }

    @Override
    public List<Product> getAll() {
        return productRepo.findAll();
    }


    @Override
    public Product findByName(String name) {
        return null;
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        return null;
    }

    @Override
    public List<Product> getProductsByIndustry(Industry industry) {
        return null;
    }
}
