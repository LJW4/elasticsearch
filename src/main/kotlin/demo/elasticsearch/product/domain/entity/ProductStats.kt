package demo.elasticsearch.product.domain.entity

import demo.elasticsearch.common.BaseTimeEntity
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicUpdate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@DynamicUpdate
@EntityListeners(AuditingEntityListener::class)
@Entity
@Table(name = "t_product_stats")
class ProductStats: BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
        protected set

    @Comment("상품 조회수")
    @Column(name = "views")
    var views: Int = 0
        protected set

    @Comment("상품 좋아요")
    @Column(name = "likes")
    var likes: Int = 0
        protected set

    fun addViews(views: Int) {
        this.views += views
    }

    fun addLikes(likes: Int) {
        this.likes += likes
    }
}