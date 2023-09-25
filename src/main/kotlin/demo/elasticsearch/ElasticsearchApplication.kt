package demo.elasticsearch

import demo.elasticsearch.product.entity.Category
import demo.elasticsearch.product.repository.CategoryRepository
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class ElasticsearchApplication(private val categoryRepository: CategoryRepository) {

	@PostConstruct
	fun initCategory() {
		val categoryNames = listOf("상의", "하의", "아우터", "신발", "액세서리", "가방", "테크")
		categoryRepository.saveAll(categoryNames.map { Category(it) }.toList())
	}
}

fun main(args: Array<String>) {
	runApplication<ElasticsearchApplication>(*args)
}