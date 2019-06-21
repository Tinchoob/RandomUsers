package com.tinchoob.randomusers.data.model

import com.squareup.moshi.Json

class Result {

    @Json(name = "gender")
    var gender: String? = null
    @Json(name = "name")
    var name: Name? = null
    @Json(name = "location")
    var location: Location? = null
    @Json(name = "email")
    var email: String? = null
    @Json(name = "login")
    var login: Login? = null
    @Json(name = "dob")
    var dob: Dob? = null
    @Json(name = "registered")
    var registered: Registered? = null
    @Json(name = "phone")
    var phone: String? = null
    @Json(name = "cell")
    var cell: String? = null
    @Json(name = "id")
    var id: Id? = null
    @Json(name = "picture")
    var picture: Picture? = null
    @Json(name = "nat")
    var nat: String? = null

}
