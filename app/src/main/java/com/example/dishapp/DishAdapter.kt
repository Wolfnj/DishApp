package com.example.dishapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DishAdapter(private val dishesList: List<Dish>,
                  private val listener: OnDishClickListner

                    ) : RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.dish_item,parent,
            false)
        return DishViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val currentDish =dishesList[position]

        holder.titleTextView.text = currentDish.title


    }

    override fun getItemCount(): Int {
        return dishesList.size
    }


    inner class DishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener{
        val titleTextView: TextView = itemView.findViewById(R.id.title_text_view)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position:Int = adapterPosition
            if(position!=RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }
    }

    interface OnDishClickListner{
        fun onItemClick(position: Int)
    }

}