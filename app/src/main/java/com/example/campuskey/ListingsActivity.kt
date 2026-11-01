package com.example.campuskey

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ListingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listings)

        // finish() closes this screen and returns to the school entry screen
        findViewById<Button>(R.id.backButton).setOnClickListener { finish() }

        val schoolIndex = intent.getIntExtra("SCHOOL_INDEX", 0)
        val selectedSchool = SchoolData.schools[schoolIndex]

        findViewById<TextView>(R.id.listingsTitle).text =
            getString(R.string.listings_title_format, selectedSchool.name)

        val container = findViewById<LinearLayout>(R.id.listingsContainer)
        val inflater = LayoutInflater.from(this)

        // Work out the distance from the selected school to every listing,
        // then show the closest ones first.
        val sortedListings = AccommodationData.listings
            .map { listing ->
                val distanceKm = DistanceCalculator.calculateDistanceKm(
                    selectedSchool.latitude, selectedSchool.longitude,
                    listing.latitude, listing.longitude
                )
                Pair(listing, distanceKm)
            }
            .sortedBy { it.second }

        for ((listing, distanceKm) in sortedListings) {
            val itemView = inflater.inflate(R.layout.item_accommodation, container, false)

            itemView.findViewById<TextView>(R.id.itemName).text = listing.name
            itemView.findViewById<TextView>(R.id.itemType).text = listing.type
            itemView.findViewById<TextView>(R.id.itemPrice).text =
                getString(R.string.price_per_month_format, listing.pricePerMonth)
            itemView.findViewById<TextView>(R.id.itemDistance).text =
                getString(R.string.distance_away_format, distanceKm)

            container.addView(itemView)
        }
    }
}
