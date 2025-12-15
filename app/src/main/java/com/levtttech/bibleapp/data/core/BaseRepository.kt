package com.levtttech.bibleapp.data.core

import com.levtttech.bibleapp.core.Abstract
import com.levtttech.bibleapp.domain.core.Repository

abstract class BaseRepository<T, C, D, R>(
    private val cloudMapper: Abstract.Mapper.Data<List<C>, List<D>>,
    private val cacheMapper: Abstract.Mapper.Data<List<T>, List<D>>
) : Repository<R> {
    override suspend fun fetch(): R {
        return try {
            val cacheList = cachedList()
            if (cacheList.isEmpty()) {
                val cloudList = cloudList()
                val dataList = cloudMapper.map(cloudList)
                save(dataList)
                returnSuccess(dataList)
            } else {
                returnSuccess(cacheMapper.map(cacheList))
            }
        } catch (e: Exception) {
            returnFail(e)
        }
    }

    protected abstract suspend fun cachedList(): List<T>
    protected abstract suspend fun cloudList(): List<C>
    protected abstract suspend fun save(data: List<D>)
    protected abstract fun returnSuccess(data: List<D>): R
    protected abstract fun returnFail(e: Exception): R

}