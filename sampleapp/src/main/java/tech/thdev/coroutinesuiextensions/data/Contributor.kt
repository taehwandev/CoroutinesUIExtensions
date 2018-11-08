package tech.thdev.coroutinesuiextensions.data

import com.google.gson.annotations.SerializedName

data class Contributor(
        @SerializedName("login") val login: String,
        @SerializedName("contributions") val contributions: Int
)