package com.github.ycannot.common.composable.theme

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TextUnderlinedBold16sp(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 16.sp,
    color: Color = Color.Black
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        textDecoration = TextDecoration.Underline,
        fontWeight = FontWeight.Bold,
        fontSize = fontSize
    )
}


@Composable
fun TextEllipsis14Sp(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 14.sp,
    color: Color = Color.Gray
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontSize = fontSize,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun TextRedError(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 16.sp,
    color: Color = Color.Red
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontSize = fontSize
    )
}


@Composable
fun TextHyperlink(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 16.sp,
    color: Color = Color.Blue,
    onClick: () -> Unit = {}
) {
    Text(
        modifier = modifier.clickable{onClick.invoke()},
        text = text,
        color = color,
        fontSize = fontSize,
        textDecoration = TextDecoration.Underline,
        fontWeight = FontWeight.Bold,
    )
}



@Composable
fun TextEllipsisExtraBold24Sp(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 24.sp,
    color: Color = Color.LightGray
) {
    Text(
        modifier = modifier.shadow(8.dp),
        text = text,
        color = color,
        fontSize = fontSize,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        fontWeight = FontWeight.ExtraBold
    )
}


@Composable
fun TextEllipsisMedium16Sp(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 16.sp,
    color: Color = Color.White
) {
    Text(
        modifier = modifier.shadow(8.dp),
        text = text,
        color = color,
        fontSize = fontSize,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun TextEllipsisSemiBold20Sp(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 20.sp,
    color: Color = Color.Black
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontSize = fontSize,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
fun TextEllipsisExtraBold22Sp(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 22.sp,
    color: Color = Color.Black
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontSize = fontSize,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        fontWeight = FontWeight.ExtraBold
    )
}

@Composable
fun TextOrangeMedium20Sp(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 20.sp,
    color: Color = orange
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontSize = fontSize,
        maxLines = 2,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun TextWhite20Sp(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 20.sp,
    color: Color = Color.White
) {
    Text(modifier = Modifier, text = text, color = color, fontSize = fontSize)
}


@Composable
fun TextGray16Sp(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 16.sp,
    color: Color = Color.Gray
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontSize = fontSize
    )
}


@Composable
@Preview
private fun TextPreviews() {
    TextEllipsisExtraBold24Sp(text = "TextProductSingleBannerTitle")
}
