package com.example.shoppingcart

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.adapters.CategoryTypeAdapter
import com.example.shoppingcart.adapters.ItemsAdapter
import com.example.shoppingcart.model.CategoryType
import com.example.shoppingcart.model.Item


class Items : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var arrayList: ArrayList<Item>
    lateinit var gridLayoutManager: GridLayoutManager
    lateinit var itemsAdapter: ItemsAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_items, container, false)


        recyclerView=view.findViewById(R.id.itemsRecyclerView)
        gridLayoutManager= GridLayoutManager(requireContext(),2, LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager=gridLayoutManager
        // recyclerView.setHasFixedSize(true)
        arrayList=getItemsData()
        itemsAdapter= ItemsAdapter(requireContext(),arrayList)
        recyclerView.adapter=itemsAdapter

        itemsAdapter.onItemClickListener(object :ItemsAdapter.onItemClickListener{


            override fun onItemClick(position: Int) {
                //Toast.makeText(context,arrayList[position].name,Toast.LENGTH_SHORT).show()
            }

        })



        var back=view.findViewById<ImageButton>(R.id.back)

        back.setOnClickListener{
            val transaction=requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout,Home())
            transaction.commit()
        }



        return view
    }

    private fun getItemsData():ArrayList<Item>{

        val value = arguments?.getString("category")
        Toast.makeText(context,value,Toast.LENGTH_SHORT).show()

        var items:ArrayList<Item> = ArrayList()

        if(value.equals("laptops")) {


            items.add(Item("Asus tuf", 94989, R.drawable.l1, "laptops"))
            items.add(Item("acer Aspire 7 Ryzen 5", 47999, R.drawable.l2, "laptops"))
            items.add(Item("HP Pavilion Ryzen 5", 54000, R.drawable.l3, "laptops"))
            items.add(Item("APPLE 2023 MacBook Pro", 323000, R.drawable.l4, "laptops"))
        }
        else if(value.equals("food")){
            items.add(Item("pomegranate 1kg", 224, R.drawable.f1, "food"))
            items.add(Item("banana 1kg", 36, R.drawable.f2, "food"))
            items.add(Item("watermelon 1pc", 111, R.drawable.f3, "food"))
            items.add(Item("mango 1kg", 150, R.drawable.f4, "food"))
        }
        else if(value.equals("mobiles")){
            items.add(Item("SAMSUNG Galaxy A54 5G", 40999, R.drawable.m2, "mobiles"))
            items.add(Item("OnePlus Nord CE 2 Lite 5G ", 18738, R.drawable.m4, "mobiles"))
            items.add(Item("realme 8 5G", 13999, R.drawable.m3, "mobiles"))
            items.add(Item("APPLE iPhone 14 Pro Max", 177999, R.drawable.m1, "mobiles"))
        }
        else if(value.equals("shoes")){
            items.add(Item("CAMPUS HURRICANE", 786, R.drawable.s1, "shoes"))
            items.add(Item("PUMA ", 1736, R.drawable.s2, "shoes"))
            items.add(Item("RED TAPE ", 1079, R.drawable.s3, "shoes"))
            items.add(Item("Sparx", 686, R.drawable.s4, "shoes"))
        }

        return  items
    }


}