package demo.elasticsearch.product.domain.document

import demo.elasticsearch.product.domain.document.ProductDocument
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductDocumentRepository: ElasticsearchRepository<ProductDocument, Long> {

}