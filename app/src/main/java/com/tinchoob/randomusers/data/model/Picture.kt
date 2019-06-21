package com.tinchoob.randomusers.data.model

import com.squareup.moshi.Json

class Picture {

    @Json(name = "large")
    var large: String? = null
    @Json(name = "medium")
    var medium: String? = null
    @Json(name = "thumbnail")
    var thumbnail: String? = null

}
