package demo.elasticsearch.product.repository

import demo.elasticsearch.product.document.ProductDocument
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.annotations.Query
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductDocumentRepository: ElasticsearchRepository<ProductDocument, Long> {

    @Query("{\"match\": {\"categoryName\": {\"query\": \"?0\"}}}")
    fun findByCategoryName(name: String, pageable: Pageable): List<ProductDocument>
}