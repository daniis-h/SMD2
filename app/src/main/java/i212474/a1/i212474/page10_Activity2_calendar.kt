package i212474.a1.i212474

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class page10_Activity2_calendar : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private var defulturi:String="https://firebasestorage.googleapis.com/v0/b/mentor-me-6558f.appspot.com/o/Pictures%2Fextra?alt=media&token=bf8bc9bc-f2c8-4faf-bd35-46b5054fd72e"

    private lateinit var user: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page10_activity2_calendar)

        database= FirebaseDatabase.getInstance().getReference("user")
        val userID = intent.getStringExtra("USER_ID")
        val mentorID = intent.getStringExtra("PROFILE")
        user =userID.toString()
        if (mentorID != null) {
            fetchUser(mentorID)
        }

        val calendarView = findViewById<CalendarView>(R.id.calender)
        val dateF = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val currentD = java.util.Date()
        var formattedDate= dateF.format(currentD)


        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // Create a Calendar instance and set the selected date
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)

            // Format the date as Day-Month-Year
            val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            formattedDate = dateFormat.format(calendar.time).toString()

            // Display the formatted date
            //Toast.makeText(this, "Selected Date: $formattedDate", Toast.LENGTH_SHORT).show()
        }
        var stime="11.00AM"

        val t10 = findViewById<TextView>(R.id.t10)
        val t11 = findViewById<TextView>(R.id.t11)
        val t12 = findViewById<TextView>(R.id.t12)
        t11.setBackgroundColor(Color.parseColor("#42A399"))
        t10.setBackgroundColor(Color.GRAY)
        t12.setBackgroundColor(Color.GRAY)
        t10.setOnClickListener {
            stime="10.00AM"
            t10.setBackgroundColor(Color.parseColor("#42A399"))
            t11.setBackgroundColor(Color.GRAY)
            t12.setBackgroundColor(Color.GRAY)
        }
        t11.setOnClickListener {
            stime="11.00AM"
            t11.setBackgroundColor(Color.parseColor("#42A399"))
            t10.setBackgroundColor(Color.GRAY)
            t12.setBackgroundColor(Color.GRAY)
        }
        t12.setOnClickListener {
            stime="12.00AM"
            t12.setBackgroundColor(Color.parseColor("#42A399"))
            t11.setBackgroundColor(Color.GRAY)
            t10.setBackgroundColor(Color.GRAY)
        }


        val back1 = findViewById<ImageView>(R.id.back)
        back1.setOnClickListener {
            finish() // This will close the current activity and go back to the previous one
        }
        val book =findViewById<Button>(R.id.booked)
        book.setOnClickListener {

            Boook(userID.toString(), formattedDate, stime,mentorID.toString() )

        }
        val  chat=findViewById<LinearLayout>(R.id.chat)
        chat.setOnClickListener {
            val intent=Intent(this, page12_Activity_chat2::class.java)
            intent.putExtra("USER_ID", user)
            intent.putExtra("PROFILE", mentorID.toString())
            startActivity(intent)
        }
        val vidcall=findViewById<LinearLayout>(R.id.vidaCal)
        vidcall.setOnClickListener {
            val intent=Intent(this, page22_Activity_vidCall::class.java)
            intent.putExtra("USER_ID", user)
            intent.putExtra("PROFILE", mentorID.toString())
            startActivity(intent)
        }

        val call=findViewById<LinearLayout>(R.id.call)
        call.setOnClickListener {
            val intent=Intent(this, page23_Activity_audioCall::class.java)
            intent.putExtra("USER_ID", user)
            intent.putExtra("PROFILE", mentorID.toString())
            startActivity(intent)
        }

    }

    private fun fetchUser(userID: String) {
        database = FirebaseDatabase.getInstance().getReference("mentor")
        database.child(userID).get().addOnSuccessListener {
            val uid = it.child("mail").value.toString()
            //********** finding name *******
            val namebase = FirebaseDatabase.getInstance().getReference("user")
            val nameTextView = findViewById<TextView>(R.id.name)
            namebase.child(uid.toString()).get().addOnSuccessListener { dataSnapshot ->
                nameTextView.text = dataSnapshot.child("namee").value.toString()
                if(nameTextView.text=="null")
                    nameTextView.text=uid.toString()
                val pic=findViewById<com.google.android.material.imageview.ShapeableImageView>(R.id.profilepic)
                var uri=dataSnapshot.child("imguri").value.toString()
                if(uri.length>10)
                    Glide.with(this)
                        .load(uri)
                        .into(pic)
                else
                    Glide.with(this)
                        .load(defulturi)
                        .into(pic)


            }.addOnFailureListener { exception ->
                // Handle failure if needed
            }


        }

    }
    private fun Boook(userID: String, date: String, time:String, mentor:String) {
        val database = FirebaseDatabase.getInstance().getReference("book")

        database.child(userID).child(mentor).push()
        val btime=BookSession(date, time)
        database.child(userID).child(mentor).setValue(btime)
        Toast.makeText(this,"Appointment is booked", Toast.LENGTH_SHORT).show()


    }

}