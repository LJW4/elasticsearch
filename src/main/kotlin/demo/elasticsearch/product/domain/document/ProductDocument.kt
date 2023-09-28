package demo.elasticsearch.product.domain.document

import demo.elasticsearch.product.domain.entity.Product
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.DateFormat
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import org.springframework.data.elasticsearch.annotations.Mapping
import org.springframework.data.elasticsearch.annotations.Setting
import java.time.LocalDateTime

@Document(indexName = "product")
@Mapping(mappingPath = "elastic/product-mapping.json")
@Setting(settingPath = "elastic/product-setting.json")
open class ProductDocument(
    id: Long?,
    categoryId: Long?,
    categoryName: String?,
    name: String?,
    price: Int?,
    statsId: Long?,
    views: Int?,
    likes: Int?,
    createdDate: LocalDateTime?,
    modifiedDate: LocalDateTime?
) {
    @Id
    var id = id
        protected set

    var categoryId = categoryId
        protected set

    var categoryName = categoryName
        protected set

    var name = name
        protected set

    var price = price
        protected set

    var statsId = statsId
        protected set

    var views = views
        protected set

    var likes = likes
        protected set

    @Field(type = FieldType.Date, format = [DateFormat.date_hour_minute_second_millis, DateFormat.epoch_millis])
    var createdDate = createdDate
        protected set

    @Field(type = FieldType.Date, format = [DateFormat.date_hour_minute_second_millis, DateFormat.epoch_millis])
    var modifiedDate = modifiedDate
        protected set

    companion object {
        fun from(product: Product): ProductDocument =
            ProductDocument(
                product.id, product.category?.id, product.category?.name, product.name,
                product.price, product.productStats?.id, product.productStats?.views, product.productStats?.likes, product.createdDate, product.modifiedDate
            )
    }
}