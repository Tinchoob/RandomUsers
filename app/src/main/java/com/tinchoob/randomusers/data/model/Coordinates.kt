package com.tinchoob.randomusers.data.model

import com.squareup.moshi.Json

class Coordinates {

    @Json(name = "latitude")
    var latitude: String? = null
    @Json(name = "longitude")
    var longitude: String? = null

}
