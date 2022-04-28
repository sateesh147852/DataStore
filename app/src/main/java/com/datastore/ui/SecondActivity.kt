package com.datastore.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.datastore.R
import com.datastore.data.ProtoRepository
import com.datastore.databinding.ActivitySecondBinding
import kotlinx.coroutines.launch

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var protoRepository: ProtoRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
        initializeClickListener()
    }

    private fun initializeClickListener() {
        binding.tvUpdateData.setOnClickListener {
            lifecycleScope.launch {
                protoRepository.updateValue("Sateesh Chikkalagi")
            }
        }
    }

    private fun initialize() {
        protoRepository = ProtoRepository(this)

        val firstName = protoRepository.readProto.asLiveData()

        firstName.observe(this) {
            Toast.makeText(this,it.firstName,Toast.LENGTH_SHORT).show()
        }

        lifecycleScope.launch {
            val person = protoRepository.getPerson()
            Log.i("TAG", "initialize: ${person.firstName}")
        }


    }
}