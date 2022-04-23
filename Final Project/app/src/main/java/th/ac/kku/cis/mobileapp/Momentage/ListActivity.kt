package th.ac.kku.cis.mobileapp.Momentage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ListActivity : AppCompatActivity() {

    lateinit var recyclerreview: RecyclerView
    lateinit var mAuth: FirebaseAuth
    lateinit var database: FirebaseDatabase
    lateinit var databaseReferencereview: DatabaseReference
    lateinit var databaseReferencemovie: DatabaseReference
    lateinit var responsereview: MutableList<Model>
    private  var MovieAdapter: MovieAdapter? = null
    private  var ReviewAdapter: ReviewAdapter? = null
    lateinit var recyclermovie: RecyclerView
    lateinit var responsemovie: MutableList<Model>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        recyclerreview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclermovie = findViewById<RecyclerView>(R.id.recyclermovie)

        //ส่วนของ Firebase
        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        //part review
        recyclerreview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        databaseReferencereview = database.getReference("Review/")
        responsereview = mutableListOf()
        ReviewAdapter = ReviewAdapter(responsereview as ArrayList<Model>)
        recyclerreview.adapter = ReviewAdapter

        //part movie
        recyclermovie.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        databaseReferencemovie = database.getReference("Movie/")
        responsemovie = mutableListOf()
        MovieAdapter = MovieAdapter(responsemovie as ArrayList<Model>)
        recyclermovie.adapter = MovieAdapter

        onBindingFirebase()
    }

    private fun onBindingFirebase() {
        databaseReferencereview.addChildEventListener(object: ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                responsereview.add(snapshot.getValue(Model::class.java)!!)
                ReviewAdapter!!.notifyDataSetChanged()
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
        databaseReferencemovie.addChildEventListener(object: ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                responsemovie.add(snapshot.getValue(Model::class.java)!!)
                MovieAdapter!!.notifyDataSetChanged()
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}