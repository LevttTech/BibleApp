package com.levtttech.holybibleapp.core


interface Matcher<T> {

    fun matches(arg: T): Boolean
}