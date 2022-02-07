package com.mercadolivro.book

class PageResponse<T> (
    var items: List<T>,
    var currentPage: Int,
    var totalItems: Long,
    var totalPages: Int,
        )