package demo.elasticsearch.product.repository

import demo.elasticsearch.product.document.ProductDocument
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductDocumentRepository: ElasticsearchRepository<ProductDocument, Long>, CrudRepository<ProductDocument, Long> {
    fun findByCategoryName(categoryName: String): List<ProductDocument>
}