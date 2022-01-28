package  com.mercadolivro.purchase

import com.mercadolivro.book.BookModel
import com.mercadolivro.book.BookRepository
import com.mercadolivro.book.BookService
import com.mercadolivro.book.BookStatus
import  com.mercadolivro.event.PurchaseEvent
import com.mercadolivro.exception.BadRequestException
import com.mercadolivro.exception.Errors
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import javax.persistence.Id

@Service
class PurchaseService(
    private val purchaseRepository: PurchaseRepository,
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val bookRepository: BookRepository

) {

    fun create(purchase: Purchase) {


      val bookStatus = bookRepository.findByStatus(BookStatus.SOLD)

        println(bookStatus)
       purchaseRepository.save(purchase)

        applicationEventPublisher.publishEvent(PurchaseEvent(this, purchase))
    }
    fun update(purchase: Purchase) {
        purchaseRepository.save(purchase)

    }
}
