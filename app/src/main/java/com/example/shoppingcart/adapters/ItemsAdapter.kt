package com.example.shoppingcart.adapters

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.R
import com.example.shoppingcart.model.Item
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

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
        holder.price.text="₹ ${item.price.toString()}"

        var addToCartBtn=holder.itemView.findViewById<Button>(R.id.addToCart)
        addToCartBtn.setOnClickListener {
            Toast.makeText(context,arrayList[position].name+ " added to cart",Toast.LENGTH_SHORT).show()
            addDataToCart(item)
            addToCartBtn.text="Added"
        }

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

    fun addDataToCart(item:Item){

        val gson = Gson() // First, create a Gson object to convert your objects to JSON
        val jsonString = gson.toJson(item) // Convert the ArrayList to JSON string using Gson

        val sharedPrefs = context.getSharedPreferences("cartData", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putString(item.id.toString(), jsonString)
        editor.apply()
    }
    fun getCartData():ArrayList<Item>{

        var cartData=ArrayList<Item>()

        val sharedPrefs = context.getSharedPreferences("cart", Context.MODE_PRIVATE)
        val jsonString = sharedPrefs.getString("cartData", null) // Retrieve the JSON string from SharedPreferences

        if (jsonString != null) {
            val gson = Gson() // Create a Gson object to convert JSON to objects
            cartData = gson.fromJson(jsonString, object : TypeToken<ArrayList<Item>>() {}.type)
            // Convert the JSON string to ArrayList of objects using Gson

        }
        return cartData
    }
}