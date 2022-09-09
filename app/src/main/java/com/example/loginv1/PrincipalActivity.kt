package com.example.loginv1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginv1.databinding.ActivityPrincipalBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import com.example.loginv1.model.Despesas as Despesas1


class PrincipalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPrincipalBinding
    private lateinit var despesaArrayList: ArrayList<Despesas1>
    private lateinit var myAdapter_Despesa: Adapter_Despesa
    val db = Firebase.firestore
    val email_user = Firebase.auth.currentUser!!.email

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val txv_User = findViewById<TextView>(R.id.txv_User)
        val auth = Firebase.auth
        val username = auth.currentUser!!.displayName
        txv_User.text = "Olá, $username"

        val descPopup = LayoutInflater.from(this).inflate(R.layout.popup_informacoes, null)
        val informacoes = descPopup.findViewById<TextView>(R.id.txv_informacoes)
        val btn_Fechar = descPopup.findViewById<TextView>(R.id.btn_Fechar)
        val mBuilder = AlertDialog.Builder(this)
            .setView(descPopup)
        val dialog = mBuilder.create()

        val btn_AddItem = findViewById<FloatingActionButton>(R.id.btn_AddItem)
        val btn_Sair = findViewById<Button>(R.id.btn_Sair)
        val recycleview_Despesas = findViewById<RecyclerView>(R.id.recview_Despesas)
        recycleview_Despesas.layoutManager = LinearLayoutManager(this)
        recycleview_Despesas.setHasFixedSize(true)


        despesaArrayList = arrayListOf()
        myAdapter_Despesa = Adapter_Despesa(despesaArrayList){despesas ->
            /*
            informacoes.text = despesas

                dialog.show()
                dialog.window?.setBackgroundDrawableResource((android.R.color.transparent))
                dialog.setCancelable(true)
            */


        Toast.makeText(this, despesas.toString(), Toast.LENGTH_SHORT).show()
        }
        btn_Fechar.setOnClickListener{
            if (descPopup.parent != null) {
                (descPopup.parent as ViewGroup).removeView(descPopup) // <- fix
            }
        }
        recycleview_Despesas.adapter = myAdapter_Despesa
        EventChangeListener()


        btn_AddItem.setOnClickListener(){
            val intent = Intent(this,CadastroItemActivity::class.java)
            startActivity(intent)
        }
        btn_Sair.setOnClickListener{
            auth.signOut()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }

private fun EventChangeListener(){
    db.collection(email_user.toString())
        .addSnapshotListener(object : EventListener<QuerySnapshot>{
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if(error != null){
                    Log.e("Erro no Firebase",error.message.toString())
                    return
                }
                for(dc : DocumentChange in value?.documentChanges!!){
                    if (dc.type == DocumentChange.Type.ADDED){
                        despesaArrayList.add(dc.document.toObject(Despesas1::class.java))
                    }
                }
                myAdapter_Despesa.notifyDataSetChanged()
            }

        })
}
   /*fun getList(): List<String> {
        db.collection(email_user.toString())
            .whereEqualTo("categoria","Despesa Fixa")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    listaDespesas.add(document.data.toString())
                    Toast.makeText(this,"${listaDespesas}", Toast.LENGTH_SHORT).show()

                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this,"Erro no retorno de dados", Toast.LENGTH_SHORT).show()
            }
        val listaRetorno : List<String> = listaDespesas
        return listaRetorno
    }*/
    /*private fun getList() = listOf(
        "Guilherme",
        "Rafaela"
    )*/


}