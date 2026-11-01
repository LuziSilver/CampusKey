package com.example.campuskey

// SCHOOLS
// Each school needs a name and real-world coordinates so we
// can measure the distance from it to each accommodation listing.

data class School(
    val name: String,
    val latitude: Double,
    val longitude: Double
)

object SchoolData {
    val schools = listOf(
        School("University of Zambia (UNZA)", -15.3933, 28.3228),
        School("Copperbelt University (CBU)", -12.8238, 28.2062),
        School("Mulungushi University", -14.4469, 28.4257),
        School("Evelyn Hone College", -15.4119, 28.2871),
        School("Cavendish University Zambia", -15.3875, 28.3228)
    )
}

// ACCOMMODATION LISTINGS (fake/sample data for now)
data class Accommodation(
    val name: String,
    val type: String,
    val pricePerMonth: Int,
    val latitude: Double,
    val longitude: Double
)

object AccommodationData {
    val listings = listOf(
        Accommodation("Kalingalinga Student Rooms", "Shared Room", 800, -15.3980, 28.3300),
        Accommodation("Mass Media Lodge", "Self-Contained", 1500, -15.4000, 28.3100),
        Accommodation("Riverside Boarding House", "Single Room", 1000, -12.8300, 28.2100),
        Accommodation("Kitwe Central Flats", "Self-Contained", 1800, -12.8150, 28.2200),
        Accommodation("Kabwe Student Hostel", "Shared Room", 700, -14.4500, 28.4300),
        Accommodation("Town Centre Rooms", "Single Room", 1200, -15.4166, 28.2833)
    )
}

// DISTANCE CALCULATION
object DistanceCalculator {
    fun calculateDistanceKm(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val earthRadiusKm = 6371.0

        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)

        val a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                Math.sin(dLon / 2) * Math.sin(dLon / 2)

        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))

        return earthRadiusKm * c
    }
}
