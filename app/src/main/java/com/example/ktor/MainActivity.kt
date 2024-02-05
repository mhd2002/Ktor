package com.example.ktor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.ktor.data.remote.dto.PostResponse
import com.example.ktor.data.remote.dto.PostsService
import com.example.ktor.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val client = PostsService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {

            try {

                val value:List<PostResponse> = client.getPosts()
                println("Received posts: $value")
                value.forEach {
                    binding.textView.append(it.body)
                }


            } catch (e: Exception) {

                runOnUiThread {
                    Toast.makeText(
                        this@MainActivity,
                        e.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }

        }


    }
}