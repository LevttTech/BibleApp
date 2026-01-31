package com.levtttech.holybibleapp.data.core

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class FavoriteDb : RealmObject() {
    @PrimaryKey
    var id: Int = -1
}