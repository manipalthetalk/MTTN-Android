package com.mttn.android

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.webkit.WebView


import kotlinx.android.synthetic.main.activity_post_detail.*

class PostDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)
        setSupportActionBar(toolbar)

        val postObj = intent.getSerializableExtra("EXTRA_POST") as Posts

        supportActionBar?.setTitle(Html.fromHtml(postObj.title))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        findViewById<WebView>(R.id.post_content).loadDataWithBaseURL(null, "<style>img{display: inline;height: auto;max-width: 100%;}</style>"+postObj.post_content, "text/html", "utf-8", null)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

}
