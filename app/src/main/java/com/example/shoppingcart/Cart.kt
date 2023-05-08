package com.example.shoppingcart

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.adapters.CartAdapter
import com.example.shoppingcart.adapters.CategoryTypeAdapter
import com.example.shoppingcart.model.CategoryType
import com.example.shoppingcart.model.Item
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Cart : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var arrayList: ArrayList<Item>
    lateinit var gridLayoutManager: GridLayoutManager
    lateinit var cartAdapter: CartAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_cart, container, false)

        var placeOrderBtn=view.findViewById<Button>(R.id.placeOrder)

        placeOrderBtn.setOnClickListener {
            onPlaceOrderClick()
        }


        recyclerView=view.findViewById(R.id.cartRecyclerView)
        gridLayoutManager= GridLayoutManager(requireContext(),1, LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager=gridLayoutManager
        // recyclerView.setHasFixedSize(true)
        arrayList=getItemsData()
        cartAdapter= CartAdapter(requireContext(),arrayList)
        recyclerView.adapter=cartAdapter

        cartAdapter.onItemClickListener(object : CartAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {

                Toast.makeText(context,arrayList[position].name,Toast.LENGTH_SHORT).show()



            }
        })

        return view
    }
    private fun getItemsData():ArrayList<Item>{
        var cartItems:ArrayList<Item> = ArrayList()

        val sharedPrefs = requireContext().getSharedPreferences("cartData", Context.MODE_PRIVATE)

        sharedPrefs.all.forEach {
            val jsonString = sharedPrefs.getString(it.key, null) // Retrieve the JSON string from SharedPreferences

            if (jsonString != null) {
                val gson = Gson() // Create a Gson object to convert JSON to objects
                var item:Item = gson.fromJson(jsonString, object : TypeToken<Item>() {}.type)
                cartItems.add(item)
                // Convert the JSON string to ArrayList of objects using Gson
            }
        }
        return  cartItems
    }

    private fun onPlaceOrderClick() {

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Confirm order")
        builder.setMessage("Do you want to place your order?")
        //builder.setIcon(R.drawable.ic_info)

        builder.setPositiveButton("Yes") { dialogInterface, which ->
            Toast.makeText(context, "clicked yes", Toast.LENGTH_LONG).show()

            // Show notification
            context?.let {
                val notificationManager = it.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                val channelId = "my_channel_id"
                val notificationBuilder = NotificationCompat.Builder(it, channelId)
                    .setSmallIcon(R.drawable.ic_back)
                    .setContentTitle("Order Placed")
                    .setContentText("Your will receive your order within 5 days!")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val channel = NotificationChannel(
                        channelId,
                        "My Channel",
                        NotificationManager.IMPORTANCE_DEFAULT
                    )
                    notificationManager.createNotificationChannel(channel)
                }

                notificationManager.notify(1, notificationBuilder.build())
            }
        }

        builder.setNegativeButton("No") { dialogInterface, which ->
            Toast.makeText(context, "clicked No", Toast.LENGTH_LONG).show()
        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()

    }


}