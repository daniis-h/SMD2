package i212474.a1.i212474

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class page17_Activity_profileView : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var storageReff: StorageReference
    private lateinit var imageuri: Uri
    private lateinit var user: String
    private var defulturi:String="https://firebasestorage.googleapis.com/v0/b/mentor-me-6558f.appspot.com/o/Pictures%2Fextra?alt=media&token=bf8bc9bc-f2c8-4faf-bd35-46b5054fd72e"

    private val selectimg = registerForActivityResult(ActivityResultContracts.GetContent()) {
        if (it != null) {
            imageuri = it
            val img =
                findViewById<com.google.android.material.imageview.ShapeableImageView>(R.id.profilepic)
            img.setImageURI(imageuri)
            saveimage()
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page17_profile_view)

        database = FirebaseDatabase.getInstance().getReference("user")
        val userID = intent.getStringExtra("USER_ID")
        user = userID.toString()

        if (userID != null) {
            fetchUser(userID)
        }

//------------------------review
        val reviewlist = mutableListOf<reviewdetail>()
        val dbMentor = FirebaseDatabase.getInstance().getReference("review")

        dbMentor.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                for (userSnapshot in snapshot.children) {
                    val userID2 = userSnapshot.key

                    // Iterate through each booking for this user
                    if(userID==userID2) {
                        for (bookingSnapshot in userSnapshot.children) {
                            val mentor = bookingSnapshot.key
                            val star = bookingSnapshot.child("star").getValue(String::class.java)
                            val text = bookingSnapshot.child("text").getValue(String::class.java)

                            // Create a Bookdetail object and add it to the list
                            val booking = reviewdetail(mentor!!, star!!, text)
                            reviewlist.add(booking)
//                            Toast.makeText(this@page17_Activity_profileView, mentor + star,
//                                Toast.LENGTH_SHORT
//                            ).show()
                        }
                    }
                }

                // Now you have a list of all bookings
                // You can use booklist for further processing or display in your UI
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
                Log.e("FirebaseError", "Error reading bookings: ${error.message}")
            }
        })
        val handler = Handler(Looper.getMainLooper())
        super.onResume()
        handler.postDelayed({
            if(reviewlist.isNotEmpty())
                topReview(reviewlist)
        }, 4000)


        val search = findViewById<LinearLayout>(R.id.search)

        search.setOnClickListener {
            val intent = Intent(this, page5_Activity_search::class.java)
            intent.putExtra("USER_ID", userID)
            startActivity(intent)
        }
        val home = findViewById<LinearLayout>(R.id.home)

        home.setOnClickListener {
            val intent = Intent(this, page4_Activity_home::class.java)
            intent.putExtra("USER_ID", userID)
            startActivity(intent)
        }
        val back1 = findViewById<ImageView>(R.id.back)
        back1.setOnClickListener {
            finish() // This will close the current activity and go back to the previous one
        }

        val chat = findViewById<LinearLayout>(R.id.chat)

        chat.setOnClickListener {
            val intent = Intent(this, page11_Activity2_chats::class.java)
            intent.putExtra("USER_ID", userID)
            startActivity(intent)
        }
        val add = findViewById<LinearLayout>(R.id.plus)

        add.setOnClickListener {
            val intent = Intent(this, page9_Activity_add::class.java)
            intent.putExtra("USER_ID", userID)
            startActivity(intent)
        }

        val edit1 = findViewById<LinearLayout>(R.id.edit1)

        edit1.setOnClickListener {
            val intent = Intent(this, page16_Activity2_profile::class.java)
            intent.putExtra("USER_ID", userID)
            startActivity(intent)
            finish()
        }
        val edit = findViewById<LinearLayout>(R.id.edit)

        edit.setOnClickListener {
            selectimg.launch("image/*")

        }

        val book = findViewById<Button>(R.id.book)

        book.setOnClickListener {
            val intent = Intent(this, page18_Activity_bookSession::class.java)
            intent.putExtra("USER_ID", userID)
            startActivity(intent)
        }
        val editp = findViewById<TextView>(R.id.notifica)

        editp.setOnClickListener {
            val intent = Intent(this, page16_Activity2_profile::class.java)
            intent.putExtra("USER_ID", userID)
            startActivity(intent)
//            val intent = Intent(this, page24_Activity_notificatons::class.java)
//            intent.putExtra("USER_ID", userID)
//            startActivity(intent)
        }
    }




    private fun fetchUser(userId: String) {
        Log.d("MyTag", "entered fetch user function")
        database = FirebaseDatabase.getInstance().getReference("user")
        database.child(userId).get().addOnSuccessListener {
            Log.d("MyTag", "Reading data")
            val nam = it.child("namee").value.toString()
            val cit = it.child("city").value.toString()
            val image = it.child("imguri").value.toString()


            val name = findViewById<TextView>(R.id.name17)
            val city = findViewById<TextView>(R.id.city17)
            Log.d("MyTag", "uploading picture")

            val img = findViewById<com.google.android.material.imageview.ShapeableImageView>(R.id.profilepic)
            if (image.length > 10)
                Glide.with(this)
                    .load(image)
                    .into(img)
            else
                Glide.with(this)
                    .load(defulturi)
                    .into(img)
            name.text = nam
            city.text = cit
            Log.d("MyTag", "out of fetctuser function")
        }
    }

    private fun saveimage() {
        val storeref = FirebaseStorage.getInstance().getReference("Pictures")
            .child(user)
        Log.d("MyTag", "entered save image function")

        storeref.putFile(imageuri!!)
            .addOnSuccessListener {
                storeref.downloadUrl.addOnSuccessListener {
                    //save Path
                    if (user != null) {
                        database.child(user).child("imguri").setValue(it.toString())
                            .addOnSuccessListener {
                                Toast.makeText(
                                    this,
                                    "Name updated successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(
                                    this,
                                    "Failed to update name: ${e.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                    }
                }
                    .addOnFailureListener {
                        Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show()
                    }
            }
        Log.d("MyTag", "out of function")
    }

    private fun topReview(reviewlist: MutableList<reviewdetail>) {

        val container = findViewById<LinearLayout>(R.id.reviewFill)
        Log.d("MyTag", "entered function")
        var r = ""
        container.removeAllViews()
        for (booked in reviewlist) {


                //Toast.makeText(this@page4_Activity_home,mntrlist[0].mail, Toast.LENGTH_SHORT).show()
                val itemView = layoutInflater.inflate(R.layout.review, null)


                // Populate the item view with mentor data

                //********** finding name *******
                val namebase = FirebaseDatabase.getInstance().getReference("user")
                val nameTextView = itemView.findViewById<TextView>(R.id.name)

                namebase.child(booked.mentor.toString()).get()
                    .addOnSuccessListener { dataSnapshot ->
                        nameTextView.text = dataSnapshot.child("namee").value.toString()
                        if (nameTextView.text == "null")
                            nameTextView.text = booked.mentor.toString()

                    }.addOnFailureListener { exception ->
                        // Handle failure if needed
                    }
                //****************************
                val postTextView = itemView.findViewById<TextView>(R.id.reviewGiven)
                val reviewbase = FirebaseDatabase.getInstance().getReference("review")
                reviewbase.child(user.toString()).get()
                    .addOnSuccessListener { dataSnapshot ->
                        postTextView.text = dataSnapshot.child(booked.mentor.toString()).child("text").value.toString()
                        val star = dataSnapshot.child(booked.mentor.toString()).child("star").value.toString()

                        val img1 = itemView.findViewById<ImageView>(R.id.s1)
                        val img2 = itemView.findViewById<ImageView>(R.id.s2)
                        val img3 = itemView.findViewById<ImageView>(R.id.s3)
                        val img4 = itemView.findViewById<ImageView>(R.id.s4)
                        val img5 = itemView.findViewById<ImageView>(R.id.s5)
                        if (star.toInt() >= 1)
                            img1.setImageResource(R.drawable.star)
                        else
                            img1.setImageResource(R.drawable.empty_star)
                        if (star.toInt() >= 2)
                            img2.setImageResource(R.drawable.star)
                        else
                            img2.setImageResource(R.drawable.empty_star)
                        if (star.toInt() >= 3)
                            img3.setImageResource(R.drawable.star)
                        else
                            img3.setImageResource(R.drawable.empty_star)
                        if (star.toInt() >= 4)
                            img4.setImageResource(R.drawable.star)
                        else
                            img4.setImageResource(R.drawable.empty_star)
                        if (star.toInt() == 5)
                            img5.setImageResource(R.drawable.star)
                        else
                            img5.setImageResource(R.drawable.empty_star)


                    }.addOnFailureListener { exception ->
                        // Handle failure if needed
                    }


                container.addView(itemView)



        }
        Log.d("MyTag", "out of review function")


    }
}

