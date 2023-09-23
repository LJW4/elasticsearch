package demo.elasticsearch.common

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
class BaseTimeEntity {
    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    val createdDate: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    @Column(name = "modified_date", nullable = false)
    val modifiedDate: LocalDateTime = LocalDateTime.now()
}