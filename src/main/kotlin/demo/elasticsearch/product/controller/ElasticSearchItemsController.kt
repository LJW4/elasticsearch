package demo.elasticsearch.product.controller

import co.elastic.clients.elasticsearch.core.SearchRequest
import demo.elasticsearch.product.service.ElasticSearchItemsService
import demo.elasticsearch.product.document.ProductDocument
import demo.elasticsearch.product.dto.ProductDocumentDto
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ElasticSearchItemsController(
    val elasticSearchItemsService: ElasticSearchItemsService
) {
    @PostMapping("/product")
    fun save(@RequestBody productDocumentDto: ProductDocumentDto): ResponseEntity<String> {
        elasticSearchItemsService.save(productDocumentDto)
        return ResponseEntity.ok("success")
    }

    @GetMapping("/products")
    fun searchByName(): ResponseEntity<List<ProductDocumentDto>> {
        return ResponseEntity.ok(elasticSearchItemsService.search())
    }

    @GetMapping("/product/name")
    fun searchByName(@RequestParam("name") name: String): ResponseEntity<ProductDocumentDto> {
        return ResponseEntity.ok(elasticSearchItemsService.searchByName(name))
    }
}