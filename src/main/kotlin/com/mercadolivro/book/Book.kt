package com.mercadolivro.book

import com.mercadolivro.customer.Customer
import java.math.BigDecimal
import java.text.DecimalFormat
import javax.persistence.*

@Entity
class Book(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    var price: BigDecimal,

    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: Customer? = null
)