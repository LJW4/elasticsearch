package demo.elasticsearch.product.document

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.DateFormat
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import org.springframework.data.elasticsearch.annotations.Mapping
import org.springframework.data.elasticsearch.annotations.Setting
import java.time.LocalDate
import java.time.LocalDateTime

@Document(indexName = "product")
@Mapping(mappingPath = "elastic/product-mapping.json")
@Setting(settingPath = "elastic/product-setting.json")
class ProductDocument(
    @Id
    val productId: Long,
    val productName: String,
    val productPrice: Int,
    val productViews: Int,
    val productLikes: Int,
    val categoryName: String,
    @Field(type = FieldType.Date, format = [DateFormat.date_hour_minute_second_millis, DateFormat.epoch_millis])
    val createdDate: LocalDateTime,
    @Field(type = FieldType.Date, format = [DateFormat.date_hour_minute_second_millis, DateFormat.epoch_millis])
    val modifiedDate: LocalDateTime,
)