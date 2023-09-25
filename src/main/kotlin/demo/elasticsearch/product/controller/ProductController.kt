package demo.elasticsearch.product.controller

import demo.elasticsearch.product.dto.ProductResponseDto
import demo.elasticsearch.product.service.ProductService
import demo.elasticsearch.product.dto.ProductSaveDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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

    @GetMapping("/product/{id}")
    fun searchByProductId(
        @PathVariable("id") id: Long
    ): ResponseEntity<ProductResponseDto> {
        return ResponseEntity.ok(productService.searchByProductId(id))
    }

    @GetMapping("/product/name/{name}")
    fun searchByProductNameContaining(
        @PathVariable("name") name: String,
        @RequestParam(defaultValue = "1") page: Int,
        @RequestParam(defaultValue = "10") perPage: Int
    ): ResponseEntity<List<ProductResponseDto>> {
        return ResponseEntity.ok(productService.searchByProductNameContaining(name, page, perPage))
    }

    @GetMapping("/product/category/{id}")
    fun searchByCategory(
        @PathVariable("id") id: Long,
        @RequestParam(defaultValue = "1") page: Int,
        @RequestParam(defaultValue = "10") perPage: Int
    ): ResponseEntity<List<ProductResponseDto>> {
        return ResponseEntity.ok(productService.searchByCategory(id, page, perPage))
    }

    @GetMapping("/product/category/{id}/{name}")
    fun searchByCategoryWithName(
        @PathVariable("id") id: Long,
        @PathVariable("name") name: String,
        @RequestParam(defaultValue = "1") page: Int,
        @RequestParam(defaultValue = "10") perPage: Int
    ): ResponseEntity<List<ProductResponseDto>> {
        return ResponseEntity.ok(productService.searchByCategoryWithName(id, name, page, perPage))
    }
}