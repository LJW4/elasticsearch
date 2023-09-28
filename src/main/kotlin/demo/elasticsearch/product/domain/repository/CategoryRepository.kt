package demo.elasticsearch.product.domain.repository

import demo.elasticsearch.product.domain.entity.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<Category, Long> {
    fun findByName(name: String?): Category?
}