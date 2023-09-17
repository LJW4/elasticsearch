package demo.elasticsearch.product.controller

import demo.elasticsearch.product.service.ProductService
import demo.elasticsearch.product.dto.ProductDto
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
    fun save(@RequestBody productDto: ProductDto): ResponseEntity<String> {
        productService.save(productDto)
        return ResponseEntity.ok("success")
    }

    @GetMapping("/products")
    fun searchByName(): ResponseEntity<List<ProductDto>> {
        return ResponseEntity.ok(productService.search())
    }

    @GetMapping("/product/name")
    fun searchByName(@RequestParam("name") name: String): ResponseEntity<ProductDto> {
        println()
        return ResponseEntity.ok(productService.searchByName(name))
    }
}