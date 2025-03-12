package com.example.localisationwithcompose

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.localisationwithcompose.ui.theme.LocalisationWIthComposeTheme

@Composable
fun SettingScreen(
    settingViewModel: SettingViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    val settingState by settingViewModel.settingState.collectAsState()

    val onAppLanguageChanged: (String) -> Unit = { newLanguage ->
        settingViewModel.changeLanguage(newLanguage)
    }
    SettingContent(
        selectedLanguage = settingState.selectedLanguage,
        onAppLanguageChanged
    ) {
        navigateBack()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingContent(
    selectedLanguage: String,
    onAppLanguageChanged: (String) -> Unit,
    onNavigateBack: () -> Unit
) {
    val barScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(topBar = {
        ChildAppTopBar(
            title = stringResource(R.string.settings),
            scrollBehavior = barScrollBehavior,
            onNavigateBack = onNavigateBack
        )
    }) { paddingValues ->

        Box(modifier = Modifier.padding(paddingValues)) {
            LazyColumn {
                items(appLanguages) { language ->
                    LanguageRow(language, language.code == selectedLanguage) {
                        onAppLanguageChanged(it.code)
                    }
                }
            }
        }

    }

}



@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    LocalisationWIthComposeTheme {
        SettingContent(
            selectedLanguage = "en",
            onAppLanguageChanged = {},
            onNavigateBack = {}
        )
    }
}