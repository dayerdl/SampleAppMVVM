package com.example.sampleappmvvm.model

import com.google.gson.annotations.SerializedName

data class Search(
    val itineraries: List<Itinerary>,
    val legs: List<Leg>
)

data class Itinerary(
    val id: String,
    val legs: List<String>,
    val price: String,
    val agent: String,

    @SerializedName("agent_rating")
    val agentRating: Double
)

data class Leg(
    val id: String,

    @SerializedName("departure_airport")
    val departureAirport: String,

    @SerializedName("arrival_airport")
    val arrivalAirport: String,

    @SerializedName("departure_time")
    val departureTime: String,

    @SerializedName("arrival_time")
    val arrivalTime: String,

    val stops: Long,

    @SerializedName("airline_name")
    val airlineName: String,

    @SerializedName("airline_id")
    val airlineID: String,

    @SerializedName("duration_mins")
    val durationMins: Long
)
