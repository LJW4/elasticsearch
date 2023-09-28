package demo.elasticsearch.product.domain.entity

import demo.elasticsearch.common.BaseEntity
import demo.elasticsearch.common.BaseTimeEntity
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicUpdate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.util.StringUtils

@DynamicUpdate
@EntityListeners(AuditingEntityListener::class)
@Entity
@Table(name = "t_category")
class Category(
    name: String?
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
        protected set

    @Comment("카테고리명")
    @Column(name = "name", nullable = false, unique = true)
    var name = name
        protected set

    fun updateCategory(name: String?) {
        if (StringUtils.hasText(name)) this.name = name
    }
}