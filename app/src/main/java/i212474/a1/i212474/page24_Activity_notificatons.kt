package i212474.a1.i212474

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class page24_Activity_notificatons : AppCompatActivity() {
    private lateinit var img2: ImageView
    private lateinit var open: LinearLayout
    private var bool = false // Initial state of the image

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page24_notificatons) // Set your main activity layout here

        // Example array of recent searches
        val recentSearchesArray = arrayOf(
            "You have added a mentor, Alex. Thank you!",
            "Your favorites list is expanding. Impressive",
            "Your favorites list is expanding. Impressive",
            "Your favorite mentor is Online. Say Hi to him.",
            "Thank you for booking a session. Good Luck."
        )

        // Create a layout inflater
        val inflater = LayoutInflater.from(this)

        // Retrieve the main layout
        val mainLayout =
            findViewById<LinearLayout>(R.id.insertRecent2) // Change LinearLayout to your main layout type
        var counter = 1
        // Loop through the recent searches array
        for (search in recentSearchesArray) {
            // Inflate the recent_search.xml layout for each search
            val recentSearchLayout = inflater.inflate(R.layout.notifications, null)

            // Retrieve the TextView and ImageButton from the inflated layout
            val recentSearchTextView = recentSearchLayout.findViewById<TextView>(R.id.recentSearch)
            val clearSearchImageButton =
                recentSearchLayout.findViewById<ImageButton>(R.id.clearSearch)

            // Set the text of the TextView
            recentSearchTextView.text = search
            if (counter == 4)
                recentSearchTextView.setTypeface(null, Typeface.BOLD)
            // Set OnClickListener to the clear button to remove the recent search
            clearSearchImageButton.setOnClickListener {
                // Remove the parent layout (recent search item) from the main layout
                counter++
                mainLayout.removeView(recentSearchLayout)

            }

            val clear = findViewById<TextView>(R.id.clear)
            clear.setOnClickListener {
                val linearLayout = findViewById<LinearLayout>(R.id.insertRecent2)
                linearLayout.removeAllViews()
            }
            // Add the inflated layout to the main layout
            mainLayout.addView(recentSearchLayout)

        }
        val back1 = findViewById<ImageView>(R.id.back)
        back1.setOnClickListener {
            finish() // This will close the current activity and go back to the previous one
        }
    }
}


