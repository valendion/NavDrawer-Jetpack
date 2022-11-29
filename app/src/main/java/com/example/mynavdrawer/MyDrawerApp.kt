package com.example.mynavdrawer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mynavdrawer.component.DrawerContent
import com.example.mynavdrawer.component.TopBar
import com.example.mynavdrawer.ui.theme.MyNavDrawerTheme

@Composable
fun MyNavDrawerApp() {
    val appState = rememberMyNavDrawerState()
    Scaffold(
        scaffoldState = appState.scaffoldState,
        topBar = {
            TopBar(
                onMenuClick = {
                    appState.onMenuClick()
                }
            )
        },
        drawerContent = {
            DrawerContent(onItemSelected = appState::onItemSelected,
                onBackPressed = appState::onBackPress)
        },
        drawerGesturesEnabled = appState.scaffoldState.drawerState.isOpen
    ) { paddingValues: PaddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(text = stringResource(id = R.string.hello_world))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyNavDrawerTheme {
        MyNavDrawerApp()
    }
}