package com.example.loginv1

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        val auth = Firebase.auth

        val edt_NomeCad = findViewById<TextView>(R.id.edt_NomeCad)
        val edt_EmailCad = findViewById<TextView>(R.id.edt_EmailCad)
        val edt_SenhaCad = findViewById<TextView>(R.id.edt_SenhaCad)
        val btn_Cadastrar = findViewById<Button>(R.id.btn_Cadastrar)

        btn_Cadastrar.setOnClickListener{
            val cadEmail = edt_EmailCad.text.toString()
            val cadSenha = edt_SenhaCad.text.toString()
            val cadNome = edt_NomeCad.text.toString()
            auth.createUserWithEmailAndPassword(cadEmail, cadSenha)
                .addOnSuccessListener {
                    val currentUser = auth.currentUser
                    val userProfileChangeRequest = UserProfileChangeRequest.Builder().setDisplayName(cadNome).build()
                    Toast.makeText(this,"Usuário ${currentUser.toString()}", Toast.LENGTH_SHORT).show()
                    currentUser!!.updateProfile(userProfileChangeRequest)
                        .addOnCompleteListener{
                            startActivity(Intent(this,MainActivity::class.java))
                        }
                }
                .addOnFailureListener{
                    Toast.makeText(this,"${auth.currentUser}", Toast.LENGTH_SHORT).show()
                }

        }

    }
}