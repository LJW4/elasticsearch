package demo.elasticsearch.product.dto

data class ProductResponseDto(
    val id: Long,
    val name: String,
    val price: Int,
    val category: String,
    val views: Int,
    val likes: Int
)
