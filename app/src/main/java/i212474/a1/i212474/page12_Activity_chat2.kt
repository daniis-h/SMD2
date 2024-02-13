package i212474.a1.i212474

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView

class page12_Activity_chat2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page12_chat2)
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

        val chat = findViewById<LinearLayout>(R.id.chat)

        chat.setOnClickListener {
            val intent = Intent(this, page11_Activity2_chats::class.java)
            startActivity(intent)
        }

        val vidcall=findViewById<ImageView>(R.id.vidaCal)
        vidcall.setOnClickListener {
            val intent=Intent(this, page22_Activity_vidCall::class.java)
            startActivity(intent)
        }

        val call=findViewById<ImageView>(R.id.call)
        call.setOnClickListener {
            val intent=Intent(this, page23_Activity_audioCall::class.java)
            startActivity(intent)
        }

        val back1 = findViewById<ImageView>(R.id.back)
        back1.setOnClickListener {
            finish() // This will close the current activity and go back to the previous one
        }
        val profile=findViewById<LinearLayout>(R.id.profile)
        profile.setOnClickListener {
            val intent=Intent(this, page17_Activity_profileView::class.java)
            startActivity(intent)
        }

    }
}