package demo.elasticsearch.product.dto

import java.time.LocalDateTime

data class ProductDocumentDto(
    var id: Long,
    var name: String,
    var price: Int,
    var description: String,
    var quantity: Int,
    var createAt: LocalDateTime
)
