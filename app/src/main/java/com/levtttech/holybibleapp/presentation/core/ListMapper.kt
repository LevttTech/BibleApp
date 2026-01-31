package com.levtttech.holybibleapp.presentation.core

import com.levtttech.holybibleapp.core.Abstract

interface ListMapper<T> : Abstract.Mapper.Data<List<T>, Unit> {
    class Empty<T> : ListMapper<T> {
        override fun map(data: List<T>) = Unit
    }
}