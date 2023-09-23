package demo.elasticsearch.product.service

import demo.elasticsearch.product.entity.Product
import demo.elasticsearch.product.entity.ProductMetrics
import demo.elasticsearch.product.repository.ProductMetricsRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductMetricsService(
    private val productMetricsRepository: ProductMetricsRepository
) {
    @Transactional
    fun saveProductMetrics(product: Product): ProductMetrics {
        return productMetricsRepository.save(ProductMetrics(product))
    }
}