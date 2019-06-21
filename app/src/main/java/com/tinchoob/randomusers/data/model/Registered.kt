package com.tinchoob.randomusers.data.model

import com.squareup.moshi.Json

class Registered {

    @Json(name = "date")
    var date: String? = null
    @Json(name = "age")
    var age: Int? = null

}
