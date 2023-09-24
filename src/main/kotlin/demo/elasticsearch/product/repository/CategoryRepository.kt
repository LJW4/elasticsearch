package demo.elasticsearch.product.repository

import demo.elasticsearch.product.entity.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<Category, Long> {

    fun findByIdIn(ids: List<Long>): List<Category>

    fun findByName(name: String): Category?
}