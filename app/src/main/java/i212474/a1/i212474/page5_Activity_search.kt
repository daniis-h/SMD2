package i212474.a1.i212474

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class page5_Activity_search : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page5_search) // Set your main activity layout here

        // Example array of recent searches
        val recentSearchesArray = arrayOf("Mentor 1", "Mentor 2", "Mentor 3")

        // Create a layout inflater
        val inflater = LayoutInflater.from(this)

        // Retrieve the main layout
        val mainLayout = findViewById<LinearLayout>(R.id.insertRecent) // Change LinearLayout to your main layout type

        // Loop through the recent searches array
        for (search in recentSearchesArray) {
            // Inflate the recent_search.xml layout for each search
            val recentSearchLayout = inflater.inflate(R.layout.recent_searches, null)

            // Retrieve the TextView and ImageButton from the inflated layout
            val recentSearchTextView = recentSearchLayout.findViewById<TextView>(R.id.recentSearch)
            val clearSearchImageButton = recentSearchLayout.findViewById<ImageButton>(R.id.clearSearch)

            // Set the text of the TextView
            recentSearchTextView.text = search

            // Set OnClickListener to the clear button to remove the recent search
            clearSearchImageButton.setOnClickListener {
                // Remove the parent layout (recent search item) from the main layout
                mainLayout.removeView(recentSearchLayout)
            }

            // Add the inflated layout to the main layout
            mainLayout.addView(recentSearchLayout)
        }
    }
}
