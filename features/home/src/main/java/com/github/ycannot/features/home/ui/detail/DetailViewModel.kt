package com.github.ycannot.features.home.ui.detail


import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import com.github.ycannot.common.composable.base.BaseViewModel
import com.github.ycannot.domain.usecases.GetItemDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getItemDetailUseCase: GetItemDetailUseCase
) : BaseViewModel<DetailContract.DetailViewState, DetailContract.DetailEvent>(DetailContract.DetailViewState()) {

    override fun sendViewEvent(event: DetailContract.DetailEvent) {
        when (event) {
            is DetailContract.DetailEvent.GetDetailData -> getProductDetail(event.productId)
            is DetailContract.DetailEvent.Redirect -> redirect(event.context)
        }
    }

    private fun getProductDetail(productId: String) {
        if (isNotProductIdValid(productId)) return

        sendRequest(
            request = { getItemDetailUseCase(GetItemDetailUseCase.Params(productId)) },
            onSuccess = {
                updateState(
                    viewState.value.copy(productDetail = it)
                )
            },
            onError = {
                updateState(
                    viewState.value.copy(
                        error = it
                    )
                )
            }
        )
    }

    private fun isNotProductIdValid(productId: String?): Boolean {
        return productId.isNullOrEmpty().also {
            if (it && viewState.value.error == null) {
                updateState(viewState.value.copy(error = Exception()))
            }
        }
    }

    private fun redirect(context: Context): Boolean {
        val url = "http://linkedin.com/in/yigit-can-yilmaz"
        return try {
            val i = Intent(Intent.ACTION_VIEW)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            i.data = Uri.parse(url)
            ContextCompat.startActivity(context, i, bundleOf())
            true
        } catch (_: Exception) { false }

    }

}