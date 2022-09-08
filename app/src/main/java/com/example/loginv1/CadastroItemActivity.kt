package com.example.loginv1

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CadastroItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_item)
        //Spinner do campo Categoria
        val spin_Categoria: Spinner = findViewById(R.id.spin_Categoria)
        ArrayAdapter.createFromResource(
            this,
            R.array.spin_categorias,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spin_Categoria.adapter = adapter
        }
        //Spinner do campo Status
        val spin_Status: Spinner = findViewById(R.id.spin_Status)
        ArrayAdapter.createFromResource(
            this,
            R.array.spin_status,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spin_Status.adapter = adapter
        }
        val email_user = Firebase.auth.currentUser!!.email
        val txt_Categoria = spin_Categoria.selectedItem.toString()
        val txt_Status = spin_Status.selectedItem.toString()
        val edt_Valor = findViewById<EditText>(R.id.edt_Valor)
        val edt_DataVencimento = findViewById<EditText>(R.id.edt_DataVencimento)
        val edt_Descricao = findViewById<EditText>(R.id.edt_Descicao)
        val btn_Adicionar = findViewById<FloatingActionButton>(R.id.btn_Adicionar)
        val btn_CancelarCadastro = findViewById<FloatingActionButton>(R.id.btn_CancelarCadastro)

        btn_Adicionar.setOnClickListener{
            val db = Firebase.firestore
            val despesa = hashMapOf(
                "categoria" to txt_Categoria,
                "status" to txt_Status,
                "valor" to edt_Valor.text.toString(),
                "dataVencimento" to edt_DataVencimento.text.toString(),
                "descricao" to edt_Descricao.text.toString()
            )
            db.collection(email_user.toString())
                .add(despesa)
                .addOnSuccessListener {
                    Toast.makeText(this,"Armazenamento concluído", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,PrincipalActivity::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Toast.makeText(this,"Falha no armazenamento",Toast.LENGTH_SHORT).show()
                }
        }

        btn_CancelarCadastro.setOnClickListener{
            val intent = Intent(this,PrincipalActivity::class.java)
            startActivity(intent)
        }
    }
}