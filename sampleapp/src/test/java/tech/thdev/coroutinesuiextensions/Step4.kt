package tech.thdev.coroutinesuiextensions

import com.google.gson.annotations.SerializedName

/**
 * Data class
 */
class Step4 {

    @SerializedName("nnn") var name: String = ""

}

data class Test(@SerializedName("nnn") val name: String) {
}