package demo.elasticsearch.product.domain.repository

import demo.elasticsearch.product.domain.entity.ProductStats
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductStatsRepository : JpaRepository<ProductStats, Long> {

}