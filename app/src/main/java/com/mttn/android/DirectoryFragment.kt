package com.mttn.android

import android.content.ContentValues
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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DirectoryFragment : Fragment() {

    var database = FirebaseDatabase.getInstance()
    var directoryRef = database.getReference("Directory")
    var directory: ArrayList<Directory> = ArrayList()

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        val rootView = inflater.inflate(R.layout.fragment_directory, container, false)

        val viewManager = LinearLayoutManager(context)
        viewAdapter = DirectoryAdapter(directory, context)

        recyclerView = rootView.findViewById<RecyclerView>(R.id.directory_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        FirebaseApp.initializeApp(context);
        directoryRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for(category in dataSnapshot.children)
                {
                    val directoryObj = Directory()
                    directoryObj.category = category.key
                    val phonesArray: ArrayList<PhoneNumber> = ArrayList()
                    for(phone in category.children){
                        val phoneObj = PhoneNumber()
                        phoneObj.contact_name = phone.key
                        phoneObj.phone_number = (phone.value).toString()
                        phonesArray.add(phoneObj)
                    }
                    directoryObj.contacts = phonesArray
                    directory.add(directoryObj)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })

        return rootView
    }


    companion object {
        fun newInstance(): DirectoryFragment = DirectoryFragment()
    }
}