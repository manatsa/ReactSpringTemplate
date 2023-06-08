package org.zimnat.lionloader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zimnat.lionloader.aop.annotation.Auditor;
import org.zimnat.lionloader.business.domain.Category;
import org.zimnat.lionloader.business.domain.User;
import org.zimnat.lionloader.business.domain.dto.CategoryDTO;
import org.zimnat.lionloader.business.services.CategoryService;
import org.zimnat.lionloader.business.services.IndustryService;
import org.zimnat.lionloader.business.services.UserService;

/**
 * @author :: codemaster
 * created on :: 2/4/2023
 */

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    IndustryService industryService;

    @Autowired
    UserService userService;

    @Auditor
    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(categoryService.getAll());
    }


    @Auditor
    @PostMapping("/")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO){
        Category category= new Category();
        User user=userService.getCurrentUser();
        System.err.println(categoryDTO);
        try{
            category.setName(categoryDTO.getName());
            category.setDescription(categoryDTO.getDescription());
            category.setIndustry(industryService.findById(categoryDTO.getIndustry()));
            category =categoryService.save(category, user);
            return  ResponseEntity.ok(categoryService.getAll());
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    @Auditor
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable("id") String id){

        try{
            User currentUser=userService.getCurrentUser();
            Category category=categoryService.update(id, categoryDTO,currentUser);
            return  ResponseEntity.ok(categoryService.getAll());
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
