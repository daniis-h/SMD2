package i212474.a1.i212474

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout

class page9_Activity_add : AppCompatActivity() {
    var option = arrayOf("Available", "Not Available")

    var autoComplete: AutoCompleteTextView? = null
    var adapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page9_add)

        val upload : Button= findViewById(R.id.upload)
        autoComplete = findViewById(R.id.status)


        adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, option)
        autoComplete?.setAdapter(adapter)


//        upload.setOnClickListener {
//            //Toast.makeText(applicationContext, "hello", Toast.LENGTH_SHORT).show()
//            val intent = Intent(this, page2::class.java)
//            startActivity(intent)
//            finish()
//        }

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
        val vid = findViewById<LinearLayout>(R.id.video)

        vid.setOnClickListener {
            val intent = Intent(this, page15_Activity_video::class.java)
            startActivity(intent)
        }
        val pic = findViewById<LinearLayout>(R.id.photo)

        pic.setOnClickListener {
            val intent = Intent(this, page14_Activity_camera::class.java)
            startActivity(intent)
        }
        val profile=findViewById<LinearLayout>(R.id.profile)
        profile.setOnClickListener {
            val intent=Intent(this, page17_Activity_profileView::class.java)
            startActivity(intent)
        }
    }
}