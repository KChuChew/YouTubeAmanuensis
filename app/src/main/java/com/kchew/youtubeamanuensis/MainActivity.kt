package com.kchew.youtubeamanuensis

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private val YOUTUBE_API_KEY = "AIzaSyCp8VekQxGvqntgWqhLCzcVZbcK2mQps3w"
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

        //run(CHANNEL_URL)
        runVolleyRequest(this)
    }

    private fun runVolleyRequest(context: Context) {
        val q = Volley.newRequestQueue(context)
        val url =
            "https://www.googleapis.com/youtube/v3/captions?videoId=LXLDLzdj-gk&part=snippet&key=$YOUTUBE_API_KEY"
        val stringRequest = StringRequest(com.android.volley.Request.Method.GET, url,
            com.android.volley.Response.Listener<String> { response ->
                findViewById<TextView>(R.id.TextView1).text = "Response is: ${response.substring(0,500)}"
            },
            com.android.volley.Response.ErrorListener { findViewById<TextView>(R.id.TextView1).text =  "That did not work!"})

        q.add(stringRequest)
    }

    private fun run(url: String) {
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) = println(response.body()?.string())
        })
    }
}
