package com.levtttech.holybibleapp.data.books

import com.levtttech.holybibleapp.core.Matcher
import com.levtttech.holybibleapp.core.Save

interface TestamentTemp : Matcher<String>, Save<String> {
    fun isEmpty(): Boolean
    fun clear() = save("")

    class Base : TestamentTemp {
        private var temp: String = ""
        override fun save(data: String) {
            temp = data
        }
        override fun matches(arg: String) = temp == arg
        override fun isEmpty() = temp.isEmpty()
    }
}