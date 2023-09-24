package demo.elasticsearch.product.controller

import demo.elasticsearch.product.dto.ProductResponseDto
import demo.elasticsearch.product.service.ProductService
import demo.elasticsearch.product.dto.ProductSaveDto
import demo.elasticsearch.product.dto.ProductSearchConditionDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    val productService: ProductService
) {
    @PostMapping("/product")
    fun save(@RequestBody productDto: ProductSaveDto): ResponseEntity<String> {
        productService.saveProduct(productDto)
        return ResponseEntity.ok("saved")
    }

    @GetMapping("/product/category")
    fun searchByCategory(
        @RequestParam name: String,
        @RequestParam(defaultValue = "1") page: Int,
        @RequestParam(defaultValue = "10") perPage: Int
    ): ResponseEntity<List<ProductResponseDto>> {
        return ResponseEntity.ok(productService.search(name, page, perPage))
    }
}