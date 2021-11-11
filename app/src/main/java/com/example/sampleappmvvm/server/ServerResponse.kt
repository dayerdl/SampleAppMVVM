package com.example.sampleappmvvm.server

import com.google.gson.annotations.SerializedName

data class ServerResponse(@SerializedName("Server_title") val title: String)
