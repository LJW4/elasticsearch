package demo.elasticsearch.product.service

import demo.elasticsearch.product.document.ProductDocument
import demo.elasticsearch.product.dto.ProductDocumentDto
import demo.elasticsearch.product.repository.ProductDocumentRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException
import java.time.LocalDateTime

@Service
class ElasticSearchItemsService(
    val productDocumentRepository: ProductDocumentRepository
) {
    @Transactional
    fun save(productDocumentDto: ProductDocumentDto) {
        productDocumentRepository.save(
            ProductDocument(
                id = productDocumentDto.id
                , name = productDocumentDto.name
                , price = productDocumentDto.price
                , description = productDocumentDto.description
                , quantity = productDocumentDto.quantity
                , createdAt = LocalDateTime.now()
            )
        )
    }

    @Transactional(readOnly = true)
    fun search(): List<ProductDocumentDto> {
        val findProducts = productDocumentRepository.findAll()
        return findProducts.map { product -> ProductDocumentDto(product.id, product.name, product.price, product.description, product.quantity, product.createdAt) }
    }

    @Transactional(readOnly = true)
    fun searchByName(name: String): ProductDocumentDto {
        val findProduct = productDocumentRepository.findByName(name) ?: throw RuntimeException("not found product")
        return ProductDocumentDto(
            id = findProduct.id,
            name = findProduct.name,
            price = findProduct.price,
            description = findProduct.description,
            quantity = findProduct.quantity,
            createAt = findProduct.createdAt
        )
    }
}