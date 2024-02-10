package i212474.a1.i212474

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class page3_Activity_create : AppCompatActivity() {

    var city1 = arrayOf("Peshawar", "Rawalpindi", "Karachi", "Islamabad")
    var city2 = arrayOf("New York", "Washington", "Alaska", "Ohio")
    var country = arrayOf("USA", "Pakistan")
    var autoCompletecoutry: AutoCompleteTextView? = null
    var autoCompletecity: AutoCompleteTextView? = null
    var adaptercity: ArrayAdapter<String>? = null
    var adaptercountry: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page3_create)

        val login : TextView= findViewById(R.id.loginC)
        autoCompletecoutry = findViewById(R.id.coutry)
        autoCompletecity = findViewById(R.id.cityy)

        adaptercountry = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, country)
        autoCompletecoutry?.setAdapter(adaptercountry)

        autoCompletecoutry?.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
            val s_country = parent.getItemAtPosition(position).toString()
            //Toast.makeText(applicationContext, "Item: $s_country", Toast.LENGTH_SHORT).show()
            if(s_country=="Pakistan")
            {
                adaptercity = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, city1)
                autoCompletecity?.setAdapter(adaptercity)

            }
            else{
                adaptercity = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, city2)
                autoCompletecity?.setAdapter(adaptercity)
            }


        })
        login.setOnClickListener {
            //Toast.makeText(applicationContext, "hello", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, page2_Activity_login::class.java)
            startActivity(intent)
            finish()
        }
    }
}
