package demo.elasticsearch.product.service

import demo.elasticsearch.product.document.ProductDocument
import demo.elasticsearch.product.dto.ProductResponseDto
import demo.elasticsearch.product.dto.ProductSaveDto
import demo.elasticsearch.product.entity.Product
import demo.elasticsearch.product.eventlistener.ViewsEvent
import demo.elasticsearch.product.repository.ProductDocumentRepository
import demo.elasticsearch.product.repository.ProductRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val productDocumentRepository: ProductDocumentRepository,
    private val categoryService: CategoryService,
    private val productMetricsService: ProductMetricsService,
    private val eventListener: ApplicationEventPublisher
) {
    @Transactional
    fun saveProduct(productDto: ProductSaveDto) {
        val findCategory = categoryService.findByName(productDto.category)
        val savedProduct = productRepository.save(Product(findCategory, productDto.name, productDto.price))
        val savedProductMetrics = productMetricsService.saveProductMetrics(savedProduct)
        productDocumentRepository.save(
            ProductDocument(savedProduct.id, savedProduct.name, savedProduct.price,
                savedProductMetrics.views, savedProductMetrics.likes, findCategory.id, findCategory.name, savedProduct.createdDate, savedProduct.modifiedDate)
        )
    }

    @Transactional
    fun searchByProductId(id: Long): ProductResponseDto {
        eventListener.publishEvent(ViewsEvent(id))

        val findProduct = productDocumentRepository.findByIdOrNull(id) ?: throw RuntimeException("not found")
        return ProductResponseDto(
            findProduct.productId,
            findProduct.productName,
            findProduct.productPrice,
            findProduct.categoryName,
            findProduct.productViews,
            findProduct.productLikes
        )
    }

    @Transactional(readOnly = true)
    fun searchByProductNameContaining(name: String, page: Int, perPage: Int): List<ProductResponseDto> {
        val findProducts = productDocumentRepository.findByProductNameContaining(name, PageRequest.of(page - 1, perPage))
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
    fun searchByCategory(id: Long, page: Int, perPage: Int): List<ProductResponseDto> {
        val findProducts = productDocumentRepository.findByCategoryId(id, PageRequest.of(page - 1, perPage))
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
    fun searchByCategoryWithName(id: Long, name: String, page: Int, perPage: Int): List<ProductResponseDto> {
        val findProducts = productDocumentRepository.findByCategoryIdAndProductNameContaining(id, name, PageRequest.of(page - 1, perPage))
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