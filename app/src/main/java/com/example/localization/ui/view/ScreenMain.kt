package com.example.localization.ui.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.localization.R
import com.example.localization.viewmodel.LanguageViewModel
import java.util.Locale
import android.content.res.Resources

@Composable
fun ScreenMainView(
    viewModel : LanguageViewModel = viewModel(),
    actionUpdateLanguage : ((Boolean)->Unit)){
    val switchState = viewModel.switchState.collectAsState()
    viewModel.setOnLanguageChangeListener {

        actionUpdateLanguage(switchState.value)
    }


    BottomNavigationSample(
        switchState.value,
        viewModel
    )
}

@Composable
fun BottomNavigationSample(
    switchState : Boolean,
    viewModel: LanguageViewModel
){
    val context = LocalContext.current
    var seletedItem by remember { mutableStateOf(0)}
    val items = listOf(
        context.getString(R.string.item1),
        context.getString(R.string.item2),
        context.getString(R.string.item3)
    )
    


    Scaffold (
        bottomBar = {
            BottomNavigation (modifier = Modifier.navigationBarsPadding()){
                items.forEachIndexed{index,item ->
                    BottomNavigationItem(
                        selected =seletedItem==index ,
                        onClick = {seletedItem = index },
                        icon = {
                            Icon(Icons.Filled.Favorite, contentDescription ="fav" )
                        },
                        label = { Text(text = "$item")})
                }
            }
        }
    ){ innerPadding ->
        // Your screen content goes here, with the padding applied
        Column (modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){

            Switch(
                checked = switchState,
                onCheckedChange = {
                    viewModel.switchUpdated()
                }
            )
        }
    }
}