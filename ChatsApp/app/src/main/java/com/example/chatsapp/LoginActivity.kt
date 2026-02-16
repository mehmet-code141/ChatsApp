package com.example.chatsapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chatsapp.databinding.ActivityLoginBinding
import com.example.chatsapp.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

private lateinit var binding: ActivityLoginBinding
private lateinit var auth: FirebaseAuth
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
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
    fun girisi(View: View) {
        val email = binding.email.text.toString()
        val sifre = binding.password.text.toString()

        if (email == "" || sifre == "") {
            Toast.makeText(this, "lütfen boş bırakılan yerlere email veya şifrenizi giriniz", Toast.LENGTH_SHORT).show()
        } else {
            auth.signInWithEmailAndPassword(email, sifre)
                .addOnSuccessListener {
                    Toast.makeText(this, "giriş işlemi başarılı", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomepageActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "giriş işlemi başarısız", Toast.LENGTH_SHORT).show()
                }
        }
    }

    fun kaydol(View: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}