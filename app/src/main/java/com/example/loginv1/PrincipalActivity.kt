package com.example.loginv1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class PrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        val txv_User = findViewById<TextView>(R.id.txv_User)
        val auth = Firebase.auth

        val username = auth.currentUser!!.displayName
        txv_User.text = "Olá,$username"

        val btn_AddItem = findViewById<FloatingActionButton>(R.id.btn_AddItem)


        btn_AddItem.setOnClickListener(){
            val intent = Intent(this,CadastroItemActivity::class.java)
            startActivity(intent)
        }
    }

}