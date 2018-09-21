package com.mttn.android

import java.io.Serializable

class Posts: Serializable {
    var post_content: String? = null
    var featured_image: String? = null
    var title: String? = null

}

class Alerts: Serializable {
    var Date: String? = null
    var Body: String? = null
    var Head: String? = null
    var Url: String? = null

}

class PhoneNumber: Serializable{
    var contact_name: String? = null
    var phone_number: String? = null
}

class Directory: Serializable {
    var category: String? = null
    var contacts: ArrayList<PhoneNumber> = ArrayList()
}