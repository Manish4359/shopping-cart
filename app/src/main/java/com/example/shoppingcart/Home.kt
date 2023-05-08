package com.example.shoppingcart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.adapters.CategoryTypeAdapter
import com.example.shoppingcart.model.CategoryType


class Home : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var arrayList: ArrayList<CategoryType>
    lateinit var gridLayoutManager: GridLayoutManager
    lateinit var categoryTypeAdapter: CategoryTypeAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view= inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView=view.findViewById(R.id.categoryTypeRecyclerView)
        gridLayoutManager= GridLayoutManager(requireContext(),2,LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager=gridLayoutManager
       // recyclerView.setHasFixedSize(true)
        arrayList=getItemsData()
        categoryTypeAdapter=CategoryTypeAdapter(requireContext(),arrayList)
        recyclerView.adapter=categoryTypeAdapter

        categoryTypeAdapter.onItemClickListener(object :CategoryTypeAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {

                val bundle = Bundle()
                bundle.putString("category",arrayList[position].itemName)
                val itemsFragment = Items()
                itemsFragment.arguments = bundle

                //Toast.makeText(context,arrayList[position].itemName,Toast.LENGTH_SHORT).show()
                val transaction=requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frame_layout,itemsFragment)
                transaction.commit()

            }
        })

        return view
    }
    private fun getItemsData():ArrayList<CategoryType>{
        var items:ArrayList<CategoryType> = ArrayList()

        items.add(CategoryType(R.drawable.fruits,"food"))
        items.add(CategoryType(R.drawable.shoes,"shoes"))
        items.add(CategoryType(R.drawable.electronics,"laptops"))
        items.add(CategoryType(R.drawable.mobiles,"mobiles"))

        return  items
    }

}