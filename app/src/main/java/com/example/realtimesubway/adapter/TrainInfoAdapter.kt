package com.example.realtimesubway.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.realtimesubway.R
import com.example.realtimesubway.list.TrainInfoList

class TrainInfoAdapter(val list: ArrayList<TrainInfoList>) :
    RecyclerView.Adapter<TrainInfoAdapter.TrainInfoViewHolder>() {
    inner class TrainInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_trainNo = itemView.findViewById<TextView>(R.id.tv_trainNo)
        val tv_statnNm = itemView.findViewById<TextView>(R.id.tv_statnNm)
        val tv_statnTnm = itemView.findViewById<TextView>(R.id.tv_statnTnm)
        val tv_trainSttus = itemView.findViewById<TextView>(R.id.tv_trainSttus)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_info, parent, false)
        return TrainInfoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: TrainInfoViewHolder, position: Int) {
        holder.tv_trainNo.text = list[position].trainNo
        holder.tv_statnNm.text = list[position].statnNm
        holder.tv_statnTnm.text = list[position].statnTnm
        holder.tv_trainSttus.text = list[position].trainSttus

        when (holder.tv_trainSttus.text.toString()) {
            "0" -> {
                holder.tv_trainSttus.setTextColor(ContextCompat.getColor(holder.tv_trainSttus.context,R.color.green))
                holder.tv_trainSttus.text = "진입"
            }
            "1" -> {
                holder.tv_trainSttus.setTextColor(ContextCompat.getColor(holder.tv_trainSttus.context,R.color.yellow))
                holder.tv_trainSttus.text = "도착"
            }
            "2" -> {
                holder.tv_trainSttus.setTextColor(ContextCompat.getColor(holder.tv_trainSttus.context,R.color.red))
                holder.tv_trainSttus.text = "출발"
            }
            "3" -> {
                holder.tv_trainSttus.setTextColor(ContextCompat.getColor(holder.tv_trainSttus.context,R.color.black))
                holder.tv_trainSttus.text = "전역출발"
            }
        }
    }

}