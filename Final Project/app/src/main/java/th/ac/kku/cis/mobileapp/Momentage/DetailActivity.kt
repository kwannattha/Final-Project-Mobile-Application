package th.ac.kku.cis.mobileapp.Momentage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import android.widget.TextView as TextView

class DetailActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var titleitem: TextView
    lateinit var spoiler: TextView
    lateinit var category: TextView

    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var titleitem: TextView = findViewById(R.id.titleitem)
        var imageView: ImageView = findViewById(R.id.imageView)
        var spoiler: TextView = findViewById(R.id.spoiler)
        var category: TextView = findViewById(R.id.category)

        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()

        val intent = intent
        var getKey = intent.getStringExtra("key")

        var databaseReference = firebaseDatabase.getReference("Review/$getKey")
        databaseReference.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot){
                titleitem.text = snapshot.child("titleitem").value.toString()
                spoiler.text = snapshot.child("spoiler").value.toString()
                category.text = snapshot.child("ategory").value.toString()
                Picasso.get().load(snapshot.child("image").value.toString())
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(imageView)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


    }
}