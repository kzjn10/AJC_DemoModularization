package com.anhndt.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseListResponse<T>(
    @SerialName("results")
    val data: T,

    @SerialName("page")
    val page: Int,

    @SerialName("total_results")
    val totalResult: Int?,

    @SerialName("total_pages")
    val totalPages: Int?,
)