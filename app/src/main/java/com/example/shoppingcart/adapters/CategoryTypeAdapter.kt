package com.example.shoppingcart.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.R
import com.example.shoppingcart.model.CategoryType

class CategoryTypeAdapter(var context: Context, var arrayList: ArrayList<CategoryType>):RecyclerView.Adapter<CategoryTypeAdapter.ItemHolder>() {
    //private lateinit var p:ViewGroup
    private lateinit var listener:onItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryTypeAdapter.ItemHolder {
        //p=parent
        var itemHolder=LayoutInflater.from(parent.context).inflate(R.layout.catergory_item,parent,false)
        return  ItemHolder(itemHolder,listener)
    }

    override fun getItemCount(): Int {
        return  arrayList.size
    }

    override fun onBindViewHolder(holder: CategoryTypeAdapter.ItemHolder, position: Int) {

        var item= arrayList[position]
        holder.image.setImageResource(item.itemImage)
        holder.name.text=item.itemName
    }

    class ItemHolder(itemView: View,listener:onItemClickListener):RecyclerView.ViewHolder(itemView){

        var image=itemView.findViewById<ImageView>(R.id.categoryItemImage)
        var name=itemView.findViewById<TextView>(R.id.categoryItemName)

        init {
                itemView.setOnClickListener {
                    listener.onItemClick(adapterPosition)
                }
        }
    }

    interface onItemClickListener{
        fun onItemClick(position:Int)
    }

    fun onItemClickListener(l:onItemClickListener){
        listener=l
    }
}