package com.github.ycannot.data.local

import com.github.ycannot.core.extensions.cast
import com.github.ycannot.core.extensions.serialize
import com.github.ycannot.core.managers.interfaces.PreferenceManager
import com.github.ycannot.data.models.response.GetListResponse

class TtechCacheManagerImpl(
    private var preferenceManager: PreferenceManager
): TtechCacheManager {
    override var getListResponse: GetListResponse?
        get() = preferenceManager.read(GET_LIST_RESPONSE, "").cast()
        set(value) {preferenceManager.write(GET_LIST_RESPONSE, value.serialize())}

    companion object{
        val GET_LIST_RESPONSE = "GET_LIST_RESPONSE"
    }

}