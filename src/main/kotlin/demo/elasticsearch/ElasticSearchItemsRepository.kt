package demo.elasticsearch

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ElasticSearchItemsRepository: ElasticsearchRepository<ElasticSearchItems, Long>, CrudRepository<ElasticSearchItems, Long> {
}