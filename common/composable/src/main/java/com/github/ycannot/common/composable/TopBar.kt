package com.github.ycannot.common.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    @DrawableRes navIcon: Int? = R.drawable.round_arrow_back_24,
    title: String? = null,
    backgroundColor: Color = Color.Transparent,
    actions: @Composable RowScope.() -> Unit = {},
    onClickNavIcon: () -> Unit = {},
) {
    Surface(shadowElevation = 8.dp) {
        CenterAlignedTopAppBar(
            title = {
                title?.let {
                    Text(text = title, style = MaterialTheme.typography.headlineSmall)
                }
            },
            navigationIcon = {
                navIcon?.let {
                    Icon(painter = painterResource(id = navIcon),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .clickable { onClickNavIcon() })
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = backgroundColor),
            actions = {
                actions()
            },
        )
    }
}

@Preview
@Composable
private fun TopBarPreview(){
    TopBar(title = "Hello", backgroundColor = Color.White)
}