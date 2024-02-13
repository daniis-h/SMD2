package i212474.a1.i212474

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            val intent = Intent(this,  page2_Activity_login ::class.java)
            startActivity(intent)
            finish() // Optional: Finish the current activity if you don't want to go back to it
        }, 5000)
    }


}