package com.example.loginv1

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.loginv1.model.Despesas

class Adapter_Despesa (
    private val myList: ArrayList<Despesas>,
    val nomeSelected: (String) -> Unit
        ):RecyclerView.Adapter<Adapter_Despesa.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_despesa,parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val despesas:Despesas = myList[position]
        holder.txv_TipoDespesa.text = despesas.categoria
        holder.txv_ValorDespesa.text = despesas.valor.toString()
        holder.txv_StatusDespesa.text = despesas.status
        holder.txv_Vencimento.text = despesas.dataVencimento
        holder.txv_descricao.text = despesas.descricao
        holder.itemView.setOnClickListener{
            nomeSelected(despesas.descricao.toString())
        }
    }

    override fun getItemCount() = myList.size

    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val txv_TipoDespesa: TextView = itemView.findViewById(R.id.txv_TipoDespesa)
        val txv_ValorDespesa: TextView = itemView.findViewById(R.id.txv_ValorDespesa)
        val txv_StatusDespesa: TextView = itemView.findViewById(R.id.txv_StatusDespesa)
        val txv_Vencimento: TextView = itemView.findViewById(R.id.txv_Vencimento)
        val txv_descricao: TextView = itemView.findViewById(R.id.txv_descricao)
    }
}