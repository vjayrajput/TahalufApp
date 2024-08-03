package com.app.tahaluf.business.university.data.main.mapper


interface Mapper<R, E> {
    fun mapTo(type: R?): E
}
