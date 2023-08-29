package com.github.ycannot.features.home.views.gridItem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.github.ycannot.common.composable.theme.TextEllipsis14Sp
import com.github.ycannot.common.composable.theme.TextUnderlinedBold16sp
import com.github.ycannot.common.composable.theme.TtechCaseTheme
import com.github.ycannot.common.composable.theme.WhiteCardBg
import com.github.ycannot.common.composable.views.CenteredCircularProgressIndicator
import com.github.ycannot.domain.models.GetListDataResult


@Composable
internal fun ProductGridItem(
    modifier: Modifier = Modifier,
    productContent: GetListDataResult.ProductResult?,
    onItemClicked: (product: GetListDataResult.ProductResult?) -> Unit = {}
) {
    val roundedCornerSize = 8.dp
    val borderStrokeWidth = 1.dp
    val paddingSizeBetweenItems = 4.dp
    val paddingInsideCard = 8.dp
    val paddingUnderImage = 16.dp
    Card(
        modifier = modifier
            .aspectRatio(0.6f)
            .padding(horizontal = paddingSizeBetweenItems)
            .clickable { onItemClicked(productContent) },
        border = BorderStroke(width = borderStrokeWidth, Color.LightGray),
        shape = RoundedCornerShape(roundedCornerSize),
        colors = CardDefaults.cardColors(containerColor = WhiteCardBg)
    ) {
        Column(
            modifier = Modifier.padding(paddingInsideCard)
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = paddingUnderImage)
                    .clip(RoundedCornerShape(roundedCornerSize))
                    .weight(4f),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(productContent?.image)
                    .crossfade(true)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
            ) {
                when (painter.state) {
                    AsyncImagePainter.State.Empty,
                    is AsyncImagePainter.State.Error,
                    is AsyncImagePainter.State.Loading -> {
                        CenteredCircularProgressIndicator()
                    }

                    is AsyncImagePainter.State.Success -> {
                        SubcomposeAsyncImageContent()
                    }
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                TextUnderlinedBold16sp(text = productContent!!.name)
                TextEllipsis14Sp(text = productContent.name)
            }
        }

    }
}

@Composable
@Preview
private fun ProductSliderPreview() {
    TtechCaseTheme {
        ProductGridItem(productContent = GetListDataResult.ProductResult(
            "12",
            "asd",
            3,
            ""
        ))
    }
}