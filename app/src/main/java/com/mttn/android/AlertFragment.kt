package com.mttn.android

import android.content.ContentValues.TAG
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener


class AlertFragment : Fragment() {

    var database = FirebaseDatabase.getInstance()
    var alertsRef = database.getReference("Alerts")
    var alerts: ArrayList<Alerts> = ArrayList()

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_alert, container, false)
        val viewManager = LinearLayoutManager(context)
        viewAdapter = AlertsAdapter(alerts, context)

        recyclerView = rootView.findViewById<RecyclerView>(R.id.alert_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        FirebaseApp.initializeApp(context);
        alertsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (alertSnapshot in dataSnapshot.getChildren()){
                    val alert = alertSnapshot.getValue(Alerts::class.java)
                    alerts.add(alert!!)
                }
                viewAdapter.notifyDataSetChanged();
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })

        return rootView
    }

    companion object {
        fun newInstance(): AlertFragment = AlertFragment()
    }
}