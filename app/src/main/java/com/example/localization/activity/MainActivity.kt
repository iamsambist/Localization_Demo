package com.example.localization.activity

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import com.example.localization.ui.theme.LocalizationTheme
import com.example.localization.ui.view.ScreenMainView

import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LocalizationTheme {
                ScreenMainView(
                    actionUpdateLanguage = {currentState -> mainUpdateLanguage(currentState)}
                )
            }
        }
    }

    private fun mainUpdateLanguage(currentState: Boolean) {

        val languageCode = if(currentState== false) "es" else "en"
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration(resources.configuration)
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
        recreate()

    }
}
