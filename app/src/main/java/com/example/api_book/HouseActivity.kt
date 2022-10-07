package com.example.api_book

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.api_book.databinding.ActivityHouseBinding
import com.google.gson.Gson
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class HouseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHouseBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHouseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val executorService: ExecutorService = Executors.newSingleThreadExecutor()
        binding.btn.setOnClickListener {
            val id = findByName(binding.etName.text.toString())
            Log.d("QWE", id.toString())
            if (id != -1) {
                val temp = executorService.submit(Callable {
                    httpRequest("https://anapioficeandfire.com/api/houses/$id")
                }).get()
                var titles = ""
                temp.titles.forEach {
                    titles += "\n${it}"
                }
                var seats = ""
                temp.seats.forEach {
                    seats += "\n${it}"
                }
                binding.tvHouseInfo.text = "Name: ${temp.name}\n" +
                        "Region: ${temp.region}\n" +
                        "Coat of arms: ${temp.coatOfArms}\n" +
                        "Words: ${temp.words}\n" +
                        "Titles: ${titles}\n" +
                        "Seats: ${seats}"
            } else {
                binding.tvHouseInfo.text = "Not found"
            }
        }
    }

    fun findByName(name: String): Int {
        var i = 1
        val executorService: ExecutorService = Executors.newSingleThreadExecutor()
        while (i<=444)
        {
            val temp = executorService.submit(Callable {
                httpRequest("https://anapioficeandfire.com/api/houses/$i")
            }).get().name
            if (temp == name)
            {
                return i
            }
            i++
        }
        return -1
    }

    @Throws(IOException::class)
    fun httpRequest(urlString: String): House {
        /*Созддание экземпляра URL*/
        val url = URL(urlString)
        /*Создание экзепляра класса для соедниения по протоколу HTTP*/
        val connection = url.openConnection() as HttpURLConnection
        /*Установка метода запроса*/
        connection.requestMethod = "GET"
        /*Отправка запроса и чтение полученых данных*/
        var data: Int = connection.inputStream.read()
        var str = ""
        while (data != -1){
            str += data.toChar()
            data = connection.inputStream.read()
        }
        val house = Gson().fromJson(str, House::class.java)
        //Log.d("API_QWE", "$character")
        return house
    }
}