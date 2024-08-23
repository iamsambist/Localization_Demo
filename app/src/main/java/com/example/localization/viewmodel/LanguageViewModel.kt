package com.example.localization.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LanguageViewModel : ViewModel() {
    private var _swtichState = MutableStateFlow(false)
    val switchState : StateFlow<Boolean> = _swtichState

    private var onLanguageChange: (() -> Unit)? = null

    fun setOnLanguageChangeListener(listener: () -> Unit) {
        onLanguageChange = listener
    }

    fun switchUpdated(){
        _swtichState.value = !_swtichState.value
        changeLanguage()
    }

    private fun changeLanguage() {
        // if en setlocale "es" else "en"
        // activity recreate
        onLanguageChange?.invoke()
    }
}