package com.sda.onlinestore.restController;
import com.sda.onlinestore.dto.CategoryDto;
import com.sda.onlinestore.entity.Category;
import com.sda.onlinestore.service.CategoryService;
import com.sda.onlinestore.transformers.CategoryTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/category")
@CrossOrigin(origins = "*")
public class CategoryController {

    private final CategoryService categoryService;

    private final CategoryTransformer categoryTransformer;

    @Autowired
    public CategoryController(CategoryService categoryService, CategoryTransformer categoryTransformer){
        this.categoryService = categoryService;
        this.categoryTransformer = categoryTransformer;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        Category category = categoryTransformer.transform(categoryDto);
        Category savedCategory = categoryService.saveCategory(category);
        CategoryDto savedCategoryDto = categoryTransformer.transformReversed(savedCategory);
        return ResponseEntity.ok(savedCategoryDto);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories(){
        List<CategoryDto> categoryDtoList = categoryService.getCategories();
        return ResponseEntity.ok(categoryDtoList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CategoryDto> findCategoryById(@PathVariable("id") Long id){
        Category category = categoryService.findCategoryById(id);
        CategoryDto categoryDto = categoryTransformer.transformReversed(category);
        return ResponseEntity.ok(categoryDto);
    }

//    @GetMapping
//    public ResponseEntity<List<CategoryDto>> getCategoryByName(@RequestParam(value = "name") String name) {
//        List<Category> categories = categoryService.findCategoryByName(name);
//        List<CategoryDto> categoryDtos = categories.stream().map(categoryTransformer::transformReversed).collect(Collectors.toList());
//        return ResponseEntity.ok(categoryDtos);
//    }

    @PutMapping
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto){
        Category category = categoryTransformer.transform(categoryDto);
        Category savedCategory = categoryService.saveCategory(category);
        CategoryDto savedCategoryDto = categoryTransformer.transformReversed(savedCategory);
        return ResponseEntity.ok(savedCategoryDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable("id") Long id){
        categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }
}
