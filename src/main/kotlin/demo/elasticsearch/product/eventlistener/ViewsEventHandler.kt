package demo.elasticsearch.product.eventlistener

import demo.elasticsearch.product.service.ProductMetricsService
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class ViewsEventHandler(
    private val productMetricsService: ProductMetricsService
) {

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    fun addViews(event: ViewsEvent) {
        productMetricsService.updateViews(event.productId)
    }
}