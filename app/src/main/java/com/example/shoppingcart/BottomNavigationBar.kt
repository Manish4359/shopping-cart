package com.example.shoppingcart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.shoppingcart.databinding.ActivityBottomNavigationBarBinding

class BottomNavigationBar : AppCompatActivity() {

    lateinit var binding:ActivityBottomNavigationBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBottomNavigationBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(Home())
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home->replaceFragment(Home())
                R.id.cart->replaceFragment(Cart())
                R.id.profile->replaceFragment(Profile())
                else ->{}
            }
            true
        }
    }

    private fun replaceFragment(fragment:Fragment){

        var fragmentManager=supportFragmentManager
        var fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}