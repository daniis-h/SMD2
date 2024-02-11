package i212474.a1.i212474

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast

class page10_Activity2_calendar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page10_activity2_calendar)

        val back1 = findViewById<ImageView>(R.id.back)
        back1.setOnClickListener {
            finish() // This will close the current activity and go back to the previous one
        }
        val book =findViewById<Button>(R.id.booked)
        book.setOnClickListener {
            Toast.makeText(applicationContext, "Appointment is booked", Toast.LENGTH_SHORT).show()
        }
        val  chat=findViewById<LinearLayout>(R.id.chat)
        chat.setOnClickListener {
            val intent=Intent(this, page12_Activity_chat2::class.java)
            startActivity(intent)
        }
    }
}