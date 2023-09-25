package demo.elasticsearch.product.repository

import demo.elasticsearch.product.document.ProductDocument
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductDocumentRepository: ElasticsearchRepository<ProductDocument, Long> {

    fun findByCategoryIdAndProductNameContaining(id: Long, name: String, pageable: Pageable): List<ProductDocument>

    fun findByCategoryId(id: Long, pageable: Pageable): List<ProductDocument>

    fun findByProductNameContaining(name: String, pageable: Pageable): List<ProductDocument>
}