package i212474.a1.i212474

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class page2_Activity_login : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page2_login)
        val forget: TextView = findViewById(R.id.forget)
        val login: Button = findViewById(R.id.login)
        var pass: EditText= findViewById(R.id.pass)
        var email: EditText= findViewById(R.id.email)
        forget.setOnClickListener {
            Toast.makeText(this, "link clicked", Toast.LENGTH_SHORT).show()
        }


        val signUp = findViewById<TextView>(R.id.create)
        signUp.text = "Sign Up"
        signUp.paintFlags = Paint.UNDERLINE_TEXT_FLAG or signUp.paintFlags

        login.setOnClickListener {
            val password = pass.text.toString()
            val mail = email.text.toString()
            if(password.isEmpty())
            {
                pass.setBackgroundResource(R.drawable.empty_textbox)
            }
            if(mail.isEmpty())
            {
                email.setBackgroundResource(R.drawable.empty_textbox)
            }
//            if(!password.isEmpty() and !mail.isEmpty())
//            {
//                val intent = Intent(this, Page4Home1_Activity::class.java)
//                startActivity(intent)
//            }
        }
        signUp.setOnClickListener {
            val intent = Intent(this, page3_Activity_create::class.java)
            startActivity(intent)
        }

    }
}