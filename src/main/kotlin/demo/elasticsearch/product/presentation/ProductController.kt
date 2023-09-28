package demo.elasticsearch.product.presentation

import demo.elasticsearch.product.application.ProductService
import demo.elasticsearch.product.application.ProductStatsService
import demo.elasticsearch.product.presentation.dto.ProductResponseDto
import demo.elasticsearch.product.presentation.dto.ProductSaveDto
import demo.elasticsearch.product.presentation.dto.ProductUpdateDto
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val productService: ProductService,
    private val productStatsService: ProductStatsService
) {
    @PostMapping("/product")
    fun save(@Validated @RequestBody productSaveDto: ProductSaveDto): ResponseEntity<Long> {
        return ResponseEntity.ok(productService.saveProduct(productSaveDto))
    }

    @PostMapping("/product/document/{id}")
    fun saveDocument(@PathVariable("id") id: Long): ResponseEntity<Long> {
        return ResponseEntity.ok(productService.saveProductDocument(id))
    }

    @PatchMapping("/product/{id}")
    fun update(@PathVariable("id") id: Long, @RequestBody productUpdateDto: ProductUpdateDto): ResponseEntity<Long> {
        return ResponseEntity.ok(productService.updateProduct(id, productUpdateDto))
    }

    @GetMapping("/product/document/{id}")
    fun findProductDocumentById( @PathVariable("id") id: Long): ResponseEntity<ProductResponseDto> {
        return ResponseEntity.ok(productService.findProductDocumentById(id))
    }

    @PatchMapping("/product/{productId}/likes/{statsId}")
    fun updateLikes(@PathVariable("productId") productId: Long, @PathVariable("statsId") statsId: Long): ResponseEntity<String> {
        productStatsService.updateLikes(statsId, productId)
        return ResponseEntity.ok("likes!!")
    }
}