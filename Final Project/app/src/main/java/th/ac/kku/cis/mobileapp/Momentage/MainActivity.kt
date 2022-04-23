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

class MainActivity : AppCompatActivity() {
    lateinit var txtemail:EditText
    lateinit var txtpassword:EditText
    lateinit var btn_login: Button
    lateinit var btn_registernew: Button

    lateinit var email:String
    lateinit var password:String

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtemail = findViewById<EditText>(R.id.txtemail)
        txtpassword = findViewById<EditText>(R.id.txtpassword)
        btn_login = findViewById<Button>(R.id.btn_login)
        btn_registernew = findViewById<Button>(R.id.btn_registernew)

        //เปลี่ยนหน้าไปสมัครสมาชิก
        btn_registernew.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        btn_login.setOnClickListener {
            login()
        }
    }

    private fun login() {
        email = txtemail.text.toString()
        password = txtpassword.text.toString()
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this) {
            task -> if(task.isSuccessful){
                Log.d("Application", "Log In Success")
                val user = mAuth.currentUser
                updateUI(user)
            } else{
                Log.w("Application", "Failure Process!", task.exception)
                Toast.makeText(this@MainActivity, "Authentication Failed", Toast.LENGTH_SHORT).show()
                updateUI(null)
            }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null){
            val uid = user.uid
            val email = user.email
            Toast.makeText(this@MainActivity, "Welcome $email", Toast.LENGTH_SHORT).show()
            val intentSession = Intent(this, ListActivity::class.java)
            startActivity(intentSession)
        }
    }
}