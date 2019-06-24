package com.tinchoob.randomusers.data.model

import com.squareup.moshi.Json

class User {

    @Json(name = "results")
    var results: MutableList<Result>? = null
    @Json(name = "info")
    var info: Info? = null

}
