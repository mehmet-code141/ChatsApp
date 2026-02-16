package com.example.chatsapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chatsapp.databinding.ActivityMainBinding
import com.example.chatsapp.databinding.ActivityRegisterBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
private lateinit var binding: ActivityRegisterBinding
private lateinit var auth: FirebaseAuth
class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val kullanıcı= auth.currentUser
        if(kullanıcı!=null){
            var intenti= Intent(this, HomepageActivity::class.java)
            startActivity(intenti)
            finish()
        }
    }
    fun kaydol(View: View){
        var email= binding.email.text.toString()
        var sifre= binding.password.text.toString()
        if(email.equals("")||sifre.equals("")){
            Toast.makeText(this, "lütfen boş bırakılan yerlere email veya şifrenizi giriniz", Toast.LENGTH_SHORT).show()
        }
        else(
                auth.createUserWithEmailAndPassword(email,sifre).addOnSuccessListener {
                    Toast.makeText(this, "kayıt işlemi başarılı", Toast.LENGTH_SHORT).show()
                    var intent= Intent(this, LoginActivity::class.java)
                    startActivity(intent)

                }.addOnFailureListener {
                    Toast.makeText(this, "kayıt işlemi başarısız", Toast.LENGTH_SHORT).show()
                }
                )
    }
}