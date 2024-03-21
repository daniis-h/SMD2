package i212474.a1.i212474

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class page9_Activity_add : AppCompatActivity() {
    var option = arrayOf("Available", "Not Available")

    var autoComplete: AutoCompleteTextView? = null
    var adapter: ArrayAdapter<String>? = null


    private lateinit var user: String
    private  lateinit var ename: EditText
    private  lateinit var edisc: EditText
    private  lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page9_add)

        //dbRef= FirebaseDatabase.getInstance().getReference("user")
        val userID = intent.getStringExtra("USER_ID")
        user =userID.toString()


        ename= findViewById(R.id.name)
        edisc= findViewById(R.id.disc)

        dbRef= FirebaseDatabase.getInstance().getReference("mentor")

        val upload : Button= findViewById(R.id.upload)
        autoComplete = findViewById(R.id.status)


        adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, option)
        autoComplete?.setAdapter(adapter)


        upload.setOnClickListener {
            //Toast.makeText(applicationContext, "hello", Toast.LENGTH_SHORT).show()
            saveUserDetails()
        }

        val search = findViewById<LinearLayout>(R.id.search)

        search.setOnClickListener {
            val intent = Intent(this, page5_Activity_search::class.java)
            intent.putExtra("USER_ID", userID)
            startActivity(intent)
        }
        val home = findViewById<LinearLayout>(R.id.home)

        home.setOnClickListener {
            val intent = Intent(this, page4_Activity_home::class.java)
            intent.putExtra("USER_ID", userID)
            startActivity(intent)
        }
        val back1 = findViewById<ImageView>(R.id.back)
        back1.setOnClickListener {
            finish() // This will close the current activity and go back to the previous one
        }

        val chat = findViewById<LinearLayout>(R.id.chat)

        chat.setOnClickListener {
            val intent = Intent(this, page11_Activity2_chats::class.java)
            intent.putExtra("USER_ID", userID)
            startActivity(intent)
        }
        val vid = findViewById<LinearLayout>(R.id.video)

        vid.setOnClickListener {
            val intent = Intent(this, page15_Activity_video::class.java)
            intent.putExtra("USER_ID", userID)
            startActivity(intent)
        }
        val pic = findViewById<LinearLayout>(R.id.photo)

        pic.setOnClickListener {
            val intent = Intent(this, page14_Activity_camera::class.java)
            intent.putExtra("USER_ID", userID)
            startActivity(intent)
        }
        val profile=findViewById<LinearLayout>(R.id.profile)
        profile.setOnClickListener {
            val intent=Intent(this, page17_Activity_profileView::class.java)
            intent.putExtra("USER_ID", userID)
            startActivity(intent)
        }
    }

    private fun saveUserDetails() {
        val status = autoComplete?.text.toString()
        val disc = edisc.text.toString()
        val name =ename.text.toString()

        var check = true
        if (status.isEmpty()) {
            autoComplete?.error = "Please choose staus"
            check = false
        }
        if (disc.isEmpty()) {
            edisc.error = "Please enter discription"
            check = false
        }

        if (check == true) {
            if (name.isEmpty()) {
                val mentor = Mentors(user,status,"$1500/session",disc)
                dbRef.child(user).setValue(mentor)
                    .addOnCompleteListener {
                        Toast.makeText(this, "Mentor Added: "+user, Toast.LENGTH_SHORT).show()
                        ename.text.clear()
                        edisc.text.clear()
                        autoComplete?.text?.clear()
                    }
                    .addOnFailureListener { err ->
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
            }
            else
            {
                val mentor = Mentors(name,status,"$1500/session",disc)
                dbRef.child(name).setValue(mentor)
                    .addOnCompleteListener {
                        Toast.makeText(this, "Mentor Added: "+name, Toast.LENGTH_SHORT).show()
                        ename.text.clear()
                        edisc.text.clear()
                        autoComplete?.text?.clear()
                    }
                    .addOnFailureListener { err ->
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
            }


        }
    }
}