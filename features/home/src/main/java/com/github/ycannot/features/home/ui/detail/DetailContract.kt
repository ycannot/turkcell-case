package com.github.ycannot.features.home.ui.detail

import android.content.Context
import com.github.ycannot.common.composable.base.ViewEvent
import com.github.ycannot.common.composable.base.ViewState
import com.github.ycannot.domain.models.GetItemDetailResult

class DetailContract {
    data class DetailViewState(
        override var isLoadingVisible: Boolean = true,
        override var error: Exception? = null,
        var productDetail: GetItemDetailResult? = null
    ) : ViewState

    sealed class DetailEvent: ViewEvent {
        data class GetDetailData(val productId: String) : DetailEvent()
        data class Redirect(val context: Context) : DetailEvent()
    }
}

