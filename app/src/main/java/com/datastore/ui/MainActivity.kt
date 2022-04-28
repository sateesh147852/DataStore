package com.datastore.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.datastore.data.UserPreferences
import com.datastore.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
        initializeClickListener()

    }

    private fun initializeClickListener() {
        binding.tvSavaData.setOnClickListener {
            var name : String = ""
            var password : String = ""
            var type : Int = 10
            lifecycleScope.launch {
                name = userPreferences.getName()!!
                password = userPreferences.getPassword()!!
                type = userPreferences.getType()!!
            }
            Toast.makeText(this, "$name $password $type",Toast.LENGTH_SHORT).show()
        }

        binding.tvProtoDataStore.setOnClickListener {
            Intent(this,SecondActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun initialize() {
        userPreferences = UserPreferences(this)
        lifecycleScope.launch {
            userPreferences.saveUserInformation("Sateesh" ,"satish1234",3)
        }
    }
}