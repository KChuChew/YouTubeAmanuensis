package com.kchew.youtubeamanuensis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private val YOUTUBE_API_KEY = "AIzaSyCWUTb9oy9nTdXG1Iz5Q-I-8Zv8p7jsU0c"
    private val CHANNEL_ID = "UCDKZ01hZBrN4KjCoWM1W5wA"
    private val CHANNEL_URL =
        "https://developers.google.com/apis-explorer/#p/youtube/v3/youtube.captions.list?" +
                "part=snippet" +
                "&videoId=liJVSwOiiwg" +
                "&maxResults=20" + "&key=" + YOUTUBE_API_KEY
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        run(CHANNEL_URL)
    }

    private fun run(url: String) {
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) = println(response.body()?.string())
        })
    }
}
