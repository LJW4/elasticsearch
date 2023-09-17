package demo.elasticsearch

import org.apache.http.protocol.HTTP
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ElasticSearchItemsController(
    val elasticSearchItemsService: ElasticSearchItemsService
) {
    @PostMapping("/api/items")
    fun save(): ResponseEntity<String> {
        elasticSearchItemsService.save()
        return ResponseEntity.ok("success")
    }
}