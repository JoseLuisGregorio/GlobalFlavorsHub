package com.challenge.globalFlavorsHub.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class GlobalFlavorsHubResponse<T>(
    @SerializedName("code") var code: Int = 0,
    @SerializedName("data") var data: @RawValue T? = null,
    @SerializedName("status") var status: String = "",
) : Parcelable
