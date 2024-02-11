package i212474.a1.i212474

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class page6_Activity_search2 : AppCompatActivity() {
    private lateinit var img2: ImageView
    private lateinit var open:LinearLayout
    private var bool = false // Initial state of the image

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page6_search2) // Set your main activity layout here

        // Get the LinearLayout container where you want to add the items
        val container = findViewById<LinearLayout>(R.id.insertRecent2)

        // Example array of data
        val dataArray = arrayOf(
            ItemData("Sample 1", "Lead-Technology Officer", "Available", "$1500/session"),
            ItemData("Sample 2", "Lead-Technology Officer", "Not Available", "$1500/session"),
            ItemData("Sample 3", "Lead-Technology Officer", "Not Available", "$1500/session"),
            ItemData("Sample 4", "Lead-Technology Officer", "Available", "$1500/session"),
            ItemData("John Cooper", "UX-Designer@Google", "Available", "$1500/session"),
            // Add more items as needed
        )

        // Inflate the layout for each item in the array
        for (data in dataArray) {
            val itemView = layoutInflater.inflate(R.layout.profile_view_in_search, null)
            img2 = itemView.findViewById<ImageView>(R.id.like)

            // Populate the item view with data
            val nameTextView = itemView.findViewById<TextView>(R.id.name)
            nameTextView.text = data.name

            val postTextView = itemView.findViewById<TextView>(R.id.post)
            postTextView.text = data.post

            val availTextView = itemView.findViewById<TextView>(R.id.avail)
            availTextView.text = data.availability
            if (data.availability == "Not Available") {
                availTextView.setTextColor(resources.getColor(android.R.color.darker_gray))
            }

            val priceTextView = itemView.findViewById<TextView>(R.id.price)
            priceTextView.text = data.price

            // Add the inflated item view to the container
            container.addView(itemView)
        }
        open = findViewById<LinearLayout>(R.id.insertRecent2)
        // Initialize img2


        // Set OnClickListener for img2
        img2.setOnClickListener {
            // Toggle between the images
            if (bool) {
                img2.setImageResource(R.drawable.heart_e)
            } else {
                img2.setImageResource(R.drawable.heart)
            }

            // Update the state
            bool = !bool
        }
        val home = findViewById<LinearLayout>(R.id.home)

        home.setOnClickListener {
            val intent = Intent(this, page5_Activity_search::class.java)
            startActivity(intent)
        }
        val add = findViewById<LinearLayout>(R.id.plus)

        add.setOnClickListener {
            val intent = Intent(this, page9_Activity_add::class.java)
            startActivity(intent)
        }
        open.setOnClickListener {
            val intent = Intent(this, page7_Activity_profile::class.java)
            startActivity(intent)
        }
        val back1 = findViewById<ImageView>(R.id.back)
        back1.setOnClickListener {
            finish() // This will close the current activity and go back to the previous one
        }

        val chat = findViewById<LinearLayout>(R.id.chat)

        chat.setOnClickListener {
            val intent = Intent(this, page11_Activity2_chats::class.java)
            startActivity(intent)
        }
    }
}

data class ItemData(
    val name: String,
    val post: String,
    val availability: String,
    val price: String
)
