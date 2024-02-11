package i212474.a1.i212474

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast

class page8_Activity_review : AppCompatActivity() {
    lateinit var img1: ImageView
    lateinit var img2: ImageView
    lateinit var img3: ImageView
    lateinit var img4: ImageView
    lateinit var img5: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page8_review)

        img1 = findViewById(R.id.s1)
        img2 = findViewById(R.id.s2)
        img3 = findViewById(R.id.s3)
        img4 = findViewById(R.id.s4)
        img5 = findViewById(R.id.s5)

        img1.setOnClickListener {
            img1.setImageResource(R.drawable.star)
            img2.setImageResource(R.drawable.empty_star)
            img3.setImageResource(R.drawable.empty_star)
            img4.setImageResource(R.drawable.empty_star)
            img5.setImageResource(R.drawable.empty_star)
        }
        img2.setOnClickListener {
            img1.setImageResource(R.drawable.star)
            img2.setImageResource(R.drawable.star)
            img3.setImageResource(R.drawable.empty_star)
            img4.setImageResource(R.drawable.empty_star)
            img5.setImageResource(R.drawable.empty_star)
        }
        img3.setOnClickListener {
            img1.setImageResource(R.drawable.star)
            img2.setImageResource(R.drawable.star)
            img3.setImageResource(R.drawable.star)
            img4.setImageResource(R.drawable.empty_star)
            img5.setImageResource(R.drawable.empty_star)
        }
        img4.setOnClickListener {
            img1.setImageResource(R.drawable.star)
            img2.setImageResource(R.drawable.star)
            img3.setImageResource(R.drawable.star)
            img4.setImageResource(R.drawable.star)
            img5.setImageResource(R.drawable.empty_star)
        }
        img5.setOnClickListener {
            img1.setImageResource(R.drawable.star)
            img2.setImageResource(R.drawable.star)
            img3.setImageResource(R.drawable.star)
            img4.setImageResource(R.drawable.star)
            img5.setImageResource(R.drawable.star)
        }
        val back1 = findViewById<ImageView>(R.id.back)
        back1.setOnClickListener {
            finish() // This will close the current activity and go back to the previous one
        }
        val book =findViewById<Button>(R.id.feedback)
        book.setOnClickListener {
            Toast.makeText(applicationContext, "Feedback is submitted", Toast.LENGTH_SHORT).show()
        }

    }
}