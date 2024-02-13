package i212474.a1.i212474

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout

class page23_Activity_audioCall : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page23_audio_call)
        val back1 = findViewById<LinearLayout>(R.id.cross)
        back1.setOnClickListener {
            finish() // This will close the current activity and go back to the previous one
        }
    }
}