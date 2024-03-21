package i212474.a1.i212474

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class page12_Activity_chat2 : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var user: String
    private lateinit var personID:String
    private var defulturi:String="https://firebasestorage.googleapis.com/v0/b/mentor-me-6558f.appspot.com/o/Pictures%2Fextra?alt=media&token=bf8bc9bc-f2c8-4faf-bd35-46b5054fd72e"
    private var msgid:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page12_chat2)

        database= FirebaseDatabase.getInstance().getReference("user")
        val userID = intent.getStringExtra("USER_ID")
        val mentorID = intent.getStringExtra("PROFILE")
        user =userID.toString()
        personID=mentorID.toString()
        if (mentorID != null) {
            fetchUser(mentorID)
        }

        val assignAnID = if (user.hashCode() < mentorID.hashCode()) {
            "$user$mentorID"
        } else {
            "$mentorID$user"
        }

//------------------------clicks-----------------
        val send=findViewById<ImageView>(R.id.send_msg)
        send.setOnClickListener{
            val msg = findViewById<EditText>(R.id.message)
            if(msg.text.isNotEmpty())
            {
                sendMessage(mentorID.toString())
            }
            LoadMsg(assignAnID.toString())

        }




        LoadMsg(assignAnID.toString())


        // Create an ArrayList of chatMessageModel objects


        val home = findViewById<LinearLayout>(R.id.home)

        home.setOnClickListener {
            val intent = Intent(this, page4_Activity_home::class.java)
            startActivity(intent)
        }
        val add = findViewById<LinearLayout>(R.id.plus)

        add.setOnClickListener {
            val intent = Intent(this, page9_Activity_add::class.java)
            startActivity(intent)
        }
        val search = findViewById<LinearLayout>(R.id.search)

        search.setOnClickListener {
            val intent = Intent(this, page5_Activity_search::class.java)
            startActivity(intent)
        }

        val chat = findViewById<LinearLayout>(R.id.chat)

        chat.setOnClickListener {
            val intent = Intent(this, page11_Activity2_chats::class.java)
            startActivity(intent)
        }

        val vidcall=findViewById<ImageView>(R.id.vidaCal)
        vidcall.setOnClickListener {
            val intent=Intent(this, page22_Activity_vidCall::class.java)
            startActivity(intent)
        }

        val call=findViewById<ImageView>(R.id.call)
        call.setOnClickListener {
            val intent=Intent(this, page23_Activity_audioCall::class.java)
            startActivity(intent)
        }

        val back1 = findViewById<ImageView>(R.id.back)
        back1.setOnClickListener {
            finish() // This will close the current activity and go back to the previous one
        }
        val profile=findViewById<LinearLayout>(R.id.profile)
        profile.setOnClickListener {
            val intent=Intent(this, page17_Activity_profileView::class.java)
            startActivity(intent)
        }

        //--------------keyboard open hide footer
        val footerView = findViewById<LinearLayout>(R.id.footer)
        val footer = findViewById<LinearLayout>(R.id.plus)
        val head = findViewById<LinearLayout>(R.id.header)
        // Add a global layout listener to detect changes in layout
        val rootView = findViewById<LinearLayout>(R.id.root)
        rootView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val rect = Rect()
                rootView.getWindowVisibleDisplayFrame(rect)

                // Get the height of the visible content area
                val screenHeight = rootView.rootView.height
                val keypadHeight = screenHeight - rect.bottom

                if (keypadHeight > screenHeight * 0.15) { // Keyboard is open
                    footerView.visibility = View.GONE
                    footer.visibility = View.GONE
                    head.visibility = View.VISIBLE
                } else { // Keyboard is closed
                    footerView.visibility = View.VISIBLE
                    footer.visibility = View.VISIBLE
                }
            }
        })

    }


////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////
    private fun sendMessage(receiverID: String) {
        val msg = findViewById<EditText>(R.id.message)
        val time = getCurrentTime()


        val assignAnID = if (user.hashCode() < receiverID.hashCode()) {
            "$user$receiverID"
        } else {
            "$receiverID$user"
        }

        val database = FirebaseDatabase.getInstance().getReference("chat").child(assignAnID)
        val messageId = database.push().key // Generate a unique key for the message

        if (messageId != null ) {

            if (msgid=="") {
                val chat = chatMessageModel(messageId,personID,user, time, "",msg.text.toString())
                database.child(messageId).setValue(chat)
                    .addOnSuccessListener {
                        //Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show()
                        msg.setText("")
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(
                            this,
                            "Failed to send message: ${e.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
            else
            {
                val chat = chatMessageModel(msgid,personID,user, time, "",msg.text.toString())
                database.child(msgid).setValue(chat)
                    .addOnSuccessListener {
                        //Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show()
                        msgid=""
                        msg.setText("")
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(
                            this,
                            "Failed to send message: ${e.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                msgid=""
            }
        }
        LoadMsg(assignAnID.toString())

    }

    private fun LoadMsg(userID: String){
        //--------------------------------- read chats

        Log.d("My tag", "Entered function")
        var chatlist=ArrayList<chatMessageModel>()
        //chatlist.clear()

        val dbMentor = FirebaseDatabase.getInstance().getReference("chat")

        dbMentor.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                for (userSnapshot in snapshot.children) {
                    val userID2 = userSnapshot.key
                    Log.d("My tag", userID2.toString())

                    // Iterate through each booking for this user
                    if(userID==userID2) {
                        for (chatSnapshot in userSnapshot.children) {
                            Log.d("My tag", chatSnapshot.toString())
                            val mentor = chatSnapshot.child("id").getValue(String::class.java)

                            val time = chatSnapshot.child("time").getValue(String::class.java)
                            val tmsg = chatSnapshot.child("message").getValue(String::class.java)
                            val messgid=chatSnapshot.child("msgID").getValue(String::class.java)
                            val namebase = FirebaseDatabase.getInstance().getReference("user")
                            var uri:String=""

                            namebase.child(mentor.toString()).get().addOnSuccessListener { dataSnapshot ->

                                 uri=dataSnapshot.child("imguri").value.toString()

                            }

                            // Create a Bookdetail object and add it to the list
                            val booking = chatMessageModel(messgid!!,"",mentor!!, time!!, uri ,tmsg)
                            chatlist.add(booking)
                            //Toast.makeText(this@page12_Activity_chat2,tmsg, Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
                Log.e("FirebaseError", "Error reading chats: ${error.message}")
            }
        })

        val handler = Handler(Looper.getMainLooper())
        super.onResume()
        handler.postDelayed({
            chatMentor(chatlist,userID)
        }, 1500)


        Log.d("My tag", "out of function")

    }

    @SuppressLint("MissingInflatedId")
    private fun chatMentor(chatlist: MutableList<chatMessageModel>, chatid:String){


        val container = findViewById<LinearLayout>(R.id.thechats)
        Log.d("MyTag:", "entered function")
        var r=""
        container.removeAllViews()
        for (chat in chatlist) {
            if (chat.ID==user) {        //for sent messages
                //Toast.makeText(this@page4_Activity_home,mntrlist[0].mail, Toast.LENGTH_SHORT).show()
                val itemView = layoutInflater.inflate(R.layout.chat_send_recyler, null)
                Log.d("MyTag:", chat.ID.toString())

                // Retrieve img2 within the itemView

                val msg = itemView.findViewById<TextView>(R.id.msg)
                val time =itemView.findViewById<TextView>(R.id.time)
                val edit=itemView.findViewById<TextView>(R.id.edit)
                val del=itemView.findViewById<TextView>(R.id.del)
                val enter=findViewById<EditText>(R.id.message)
                msg.text=chat.message
                time.text=chat.time.toString()

                Log.d("MyTag:", chat.message.toString())
                msg.setOnLongClickListener(object : View.OnLongClickListener {
                    override fun onLongClick(v: View?): Boolean {
                        edit.text="Edit"
                        del.text="Delete"
                        return true // Return true to consume the long-click event
                    }
                })
                edit.setOnClickListener {
                    edit.text=""
                    del.text=""
                    enter.setText(msg.text)
                    msgid=chat.msgID
                    enter.requestFocus()


                }
                msg.setOnClickListener {
                    edit.text=""
                    del.text=""

                }
                del.setOnClickListener {
                    edit.text=""
                    del.text=""
                    msg.text=""
                    val database = FirebaseDatabase.getInstance().getReference("chat")
                    val childRef = database.child(chatid).child(chat.msgID)
                    childRef.removeValue()
                        .addOnSuccessListener {
                            // Child node deleted successfully
                        }
                        .addOnFailureListener { error ->
                            // Handle any errors that occurred
                            Log.e("Firebase", "Error deleting child node: ${error.message}")
                        }
                }


                // Add the inflated item view to the container

                container.addView(itemView)

            }
            else {
                //Toast.makeText(this@page4_Activity_home,mntrlist[0].mail, Toast.LENGTH_SHORT).show()
                val itemView = layoutInflater.inflate(R.layout.chat_recieve_recyler, null)

                // Retrieve img2 within the itemView
                val msg = itemView.findViewById<TextView>(R.id.msg)
                val time =itemView.findViewById<TextView>(R.id.time)
                val pic= itemView.findViewById<com.google.android.material.imageview.ShapeableImageView>(R.id.profilepic)
                Log.d("MyTag:", chat.message.toString())

                msg.text=chat.message
                time.text=chat.time.toString()
                if(chat.uri.length>10)
                    Glide.with(this)
                        .load(chat.uri)
                        .into(pic)
                else
                    Glide.with(this)
                        .load(defulturi)
                        .into(pic)



                // Add the inflated item view to the container

                container.addView(itemView)

            }
        }

        val scrollView = findViewById<ScrollView>(R.id.scroll) // Replace with your ScrollView ID

        scrollView.post {
            scrollView.fullScroll(ScrollView.FOCUS_DOWN)
        }
        Log.d("MyTag:", "out function")

    }

    fun getCurrentTime(): String {
        val currentTime = Date()
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return dateFormat.format(currentTime)
    }
    private fun fetchUser(userID: String) {
        val namebase = FirebaseDatabase.getInstance().getReference("user")
        val nameTextView = findViewById<TextView>(R.id.name)
        namebase.child(userID.toString()).get().addOnSuccessListener { dataSnapshot ->
            nameTextView.text = dataSnapshot.child("namee").value.toString()
            if (nameTextView.text == "null")
                nameTextView.text = userID.toString()
            var uri = dataSnapshot.child("imguri").value.toString()
            if (uri.length > 10)
                defulturi=uri

        }
    }
}
