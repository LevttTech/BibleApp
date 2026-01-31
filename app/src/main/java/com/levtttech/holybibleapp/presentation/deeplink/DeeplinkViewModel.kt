package com.levtttech.holybibleapp.presentation.deeplink

import android.content.Intent
import androidx.lifecycle.viewModelScope
import com.levtttech.holybibleapp.core.ResourceProvider
import com.levtttech.holybibleapp.core.Save
import com.levtttech.holybibleapp.presentation.main.BaseViewModel
import com.levtttech.holybibleapp.presentation.main.NavigationCommunication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DeeplinkViewModel(
    resourceProvider: ResourceProvider,
    private val saveIds: Save<String>,
    communication: NavigationCommunication,
) : BaseViewModel<NavigationCommunication, Int>(resourceProvider, communication) {

    private var data = ""

    override fun fetch() {
        viewModelScope.launch(Dispatchers.IO) {
            saveIds.save(data)
            withContext(Dispatchers.Main) { communication.map(1) }
        }
    }

    fun init(intent: Intent?) {
        data = intent?.data.toString()
        fetch()
    }
}