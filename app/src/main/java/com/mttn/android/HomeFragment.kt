package com.mttn.android

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.JsonArrayRequest
import android.widget.ProgressBar




class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    var postsArray: ArrayList<Posts> = ArrayList()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        viewManager = LinearLayoutManager(context)
        viewAdapter = WordpressAdapter(postsArray, context)

        recyclerView = rootView.findViewById<RecyclerView>(R.id.wp_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter

        }
        getWordpressData()
        return rootView
    }


    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    private fun getWordpressData() {
        val cache = DiskBasedCache(context?.cacheDir, 1024 * 1024)
        val network = BasicNetwork(HurlStack())
        val requestQueue = RequestQueue(cache, network).apply {
            start()
        }
        val url = "http://manipalthetalk.org/wp-json/wp/v2/posts"
        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    System.out.println(response)
                    for (i in 0..(response.length() - 1)) {
                        val post = response.getJSONObject(i)
                        val postObj = Posts()
                        postObj.title = (post.getJSONObject("title")).getString("rendered")
                        postObj.post_content = (post.getJSONObject("content")).getString("rendered")
                        postObj.featured_image = ((((post.getJSONObject("better_featured_image")).getJSONObject("media_details")).getJSONObject("sizes")).getJSONObject("thumb-standard")).getString("source_url")
                        postsArray.add(postObj)
                    }
                    viewAdapter.notifyDataSetChanged();
                },
                Response.ErrorListener { error ->
                    System.out.println(error)
                }
        )
        requestQueue.add(jsonArrayRequest)
    }


}