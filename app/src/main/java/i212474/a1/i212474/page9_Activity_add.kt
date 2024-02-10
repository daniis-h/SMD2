package i212474.a1.i212474

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button

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
    }
}