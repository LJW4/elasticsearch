package demo.elasticsearch.product.service

import demo.elasticsearch.product.dto.CategorySaveDto
import demo.elasticsearch.product.entity.Category
import demo.elasticsearch.product.repository.CategoryRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {
    @Transactional
    fun saveCategory(categorySaveDto: CategorySaveDto): Category {
        return categoryRepository.save(Category(categorySaveDto.name))
    }

    @Transactional(readOnly = true)
    fun findByName(name: String): Category {
        return categoryRepository.findByName(name) ?: throw RuntimeException("category is not found")
    }

    @Transactional(readOnly = true)
    fun findByIdIn(ids: List<Long>): List<Category> {
        return categoryRepository.findByIdIn(ids)
    }
}