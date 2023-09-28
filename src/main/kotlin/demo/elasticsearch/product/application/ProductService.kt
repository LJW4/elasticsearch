package demo.elasticsearch.product.application

import demo.elasticsearch.product.domain.document.ProductDocument
import demo.elasticsearch.product.presentation.dto.ProductSaveDto
import demo.elasticsearch.product.domain.entity.Product
import demo.elasticsearch.product.domain.entity.ProductStats
import demo.elasticsearch.product.domain.repository.CategoryRepository
import demo.elasticsearch.product.domain.document.ProductDocumentRepository
import demo.elasticsearch.product.domain.repository.ProductRepository
import demo.elasticsearch.product.domain.repository.ProductStatsRepository
import demo.elasticsearch.product.listener.ProductViewsEvent
import demo.elasticsearch.product.presentation.dto.ProductResponseDto
import demo.elasticsearch.product.presentation.dto.ProductUpdateDto
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository,
    private val productStatsRepository: ProductStatsRepository,
    private val productDocumentRepository: ProductDocumentRepository,
    private val publisher: ApplicationEventPublisher
) {
    @Transactional
    fun saveProduct(productSaveDto: ProductSaveDto): Long {
        val findCategory = categoryRepository.findByName(productSaveDto.category) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "category is not found")
        val savedProductStats = productStatsRepository.save(ProductStats())
        val savedProduct = productRepository.save(Product(findCategory, productSaveDto.name, productSaveDto.price, savedProductStats))
        return savedProduct.id;
    }

    @Transactional
    fun saveProductDocument(id: Long?): Long? {
        val findProduct = productRepository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "product is not found")
        val savedProductDocument = productDocumentRepository.save(ProductDocument.from(findProduct))
        return savedProductDocument.id
    }

    @Transactional
    fun updateProduct(id: Long, productUpdateDto: ProductUpdateDto): Long {
        val findProduct = productRepository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND,"product is not found")
        findProduct.updateProduct(productUpdateDto.name, productUpdateDto.price)
        findProduct.category?.updateCategory(productUpdateDto.category)
        return findProduct.id
    }

    @Transactional(readOnly = true)
    fun findProductDocumentById(id: Long): ProductResponseDto {
        val findProductDocument = productDocumentRepository.findByIdOrNull(id) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Product not found in Elasticsearch"
        )
        publisher.publishEvent(ProductViewsEvent(findProductDocument.id, findProductDocument.statsId))
        return ProductResponseDto.from(findProductDocument)
    }
}