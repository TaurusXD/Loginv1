package com.example.loginv1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.HashMap

class PrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        val txv_User = findViewById<TextView>(R.id.txv_User)
        val auth = Firebase.auth

        val username = auth.currentUser!!.displayName
        txv_User.text = "Olá,$username"
        val recycleview_Despesas = findViewById<RecyclerView>(R.id.recyclerView_Despesas)
        recycleview_Despesas.layoutManager = LinearLayoutManager(this)
        recycleview_Despesas.setHasFixedSize(true)


        val btn_AddItem = findViewById<FloatingActionButton>(R.id.btn_AddItem)

        btn_AddItem.setOnClickListener(){
            val intent = Intent(this,CadastroItemActivity::class.java)
            startActivity(intent)
        }
    }

}