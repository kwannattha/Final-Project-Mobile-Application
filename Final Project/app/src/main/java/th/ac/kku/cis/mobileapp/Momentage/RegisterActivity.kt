package th.ac.kku.cis.mobileapp.Momentage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    lateinit var txtnameregister: EditText
    lateinit var txtemailregister: EditText
    lateinit var txtpasswordregister: EditText
    lateinit var btn_createnew: Button

    lateinit var name: String
    lateinit var email: String
    lateinit var password: String

    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        txtnameregister = findViewById<EditText>(R.id.txtnameregister)
        txtemailregister = findViewById<EditText>(R.id.txtemailregister)
        txtpasswordregister = findViewById<EditText>(R.id.txtpasswordregister)
        btn_createnew = findViewById<Button>(R.id.btn_createnew)

        //ส่วนของ Firebase
        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        btn_createnew!!.setOnClickListener {
            createAccount()
        }
    }

    private fun createAccount() {
        name = txtnameregister!!.text.toString()
        email = txtemailregister!!.text.toString()
        password = txtpasswordregister!!.text.toString()
        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("Application", "Create New Account Success")
                    val user = mAuth!!.currentUser
                    val databaseReference = database.reference.child("User").push()
                    databaseReference.child("uid").setValue(user!!.uid)
                    databaseReference.child("email").setValue(user!!.email)
                    databaseReference.child("fullname").setValue(txtnameregister.text.toString())
                    updateUI(user)
                } else {
                    Log.w("Application", "Failure Process!", task.exception)
                    Toast.makeText(
                        this@RegisterActivity,
                        "Authentication Failed",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            Toast.makeText(this@RegisterActivity, "Welcome $name", Toast.LENGTH_SHORT).show()
            val intentSession = Intent(this, ListActivity::class.java)
            startActivity(intentSession)
        }
    }
}