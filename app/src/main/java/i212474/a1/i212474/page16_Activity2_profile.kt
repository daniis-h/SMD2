package i212474.a1.i212474

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class page16_Activity2_profile : AppCompatActivity() {
    var city1 = arrayOf("Peshawar", "Rawalpindi", "Karachi", "Islamabad")
    var city2 = arrayOf("New York", "Washington", "Alaska", "Ohio")
    var country = arrayOf("USA", "Pakistan")
    var autoCompletecoutry: AutoCompleteTextView? = null
    var autoCompletecity: AutoCompleteTextView? = null
    var adaptercity: ArrayAdapter<String>? = null
    var adaptercountry: ArrayAdapter<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page16_activity2_profile)

        autoCompletecoutry = findViewById(R.id.coutry)
        autoCompletecity = findViewById(R.id.cityy)

        adaptercountry = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, country)
        autoCompletecoutry?.setAdapter(adaptercountry)

        autoCompletecoutry?.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            val s_country = parent.getItemAtPosition(position).toString()
            //Toast.makeText(applicationContext, "Item: $s_country", Toast.LENGTH_SHORT).show()
            if (s_country == "Pakistan") {
                adaptercity =
                    ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, city1)
                autoCompletecity?.setAdapter(adaptercity)

            } else {
                adaptercity =
                    ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, city2)
                autoCompletecity?.setAdapter(adaptercity)
            }
        })
        val back1 = findViewById<ImageView>(R.id.back)
        back1.setOnClickListener {
            finish() // This will close the current activity and go back to the previous one
        }
        val book =findViewById<Button>(R.id.create)
        book.setOnClickListener {
            Toast.makeText(applicationContext, "Profile is updated", Toast.LENGTH_SHORT).show()
        }
    }
}