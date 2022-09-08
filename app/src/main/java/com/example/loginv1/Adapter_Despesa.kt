package com.example.loginv1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loginv1.model.Despesas

class Adapter_Despesa (
    private val myList: ArrayList<Despesas>
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
    }

    override fun getItemCount() = myList.size

    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val txv_TipoDespesa: TextView = itemView.findViewById(R.id.txv_TipoDespesa)
        val txv_ValorDespesa: TextView = itemView.findViewById(R.id.txv_ValorDespesa)
        val txv_StatusDespesa: TextView = itemView.findViewById(R.id.txv_StatusDespesa)
    }
}