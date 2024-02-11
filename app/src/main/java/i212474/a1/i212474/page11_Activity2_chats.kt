package i212474.a1.i212474

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class page11_Activity2_chats : AppCompatActivity() {
    private lateinit var img2: ImageView
    private lateinit var open:LinearLayout
    private var bool = false // Initial state of the image

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page11_activity2_chats) // Set your main activity layout here

        // Get the LinearLayout container where you want to add the items
        val container = findViewById<LinearLayout>(R.id.insertRecent2)

        // Example array of data
        val dataArray = arrayOf(
            Person("John Cooper", "1 new Message" ),
            Person("Jack Watson", "No new Message" ),
            Person("Emma Phillips", "No new Message" )
        )

        // Inflate the layout for each item in the array
        for (data in dataArray) {
            val itemView = layoutInflater.inflate(R.layout.chatperson, null)

            // Populate the item view with data
            val nameTextView = itemView.findViewById<TextView>(R.id.name)
            nameTextView.text = data.name


            val availTextView = itemView.findViewById<TextView>(R.id.new_msg)
            availTextView.text = data.msgs
            if (data.msgs == "No new Message") {
                availTextView.setTextColor(resources.getColor(android.R.color.darker_gray))
            }
            else
                availTextView.setTextColor(resources.getColor(android.R.color.holo_red_dark))
            // Add the inflated item view to the container
            container.addView(itemView)
        }
        open = findViewById<LinearLayout>(R.id.insertRecent2)
        // Initialize img2



        val home = findViewById<LinearLayout>(R.id.home)

        home.setOnClickListener {
            val intent = Intent(this, page4_Activity_home::class.java)
            startActivity(intent)
        }
        val add = findViewById<LinearLayout>(R.id.plus)

        add.setOnClickListener {
            val intent = Intent(this, page9_Activity_add::class.java)
            startActivity(intent)
        }
        val search = findViewById<LinearLayout>(R.id.search)

        search.setOnClickListener {
            val intent = Intent(this, page5_Activity_search::class.java)
            startActivity(intent)
        }
//        open.setOnClickListener {
//            val intent = Intent(this, page7_Activity_profile::class.java)
//            startActivity(intent)
//        }
        val back1 = findViewById<ImageView>(R.id.back)
        back1.setOnClickListener {
            finish() // This will close the current activity and go back to the previous one
        }
    }
}

data class Person(
    val name: String,
    val msgs: String,
)
