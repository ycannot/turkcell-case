package com.github.ycannot.common.composable.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.ycannot.common.composable.R
import com.github.ycannot.common.composable.theme.TextHyperlink
import com.github.ycannot.common.composable.theme.TextRedError

@Composable
fun ErrorMessage(
    modifier: Modifier = Modifier,
    messageText: String = stringResource(R.string.unexpected_error_message),
    hyperLinkText: String = stringResource(R.string.try_again),
    onClick: (() -> Unit) = {}
) {
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center    ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextRedError(modifier = Modifier.padding(8.dp), text = messageText)
        TextHyperlink(modifier = Modifier.padding(8.dp), text = hyperLinkText, onClick = onClick)
    }
}