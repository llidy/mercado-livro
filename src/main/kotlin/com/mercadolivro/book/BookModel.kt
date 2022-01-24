package com.mercadolivro.book

import com.mercadolivro.customer.Customer
import com.mercadolivro.exception.BadRequestException
import com.mercadolivro.exception.Errors
import java.math.BigDecimal
import java.text.DecimalFormat
import javax.persistence.*

@Entity(name = "book")
class BookModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    var price: BigDecimal,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: Customer? = null
){
    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null
        set(value){
            if(field == BookStatus.CALLED_OFF || field == BookStatus.DELETED){
                throw BadRequestException(Errors.ML101.message.format(field), Errors.ML101.code)
            } else if (field == BookStatus.SOLD){
                throw BadRequestException(Errors.ML101.message, Errors.ML101.code)
            }
            field = value
        }


    constructor(id: Int? = null,
                name: String,
                price: BigDecimal,
                customer: Customer? = null,
                status: BookStatus?): this(id, name, price, customer){
                    this.status = status

                }

}