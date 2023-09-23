package demo.elasticsearch.product.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import demo.elasticsearch.common.BaseEntity
import demo.elasticsearch.common.BaseTimeEntity
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicUpdate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@DynamicUpdate
@EntityListeners(AuditingEntityListener::class)
@Entity
@Table(name = "t_product")
class Product(
    category: Category,
    name: String,
    price: Int
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
        protected set

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    var category = category
        protected set

    @Comment("상품명")
    @Column(name = "name", nullable = false, unique = true)
    var name = name
        protected set

    @Comment("상품 가격")
    @Column(name = "price")
    var price = price
        protected set

    fun updateName(name: String) {
        this.name = name
    }

    fun updatePrice(price: Int) {
        this.price = price
    }
}