package com.example.moviecatalog.network.dataclasses.models

@kotlinx.serialization.Serializable
data class PageInfoModel(
    val pageSize: Int,
    val pageCount: Int,
    val currentPage: Int
)
