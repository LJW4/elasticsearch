package demo.elasticsearch.common

import demo.elasticsearch.common.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.Comment
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
class BaseEntity: BaseTimeEntity() {
    @Comment("등록자 아이디")
    @Column(name = "created_id")
    var createdId: String? = null
        protected set

    @Comment("수정자 아이디")
    @Column(name = "modified_id")
    var modifiedId: String? = null
        protected set
}