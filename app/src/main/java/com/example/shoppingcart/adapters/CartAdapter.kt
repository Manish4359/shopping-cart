package com.example.shoppingcart.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.R
import com.example.shoppingcart.model.Item
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CartAdapter(var context: Context, var arrayList: ArrayList<Item>): RecyclerView.Adapter<CartAdapter.ItemHolder>() {
    private lateinit var listener:onItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ItemHolder {
        //p=parent
        var itemHolder=
            LayoutInflater.from(parent.context).inflate(R.layout.cart_item,parent,false)
        return  ItemHolder(itemHolder,listener)
    }

    override fun getItemCount(): Int {
        return  arrayList.size
    }

    override fun onBindViewHolder(holder: CartAdapter.ItemHolder, position: Int) {

        var item= arrayList[position]
        holder.image.setImageResource(item.image)
        holder.name.text=item.name
        holder.price.text="₹ ${item.price.toString()}"
        holder.quantity.text=item.quantity.toString()


        var cartItem=holder.itemView

        var incQuantityBtn=cartItem.findViewById<ImageButton>(R.id.incQuantity)
        var decQuantityBtn=cartItem.findViewById<ImageButton>(R.id.decQuantity)

        incQuantityBtn.setOnClickListener {

            increaseQuantity(item.id.toString(),position)
            notifyDataSetChanged()
        }
        decQuantityBtn.setOnClickListener {
            decreaseQuantity(item.id.toString(),position)
            notifyDataSetChanged()
        }

    }

    class ItemHolder(itemView: View, listener:onItemClickListener): RecyclerView.ViewHolder(itemView){

        var image=itemView.findViewById<ImageView>(R.id.itemImage)
        var name=itemView.findViewById<TextView>(R.id.itemName)
        var price=itemView.findViewById<TextView>(R.id.itemPrice)
        var quantity=itemView.findViewById<TextView>(R.id.quantity)

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

    fun increaseQuantity(itemId:String,position:Int) {
        val sharedPrefs = context.getSharedPreferences("cartData", Context.MODE_PRIVATE)
        val jsonString = sharedPrefs.getString(itemId, null) // Retrieve the JSON string from SharedPreferences

        val gson = Gson() // Create a Gson object to convert JSON to objects
        var item: Item = gson.fromJson(jsonString, object : TypeToken<Item>() {}.type)
        item.quantity++
        arrayList[position].quantity++
        val newJsonString = gson.toJson(item)
        val editor = sharedPrefs.edit()
        editor.putString(itemId, newJsonString)
        editor.apply()


    }
    fun decreaseQuantity(itemId:String,position:Int) {
        val sharedPrefs = context.getSharedPreferences("cartData", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()

        if(arrayList[position].quantity==1){
            arrayList.removeAt(position)
            editor.remove(itemId)
            editor.apply()
        }else {
            val jsonString = sharedPrefs.getString(itemId, null) // Retrieve the JSON string from SharedPreferences
            val gson = Gson() // Create a Gson object to convert JSON to objects
            var item: Item = gson.fromJson(jsonString, object : TypeToken<Item>() {}.type)
            item.quantity--;
            arrayList[position].quantity--
            val newJsonString = gson.toJson(item)
            editor.putString(itemId, newJsonString)
            editor.apply()
        }
    }

}