package com.example.api_book

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.api_book.databinding.ActivityMainBinding
import com.google.gson.Gson
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val executorService: ExecutorService = Executors.newSingleThreadExecutor()
        binding.btn.setOnClickListener {
            val id = findByName(binding.etName.text.toString())
            Log.d("QWE", id.toString())
            if (id != -1) {
                val temp = executorService.submit(Callable {
                    httpRequest("https://anapioficeandfire.com/api/characters/$id")
                }).get()
                var titles = ""
                temp.titles.forEach {
                    titles += "${it}\n"
                }
                var aliases = ""
                temp.aliases.forEach {
                    aliases += "${it}\n"
                }
                binding.tvCharInfo.text = "Name: ${temp.name}\n" +
                        "Gender: ${temp.gender}\n" +
                        "Culture: ${temp.culture}\n" +
                        "Born: ${temp.culture}\n" +
                        "Titles:\n${titles}\n" +
                        "Aliases:\n${aliases}\n" +
                        "Father: ${temp.father}\n" +
                        "Mother: ${temp.mother}"
            } else {
                binding.tvCharInfo.text = "Not found"
            }

        }
        /*val id = findByName("Aemon Blackfyre")
        binding.tvCharInfo.text = executorService.submit(Callable {
            httpRequest("https://anapioficeandfire.com/api/characters/$id")
        }).get().name*/
    }

    fun findByName(name: String): Int {
        var i = 1
        val executorService: ExecutorService = Executors.newSingleThreadExecutor()
        while (i<=2138)
        {
            val temp = executorService.submit(Callable {
                httpRequest("https://anapioficeandfire.com/api/characters/$i")
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
    fun httpRequest(urlString: String): Character {
        /*?????????????????? ???????????????????? URL*/
        val url = URL(urlString)
        /*???????????????? ?????????????????? ???????????? ?????? ???????????????????? ???? ?????????????????? HTTP*/
        val connection = url.openConnection() as HttpURLConnection
        /*?????????????????? ???????????? ??????????????*/
        connection.requestMethod = "GET"
        /*???????????????? ?????????????? ?? ???????????? ?????????????????? ????????????*/
        var data: Int = connection.inputStream.read()
        var str = ""
        while (data != -1){
            str += data.toChar()
            data = connection.inputStream.read()
        }
        val character = Gson().fromJson(str, Character::class.java)
        //Log.d("API_QWE", "$character")
        return character
    }
}