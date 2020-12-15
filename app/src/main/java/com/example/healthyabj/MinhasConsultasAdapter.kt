package com.example.healthyabj
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.minhasconsultaslistview.view.*
import java.nio.file.Files.size

class MinhasConsultasAdapter (

    var lvbro : List<Consultas>


) : RecyclerView.Adapter <MinhasConsultasAdapter.MCLVHolder>()

{

    inner class MCLVHolder (itemView : View ) : RecyclerView.ViewHolder (itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MCLVHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.minhasconsultaslistview,parent, false )
        return MCLVHolder(view)
    }

    override fun getItemCount(): Int {
        return lvbro.size
    }

    override fun onBindViewHolder(holder: MCLVHolder, position: Int) {
        holder.itemView.apply {
            //tvtitle.text = todos[posotion}.title
            mconsultaslvData.text = lvbro[position].DiaConsulta.toString()
            mconsultaslvPaciente.text = lvbro[position].EmaiPaciente.toString()
            mconsultaslvMedico.text = lvbro[position].EmailMedico.toString()
            mconsultaslvHora.text = lvbro[position].HoraConsulta.toString()




        }
    }


}