package com.example.mynavdrawer.component

import android.graphics.drawable.Icon
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mynavdrawer.R
import com.example.mynavdrawer.model.MenuItem

@Composable
fun DrawerContent(
    modifier: Modifier = Modifier,
    onItemSelected: (title: String) -> Unit,
    onBackPressed: () -> Unit
) {
    val items = listOf(
        MenuItem(
            title = stringResource(id = R.string.home),
            icon = Icons.Default.Home
        ),

        MenuItem(
            title = stringResource(id = R.string.favourite),
            icon = Icons.Default.Favorite
        ),

        MenuItem(
            title = stringResource(id = R.string.profile),
            icon = Icons.Default.AccountCircle
        )
    )

    Column(modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colors.primary)
        )


        for (item in items) {
            Row(
                modifier = Modifier
                    .clickable { onItemSelected(item.title) }
                    .padding(
                        vertical = 12.dp,
                        horizontal = 16.dp
                    )
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.title,
                    tint = Color.DarkGray
                )

                Spacer(modifier = Modifier.width(32.dp))
                Text(text = item.title, style = MaterialTheme.typography.subtitle2)
            }


        }

        Divider()
    }
    BackPressHandler {
        onBackPressed()
    }
}

@Composable
fun BackPressHandler(enable: Boolean= true, onBackPressed: () ->Unit) {
    val currentOnBackPressed by rememberUpdatedState(newValue = onBackPressed )
    val backCalback = remember {
        object : OnBackPressedCallback(enable){
            override fun handleOnBackPressed() {
                currentOnBackPressed()
            }

        }
    }

    SideEffect {
        backCalback.isEnabled = enable
    }

    val backDispatcher = checkNotNull(
        LocalOnBackPressedDispatcherOwner.current
    ){
        "No OnBackPressedDispatcherOwner was provided via LocalOnBackPressedDispatcherOwner"
    }.onBackPressedDispatcher

    val lifecycleOwner =  LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner, backDispatcher){
        backDispatcher.addCallback(lifecycleOwner, backCalback)
        onDispose {
            backCalback.remove()
        }
    }
}

