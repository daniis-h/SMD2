package i212474.a1.i212474

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class page7_Activity_profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page7_profile)

        // Initialize the TextView
        val gabout: TextView = findViewById(R.id.about)

        // Define the about text
        val about = "I am a passionate UX designer at Google with a focus on " +
                "creating user-centric and intuitive interfaces. With 10 years of " +
                "experience, I have had the opportunity to work on diverse " +
                "projects that have shaped my understanding of design " +
                "principles and user experience."


        gabout.text = about
        val review1 = findViewById<LinearLayout>(R.id.review)

        review1.setOnClickListener {
            val intent = Intent(this, page8_Activity_review::class.java)
            startActivity(intent)
        }
        val back1 = findViewById<ImageView>(R.id.back)
        back1.setOnClickListener {
            finish() // This will close the current activity and go back to the previous one
        }
        val book =findViewById<Button>(R.id.book_session)
        book.setOnClickListener {
            val intent = Intent(this, page10_Activity2_calendar::class.java)
            startActivity(intent)
        }
    }
}
