package i212474.a1.i212474

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout

class page4_Activity_home : AppCompatActivity() {
    lateinit var img1: ImageView
    lateinit var img2: ImageView
    lateinit var img3: ImageView
    lateinit var img4: ImageView
    lateinit var img5: ImageView
    lateinit var img6: ImageView
    lateinit var img7: ImageView
    lateinit var img8: ImageView
    lateinit var img9: ImageView
    lateinit var search : ImageView
    val bool = BooleanArray(9) { false }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page4_home)

        // Initialize ImageView variables after setContentView
        img1 = findViewById(R.id.john_h)
        img2 = findViewById(R.id.martina_h)
        img3 = findViewById(R.id.micheal1_h)
        img4 = findViewById(R.id.jane1_h)
        img5 = findViewById(R.id.jane_h)
        img6 = findViewById(R.id.emma_h)
        img7 = findViewById(R.id.alex_h)
        img8 = findViewById(R.id.alex1_h)
        img9 = findViewById(R.id.micheal_h)



        img1.setOnClickListener {
            // Handle click for img1
            if (bool[0] == false) {
                img1.setImageResource(R.drawable.heart)
                bool[0]=true
            }
            else
            {
                img1.setImageResource(R.drawable.heart_e)
                bool[0]=false
            }


        }

        img2.setOnClickListener {
            // Handle click for img1
            if (bool[1] == false) {
                img2.setImageResource(R.drawable.heart)
                bool[1]=true
            }
            else
            {
                img2.setImageResource(R.drawable.heart_e)
                bool[1]=false
            }


        }
        img3.setOnClickListener {
            // Handle click for img1
            if (bool[2] == false) {
                img3.setImageResource(R.drawable.heart)
                bool[2]=true
            }
            else
            {
                img3.setImageResource(R.drawable.heart_e)
                bool[2]=false
            }


        }
        img4.setOnClickListener {
            // Handle click for img1
            if (bool[3] == false) {
                img4.setImageResource(R.drawable.heart)
                bool[3]=true
            }
            else
            {
                img4.setImageResource(R.drawable.heart_e)
                bool[3]=false
            }


        }
        img5.setOnClickListener {
            // Handle click for img1
            if (bool[4] == false) {
                img5.setImageResource(R.drawable.heart)
                bool[4]=true
            }
            else
            {
                img5.setImageResource(R.drawable.heart_e)
                bool[4]=false
            }


        }
        img6.setOnClickListener {
            // Handle click for img1
            if (bool[5] == false) {
                img6.setImageResource(R.drawable.heart)
                bool[5]=true
            }
            else
            {
                img6.setImageResource(R.drawable.heart_e)
                bool[5]=false
            }


        }
        img7.setOnClickListener {
            // Handle click for img1
            if (bool[6] == false) {
                img7.setImageResource(R.drawable.heart)
                bool[6]=true
            }
            else
            {
                img7.setImageResource(R.drawable.heart_e)
                bool[6]=false
            }


        }
        img8.setOnClickListener {
            // Handle click for img1
            if (bool[7] == false) {
                img8.setImageResource(R.drawable.heart)
                bool[7]=true
            }
            else
            {
                img8.setImageResource(R.drawable.heart_e)
                bool[7]=false
            }


        }
        img9.setOnClickListener {
            // Handle click for img1
            if (bool[8] == false) {
                img9.setImageResource(R.drawable.heart)
                bool[8]=true
            }
            else
            {
                img9.setImageResource(R.drawable.heart_e)
                bool[8]=false
            }


        }
        val search = findViewById<LinearLayout>(R.id.search)

        search.setOnClickListener {
            val intent = Intent(this, page5_Activity_search::class.java)
            startActivity(intent)
        }
        val add = findViewById<LinearLayout>(R.id.plus)

        add.setOnClickListener {
            val intent = Intent(this, page9_Activity_add::class.java)
            startActivity(intent)
        }

        val chat = findViewById<LinearLayout>(R.id.chat)

        chat.setOnClickListener {
            val intent = Intent(this, page11_Activity2_chats::class.java)
            startActivity(intent)
        }
        val profile=findViewById<LinearLayout>(R.id.profile)
        profile.setOnClickListener {
            val intent=Intent(this, page17_Activity_profileView::class.java)
            startActivity(intent)
        }


    }
}