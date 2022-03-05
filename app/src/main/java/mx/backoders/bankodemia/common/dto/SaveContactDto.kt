package mx.backoders.bankodemia.common.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SaveContactDto(
    val shortName: String,
    val user: String
)
