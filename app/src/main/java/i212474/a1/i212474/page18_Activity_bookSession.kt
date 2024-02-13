package i212474.a1.i212474

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class page18_Activity_bookSession : AppCompatActivity() {

    private var bool = false // Initial state of the image
    private lateinit var open:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page18_book_session) // Set your main activity layout here

        // Get the LinearLayout container where you want to add the items
        val container = findViewById<LinearLayout>(R.id.insertRecent2)

        // Example array of data
        val dataArray = arrayOf(

            ItemData2("John Cooper", "UX-Designer@Google", "24th Dec 2023", "1:00 pm"),
            ItemData2("Emma Phillips", "Android Developer", "1st jan 2024", "08:50 pm")
            // Add more items as needed
        )

        // Inflate the layout for each item in the array
        for (data in dataArray) {
            val itemView = layoutInflater.inflate(R.layout.book_session_people, null)


            // Populate the item view with data
            val nameTextView = itemView.findViewById<TextView>(R.id.name)
            nameTextView.text = data.name

            val postTextView = itemView.findViewById<TextView>(R.id.post)
            postTextView.text = data.post

            val availTextView = itemView.findViewById<TextView>(R.id.date)
            availTextView.text = data.date


            val priceTextView = itemView.findViewById<TextView>(R.id.time)
            priceTextView.text = data.time

            // Add the inflated item view to the container
            container.addView(itemView)
        }
        open = findViewById<LinearLayout>(R.id.insertRecent2)
        // Initialize img2




        val back1 = findViewById<ImageView>(R.id.back)
        back1.setOnClickListener {
            finish() // This will close the current activity and go back to the previous one
        }


    }
}

data class ItemData2(
    val name: String,
    val post: String,
    val date: String,
    val time: String
)
