package com.example.domain

import com.google.gson.annotations.SerializedName

data class Response(@SerializedName("results") val results: List<Character>)
