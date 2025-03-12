package com.example.localisationwithcompose
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val appLocaleManager = AppLocaleManager()
    private val _settingState = MutableStateFlow(SettingState())
    val settingState: StateFlow<SettingState> = _settingState

    init {
        loadInitialLanguage()
    }

    private fun loadInitialLanguage() {
        viewModelScope.launch {
            val currentLanguage = appLocaleManager.getLanguageCode(context)
            _settingState.value = _settingState.value.copy(selectedLanguage = currentLanguage)
        }
    }

    fun changeLanguage(languageCode: String) {
        viewModelScope.launch {
            appLocaleManager.changeLanguage(context, languageCode)
            _settingState.value = _settingState.value.copy(selectedLanguage = languageCode)
        }
    }
}

data class SettingState(
    val selectedLanguage: String = ""
)