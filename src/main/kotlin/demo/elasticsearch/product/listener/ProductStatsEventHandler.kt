package demo.elasticsearch.product.listener

import demo.elasticsearch.product.application.ProductStatsService
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class ProductStatsEventHandler(
    private val productStatsService: ProductStatsService
) {
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun addViews(event: ProductViewsEvent) {
        productStatsService.updateViews(event.id, event.productId)
    }
}