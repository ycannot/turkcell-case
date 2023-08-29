package com.github.ycannot.features.home.ui.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.github.ycannot.common.composable.TopBar
import com.github.ycannot.common.composable.theme.TextEllipsis14Sp
import com.github.ycannot.common.composable.theme.TextEllipsisExtraBold22Sp
import com.github.ycannot.common.composable.theme.TextEllipsisExtraBold24Sp
import com.github.ycannot.common.composable.theme.TextWhite20Sp
import com.github.ycannot.common.composable.theme.TextGray16Sp
import com.github.ycannot.common.composable.theme.TextOrangeMedium20Sp
import com.github.ycannot.common.composable.theme.TextEllipsisSemiBold20Sp
import com.github.ycannot.common.composable.theme.TextEllipsisMedium16Sp
import com.github.ycannot.common.composable.theme.orange
import com.github.ycannot.common.composable.theme.shadowBg
import com.github.ycannot.common.composable.views.CenteredCircularProgressIndicator
import com.github.ycannot.common.composable.views.ErrorMessage
import com.github.ycannot.core.extensions.orZero
import com.github.ycannot.domain.models.GetListDataResult
import com.github.ycannot.features.home.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    product: GetListDataResult.ProductResult?,
    vm: DetailViewModel = hiltViewModel()
) {
    val state by vm.viewState.collectAsState()

    LaunchedEffect(key1 = vm){
        vm.sendViewEvent(DetailContract.DetailEvent.GetDetailData(product?.productId.orEmpty()))
    }

    if (state.error != null) {
        ErrorMessage(
            hyperLinkText = stringResource(R.string.navigate_back),
            onClick = { navController.popBackStack() }
        )
        return
    }
    if (product == null) {
        return
    }
    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(
                title = stringResource(R.string.product_detail),
                onClickNavIcon = { navController.popBackStack() })
        },
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(it)
                .padding(top = 52.dp)
                .fillMaxSize()
        ) {
            val (scrollable, stickyBottom) = createRefs()
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .constrainAs(scrollable) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        //linkTo(top = scrollable.bottom, bottom = stickyBottom.top, bias = 0f)
                    }
            ) {
                DetailScreenImageSlider(
                    imageUrls = arrayListOf(state.productDetail?.image)
                )
                Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                    TextEllipsisExtraBold22Sp(text = product.name)
                    TextEllipsis14Sp(text = stringResource(id = R.string.product_id, state.productDetail?.productId.orEmpty()))
                    TextGray16Sp(
                        modifier = Modifier.padding(vertical = 24.dp),
                        text = state.productDetail?.description.orEmpty()
                    )
                }
            }

            StickyBottom(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(stickyBottom) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                salePrice = state.productDetail?.price.orZero().toDouble(),
                vm = vm
            )
        }
    }
}

@Composable
private fun StickyBottom(modifier: Modifier = Modifier, salePrice: Double, vm: DetailViewModel) {
    val context = LocalContext.current
    Surface(
        modifier = modifier,
        color = Color.LightGray,
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(
                start = 16.dp,
                end = 8.dp,
                top = 8.dp,
                bottom = 24.dp
            )
        ) {
            TextOrangeMedium20Sp(
                modifier = Modifier.padding(bottom = 8.dp),
                text = stringResource(R.string.price_in_try, salePrice)
            )
            Button(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                onClick = { vm.sendViewEvent(DetailContract.DetailEvent.Redirect(context)) },
                colors = ButtonDefaults.buttonColors(containerColor = orange)
            ) {
                TextWhite20Sp(
                    text = stringResource(R.string.contact_with_developer)
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun DetailScreenImageSlider(
    modifier: Modifier = Modifier,
    imageUrls: List<String?>
) {
    Box(modifier = modifier) {
        HorizontalPager(pageCount = imageUrls.size) {
            Box {
                DetailScreenImage(imageUrl = imageUrls[it].orEmpty())
                TextEllipsisMedium16Sp(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(8.dp)
                        .background(shadowBg),
                    text = "${it + 1}/${imageUrls.size}"
                )
            }
        }
    }
}

@Composable
private fun DetailScreenImage(modifier: Modifier = Modifier, imageUrl: String) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .build(),
        contentDescription = "",
        contentScale = ContentScale.Fit,
    ) {
        when (painter.state) {
            AsyncImagePainter.State.Empty,
            is AsyncImagePainter.State.Error,
            is AsyncImagePainter.State.Loading -> {
                CenteredCircularProgressIndicator()
            }

            is AsyncImagePainter.State.Success -> {

                SubcomposeAsyncImageContent(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Preview
@Composable
fun DetailScreenPreview(){

}