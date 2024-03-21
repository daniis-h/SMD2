package i212474.a1.i212474

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class page6_Activity_search2 : AppCompatActivity() {
    private lateinit var img2: ImageView
    private lateinit var open:LinearLayout
    private var bool1 = false // Initial state of the image
    private lateinit var database: DatabaseReference
    private lateinit var dbMentor: DatabaseReference
    private lateinit var user: String
    private var defulturi:String="https://firebasestorage.googleapis.com/v0/b/mentor-me-6558f.appspot.com/o/Pictures%2Fextra?alt=media&token=bf8bc9bc-f2c8-4faf-bd35-46b5054fd72e"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page6_search2) // Set your main activity layout here

        // Get the LinearLayout container where you want to add the items
        database= FirebaseDatabase.getInstance().getReference("user")
        val userID = intent.getStringExtra("USER_ID")
        user =userID.toString()

        var mntrlist= mutableListOf<Mentors>()
        val container = findViewById<LinearLayout>(R.id.insertRecent2)

 //---------------------------------Get Mentor data from firebase

        dbMentor = FirebaseDatabase.getInstance().getReference("mentor")

        dbMentor.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                for (mntosnap in snapshot.children) {


                    // Retrieve data from the database
                    val name = mntosnap.child("mail").getValue(String::class.java)
                    val price = mntosnap.child("price").getValue(String::class.java)
                    val disc = mntosnap.child("disc").getValue(String::class.java)
                    val status = mntosnap.child("status").getValue(String::class.java)

                    val mdata = Mentors(name, status, price, disc)
                    mntrlist.add(mdata)
                    //Toast.makeText(this@page4_Activity_home,mntrlist.size.toString(), Toast.LENGTH_SHORT).show()

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@page6_Activity_search2,"error", Toast.LENGTH_SHORT).show()
                // Handle onCancelled event (optional)
            }
        })


//--------------------------all mentor
        val handler = Handler(Looper.getMainLooper())
        super.onResume()
        handler.postDelayed({
            topMentor(mntrlist)
        }, 3000)
        //Toast.makeText(this@page6_Activity_search2,r, Toast.LENGTH_SHORT).show()

        val home = findViewById<LinearLayout>(R.id.home)

        home.setOnClickListener {
            val intent = Intent(this, page5_Activity_search::class.java)
            intent.putExtra("USER_ID", userID)
            startActivity(intent)
        }
        val add = findViewById<LinearLayout>(R.id.plus)

        add.setOnClickListener {
            val intent = Intent(this, page9_Activity_add::class.java)
            intent.putExtra("USER_ID", userID)
            startActivity(intent)
        }

        val back1 = findViewById<ImageView>(R.id.back)
        back1.setOnClickListener {
            finish() // This will close the current activity and go back to the previous one
        }

        val profile=findViewById<LinearLayout>(R.id.profile)
        profile.setOnClickListener {
            val intent=Intent(this, page17_Activity_profileView::class.java)
            intent.putExtra("USER_ID", userID)
            startActivity(intent)
        }

        val chat = findViewById<LinearLayout>(R.id.chat)

        chat.setOnClickListener {

            val intent = Intent(this, page11_Activity2_chats::class.java)
            intent.putExtra("USER_ID", userID)
            startActivity(intent)
        }

        val filter=findViewById<AutoCompleteTextView>(R.id.filter)
        filter.setOnClickListener{
            topMentor(mntrlist)
        }
    }

    private fun topMentor(mntrlist: MutableList<Mentors>){

        val container = findViewById<LinearLayout>(R.id.insertRecent2)
        Log.d("MyTag", "entered function")
        var r=""
        container.removeAllViews()
        for (mentor in mntrlist) {
            r="ok"

            if (mentor.mail.toString()!=user.toString()) {
                bool1=true
                //Toast.makeText(this@page4_Activity_home,mntrlist[0].mail, Toast.LENGTH_SHORT).show()
                val itemView = layoutInflater.inflate(R.layout.profile_view_in_search, null)

                // Retrieve img2 within the itemView
                val img2 = itemView.findViewById<ImageView>(R.id.like)
                val goto =itemView.findViewById<LinearLayout>(R.id.profileforsearch)

                // Populate the item view with mentor data

                //********** finding name *******
                val namebase = FirebaseDatabase.getInstance().getReference("user")
                val nameTextView = itemView.findViewById<TextView>(R.id.name)
                namebase.child(mentor.mail.toString()).get().addOnSuccessListener { dataSnapshot ->
                    nameTextView.text = dataSnapshot.child("namee").value.toString()
                    if(nameTextView.text=="null")
                        nameTextView.text=mentor.mail.toString()

                    val pic=itemView.findViewById<ImageView>(R.id.profilepic)
                    var uri=dataSnapshot.child("imguri").value.toString()
                    if(uri.length>10)
                        Glide.with(this)
                            .load(uri)
                            .into(pic)
                    else
                        Glide.with(this)
                            .load(defulturi)
                            .into(pic)
                    Log.d("My tag", nameTextView.text.toString()+uri.toString())
                }.addOnFailureListener { exception ->
                    // Handle failure if needed
                }
                //****************************

                val postTextView = itemView.findViewById<TextView>(R.id.post)
                postTextView.text = mentor.disc

                val availTextView = itemView.findViewById<TextView>(R.id.avail)
                availTextView.text = mentor.status

                val priceTextView = itemView.findViewById<TextView>(R.id.price)
                priceTextView.text = mentor.price

                //****** Finding favourites *********
                var bool=false
                val likebase = FirebaseDatabase.getInstance().getReference("favourite")
                likebase.child(user).get().addOnSuccessListener { dataSnapshot ->
                    val namee = dataSnapshot.child("fmail").value.toString().replace("[", "")
                        .replace("]", "")
                    val fmail = namee.split(",") // Assuming your string is comma-separated

                    val updatedList = fmail.toMutableList()
                    val modifiedList = mutableListOf<String>()
                    for(aa in updatedList)      //remove space from start of the word
                    {
                        if (aa[0]==' ') {
                            modifiedList.add(aa.substring(1))
                        }
                        else
                            modifiedList.add(aa)
                    }
                    var nam=true

//                        val f = Favoutites(user, modifiedList)
//                        likebase.child(user).setValue(f)
                    nam=modifiedList.any{it==mentor.mail.toString()}

                    if (nam == true) {
                        img2.setImageResource(R.drawable.heart)

                        nam = false
                    } else {
                        img2.setImageResource(R.drawable.heart_e)
                        nam = true
                    }
                    bool=nam

                }
                img2.setOnClickListener {
                    // Handle click for img2 within this item view
                    if (bool == true) {
                        favour(user.toString(),mentor.mail.toString())
                        img2.setImageResource(R.drawable.heart)
                        bool = false
                    } else {
                        rmfavour(user.toString(),mentor.mail.toString())
                        img2.setImageResource(R.drawable.heart_e)
                        bool = true
                    }
                    //eduMentor(mntrlist)
                }

                goto.setOnClickListener {
                    val intent = Intent(this, page7_Activity_profile::class.java)
                    intent.putExtra("USER_ID", user)
                    intent.putExtra("PROFILE", mentor.mail.toString())
                    startActivity(intent)
                }
                //**********************************




                // Add the inflated item view to the container

                container.addView(itemView)

            }
        }


    }

    private fun favour(userID: String, like: String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("favourite")
        val database = FirebaseDatabase.getInstance().getReference("favourite")

        database.child(userID).get().addOnSuccessListener { dataSnapshot ->
            val namee = dataSnapshot.child("fmail").value.toString().replace("[", "")
                .replace("]", "")

            val fmail = namee.split(",") // Assuming your string is comma-separated

            val likeString = like.toString()
            val updatedList = fmail.toMutableList()
            val modifiedList = mutableListOf<String>()
            for(aa in updatedList)      //remove space from start of the word
            {
                if (aa[0]==' ') {
                    modifiedList.add(aa.substring(1))
                }
                else
                    modifiedList.add(aa)
            }
            val present=modifiedList.any{it==like}
            modifiedList.add(likeString)


            // Create the Favoutites object and set its value in the database
            if (present==false){
                val f = Favoutites(userID, modifiedList)
                dbRef.child(userID).setValue(f)
            }
        }.addOnFailureListener { exception ->
            // Handle failure if needed
        }
    }

    private fun rmfavour(userID :String, like:String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("favourite")
        val database = FirebaseDatabase.getInstance().getReference("favourite")

        database.child(userID).get().addOnSuccessListener { dataSnapshot ->
            val namee = dataSnapshot.child("fmail").value.toString().replace("[", "")
                .replace("]", "")
            val fmail = namee.split(",") // Assuming your string is comma-separated
            val likeString = like.toString()
            val updatedList = fmail.toMutableList()
            val modifiedList = mutableListOf<String>()
            for(aa in updatedList)      //remove space from start of the word
            {
                if (aa[0]==' ') {
                    modifiedList.add(aa.substring(1))
                }
                else
                    modifiedList.add(aa)
            }
//            for (aa in updatedList)
//            {
//                Toast.makeText(this@page4_Activity_home,aa.toString(), Toast.LENGTH_LONG).show()
//            }

            modifiedList.remove(like)
            var like2=" "+like

            updatedList.remove(like2)
            // Create the Favoutites object and set its value in the database
            val f = Favoutites(userID, modifiedList)
            dbRef.child(userID).setValue(f)
        }.addOnFailureListener { exception ->
            // Handle failure if needed
        }
    }
}

