package demo.elasticsearch

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

@Document(indexName = "items")
class ElasticSearchItems(
    @Id
    val id: Long,

    @Field(type = FieldType.Keyword)
    val name: String,

    @Field(type = FieldType.Integer)
    val price: Int,

    @Field(type = FieldType.Keyword)
    val description: String,

    @Field(type = FieldType.Integer)
    val quantity: Int
)