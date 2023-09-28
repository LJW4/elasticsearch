package demo.elasticsearch.product.presentation.dto

import demo.elasticsearch.product.domain.document.ProductDocument
import demo.elasticsearch.product.domain.entity.Product

data class ProductResponseDto(
    val id: Long?,
    val category: String?,
    val name: String?,
    val price: Int?,
    val views: Int?,
    val likes: Int?,
) {
    companion object {
        fun from(productDocument: ProductDocument): ProductResponseDto = ProductResponseDto(
            productDocument.id, productDocument.categoryName, productDocument.name, productDocument.price,
            productDocument.views, productDocument.likes
        )

        fun from(product: Product): ProductResponseDto = ProductResponseDto(
            product.id, product.category?.name, product.name, product.price,
            product.productStats?.views, product.productStats?.likes
        )
    }
}
