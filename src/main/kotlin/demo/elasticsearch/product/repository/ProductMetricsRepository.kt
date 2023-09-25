package demo.elasticsearch.product.repository

import demo.elasticsearch.product.entity.ProductMetrics
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductMetricsRepository : JpaRepository<ProductMetrics, Long> {

    fun findByProductId(productId: Long): ProductMetrics?
}