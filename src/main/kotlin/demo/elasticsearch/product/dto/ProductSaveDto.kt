package demo.elasticsearch.product.dto

data class ProductSaveDto(
    val name: String,
    val price: Int,
    val category: String,
)
