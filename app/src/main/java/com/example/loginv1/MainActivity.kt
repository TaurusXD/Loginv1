package com.example.loginv1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val auth = Firebase.auth
        val edt_Email = findViewById<TextView>(R.id.edt_Email)
        val edt_Senha = findViewById<TextView>(R.id.edt_Senha)
        val btn_Entrar = findViewById<Button>(R.id.btn_Entrar)
        val txv_Cadastrar = findViewById<TextView>(R.id.txv_Cadastrar)

        btn_Entrar.setOnClickListener{
            val email = edt_Email.text.toString()
            val senha = edt_Senha.text.toString()
            if(email.toString() == "" || senha.toString() == ""){
                Toast.makeText(this,"Campos Requiridos",Toast.LENGTH_SHORT).show()
            }else{
                auth.signInWithEmailAndPassword(email,senha)
                    .addOnSuccessListener {
                        val intent = Intent(this,PrincipalActivity::class.java)
                        startActivity(intent)
                    }
                    .addOnFailureListener{
                        Toast.makeText(this,"Falha na autencticação",Toast.LENGTH_SHORT).show()
                    }
            }

        }

        /*
        txv_Cadastrar.setOnClickListener{
            val intent = Intent(this,CadastroActivity::class.java)
            startActivity(intent)
        }
        */
    }
    fun Cadastrar(view: View?){
        val intent = Intent(this,CadastroActivity::class.java)
        startActivity(intent)
    }

}