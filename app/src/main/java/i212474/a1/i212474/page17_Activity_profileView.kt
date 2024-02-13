package i212474.a1.i212474

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class page17_Activity_profileView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page17_profile_view)
        val search = findViewById<LinearLayout>(R.id.search)

        search.setOnClickListener {
            val intent = Intent(this, page5_Activity_search::class.java)
            startActivity(intent)
        }
        val home = findViewById<LinearLayout>(R.id.home)

        home.setOnClickListener {
            val intent = Intent(this, page4_Activity_home::class.java)
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
        val add = findViewById<LinearLayout>(R.id.plus)

        add.setOnClickListener {
            val intent = Intent(this, page9_Activity_add::class.java)
            startActivity(intent)
        }

        val edit1 = findViewById<LinearLayout>(R.id.edit1)

        edit1.setOnClickListener {
            val intent = Intent(this, page16_Activity2_profile::class.java)
            startActivity(intent)
        }
        val edit = findViewById<LinearLayout>(R.id.edit)

        edit.setOnClickListener {
            val intent = Intent(this, page16_Activity2_profile::class.java)
            startActivity(intent)
        }
        val book = findViewById<Button>(R.id.book)

        book.setOnClickListener {
            val intent = Intent(this, page18_Activity_bookSession::class.java)
            startActivity(intent)
        }
        val noti = findViewById<TextView>(R.id.notifica)

        noti.setOnClickListener {
            val intent = Intent(this, page24_Activity_notificatons::class.java)
            startActivity(intent)
        }
    }
}