package com.levtttech.holybibleapp.data.core

import com.levtttech.holybibleapp.core.Matcher


class FavoritesList(private val ids: List<Int>) {
    fun isFavorite(matcher: Matcher<Int>, transform: (Int) -> Int) =
        findFavorite(ids.map(transform), matcher)

    fun isFavorite(matcher: Matcher<Int>) = findFavorite(ids, matcher)

    private fun findFavorite(list: List<Int>, matcher: Matcher<Int>) =
        list.find { matcher.matches(it) } != null
}