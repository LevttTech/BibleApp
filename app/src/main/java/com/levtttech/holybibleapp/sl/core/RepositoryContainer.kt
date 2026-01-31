package com.levtttech.holybibleapp.sl.core

import com.levtttech.holybibleapp.domain.core.Repository
interface RepositoryContainer<T : Repository<*>> {

    fun repository(): T
}