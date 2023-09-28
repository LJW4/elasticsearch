package demo.elasticsearch.product.domain.entity

import demo.elasticsearch.common.BaseTimeEntity
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicUpdate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.util.StringUtils

@DynamicUpdate
@EntityListeners(AuditingEntityListener::class)
@Entity
@Table(name = "t_product")
class Product(
    category: Category?,
    name: String?,
    price: Int?,
    productStats: ProductStats?
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
        protected set

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stats_id")
    var productStats = productStats
        protected set

    fun updateProduct(name: String?, price: Int?) {
        if (StringUtils.hasText(name)) this.name = name
        if (price != null) this.price = price;
    }
}