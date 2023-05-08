package com.example.shoppingcart.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.R
import com.example.shoppingcart.model.Item

class ItemsAdapter(var context: Context, var arrayList: ArrayList<Item>): RecyclerView.Adapter<ItemsAdapter.ItemHolder>() {
    //private lateinit var p:ViewGroup
    private lateinit var listener:onItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsAdapter.ItemHolder {
        //p=parent
        var itemHolder=
            LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return  ItemHolder(itemHolder,listener)
    }

    override fun getItemCount(): Int {
        return  arrayList.size
    }

    override fun onBindViewHolder(holder: ItemsAdapter.ItemHolder, position: Int) {

        var item= arrayList[position]
        holder.image.setImageResource(item.image)
        holder.name.text=item.name
        holder.price.text=item.price.toString()
    }

    class ItemHolder(itemView: View, listener:onItemClickListener): RecyclerView.ViewHolder(itemView){

        var image=itemView.findViewById<ImageView>(R.id.itemImage)
        var name=itemView.findViewById<TextView>(R.id.itemName)
        var price=itemView.findViewById<TextView>(R.id.itemPrice)

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