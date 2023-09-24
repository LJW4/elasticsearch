package demo.elasticsearch.product.controller

import demo.elasticsearch.product.dto.CategorySaveDto
import demo.elasticsearch.product.service.CategoryService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CategoryController(
    private val categoryService: CategoryService
) {
    @PostMapping("/category")
    fun saveCategory(@RequestBody categorySaveDto: CategorySaveDto) {
        categoryService.saveCategory(categorySaveDto)
    }
}