package com.example.api_book

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.api_book.databinding.ActivityHelloBinding

class Hello_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityHelloBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelloBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.toChar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.toHouse.setOnClickListener {
            val intent = Intent(this, HouseActivity::class.java)
            startActivity(intent)
        }
        binding.toBook.setOnClickListener {
            val intent = Intent(this, BookActivity::class.java)
            startActivity(intent)
        }
    }
}