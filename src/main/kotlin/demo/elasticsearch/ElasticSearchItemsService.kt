package demo.elasticsearch

import org.springframework.stereotype.Service

@Service
class ElasticSearchItemsService(
    val elasticSearchItemsRepository: ElasticSearchItemsRepository
) {

    fun save() {
        elasticSearchItemsRepository.save(
            ElasticSearchItems(id = 1L, name = "선풍기", price = 30000, description = "선풍기", quantity = 1)
        )
    }
}