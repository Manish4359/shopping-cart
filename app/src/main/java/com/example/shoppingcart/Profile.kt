package com.example.shoppingcart

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


class Profile : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_profile, container, false)

        var logoutBtn=view.findViewById<Button>(R.id.profile_logout)

        logoutBtn.setOnClickListener {

            var intent= Intent(context,Login::class.java)
            startActivity(intent)
        }


        return view
    }


}