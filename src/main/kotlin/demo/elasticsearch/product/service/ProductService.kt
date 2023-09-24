package demo.elasticsearch.product.service

import demo.elasticsearch.product.document.ProductDocument
import demo.elasticsearch.product.dto.ProductResponseDto
import demo.elasticsearch.product.dto.ProductSaveDto
import demo.elasticsearch.product.entity.Product
import demo.elasticsearch.product.repository.ProductDocumentRepository
import demo.elasticsearch.product.repository.ProductRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val productDocumentRepository: ProductDocumentRepository,
    private val categoryService: CategoryService,
    private val productMetricsService: ProductMetricsService
) {
    @Transactional
    fun saveProduct(productDto: ProductSaveDto) {
        val findCategory = categoryService.findByName(productDto.category)
        val savedProduct = productRepository.save(Product(findCategory, productDto.name, productDto.price))
        val savedProductMetrics = productMetricsService.saveProductMetrics(savedProduct)
        productDocumentRepository.save(
            ProductDocument(savedProduct.id, savedProduct.name, savedProduct.price,
                savedProductMetrics.views, savedProductMetrics.likes, findCategory.name, savedProduct.createdDate, savedProduct.modifiedDate)
        )
    }

    @Transactional(readOnly = true)
    fun search(name: String, page: Int, perPage: Int): List<ProductResponseDto> {
        val findProducts = productDocumentRepository.findByCategoryName(name, PageRequest.of(page - 1, perPage))
        return findProducts.map { product ->
            ProductResponseDto(
                product.productId,
                product.productName,
                product.productPrice,
                product.categoryName,
                product.productViews,
                product.productLikes
            )
        }
    }
}