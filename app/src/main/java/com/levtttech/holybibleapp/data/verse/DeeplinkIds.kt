package com.levtttech.holybibleapp.data.verse

import com.levtttech.holybibleapp.core.Save
import com.levtttech.holybibleapp.presentation.deeplink.DeeplinkData

class DeeplinkIds(
    private val saveBookId: Save<Int>,
    private val saveChapterId: Save<Int>,
    private val saveVerseId: Save<Int>,
    private val deeplinkData: DeeplinkData
) : Save<String> {

    override fun save(data: String) {
        val ids = deeplinkData.ids(data)
        if (ids.size == 3) {
            saveBookId.save(ids[0])
            saveChapterId.save(ids[1])
            saveVerseId.save(ids[2])
        }
    }
}