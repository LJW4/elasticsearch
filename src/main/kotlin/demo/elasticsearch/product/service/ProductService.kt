package demo.elasticsearch.product.service

import demo.elasticsearch.product.document.ProductDocument
import demo.elasticsearch.product.dto.ProductResponseDto
import demo.elasticsearch.product.dto.ProductSaveDto
import demo.elasticsearch.product.entity.Product
import demo.elasticsearch.product.repository.ProductDocumentRepository
import demo.elasticsearch.product.repository.ProductRepository
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
        val savedCategory = categoryService.saveCategory(productDto.category)
        val savedProduct = productRepository.save(Product(savedCategory, productDto.name, productDto.price))
        val savedProductMetrics = productMetricsService.saveProductMetrics(savedProduct)
        productDocumentRepository.save(
            ProductDocument(savedProduct.id, savedProduct.name, savedProduct.price,
                savedProductMetrics.views, savedProductMetrics.likes, savedCategory.name, savedProduct.createdDate, savedProduct.modifiedDate)
        )
    }

    @Transactional(readOnly = true)
    fun searchByCategory(name: String): List<ProductResponseDto> {
        val findProducts = productDocumentRepository.findByCategoryName(name)
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

    @Transactional(readOnly = true)
    fun searchAll(): List<ProductResponseDto> {
        val findProducts = productDocumentRepository.findAll()
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