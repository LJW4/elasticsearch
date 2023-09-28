package demo.elasticsearch.product.application

import demo.elasticsearch.product.domain.repository.ProductStatsRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class ProductStatsService(
    private val productStatsRepository: ProductStatsRepository,
    private val productService: ProductService
) {
    @Transactional
    fun updateViews(id: Long?, productId: Long?) {
        val findProductMetrics = productStatsRepository.findByIdOrNull(id) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Product stats info not found"
        )
        findProductMetrics.addViews(1)
        productService.saveProductDocument(productId)
    }

    @Transactional
    fun updateLikes(id: Long?, productId: Long?) {
        val findProductMetrics = productStatsRepository.findByIdOrNull(id) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Product stats info not found"
        )
        findProductMetrics.addLikes(1)
        productService.saveProductDocument(productId)
    }
}