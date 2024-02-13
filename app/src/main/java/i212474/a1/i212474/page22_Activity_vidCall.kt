package i212474.a1.i212474

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class page22_Activity_vidCall : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page22_vid_call)

        val back1 = findViewById<LinearLayout>(R.id.cross)
        back1.setOnClickListener {
            finish() // This will close the current activity and go back to the previous one
        }
    }

}