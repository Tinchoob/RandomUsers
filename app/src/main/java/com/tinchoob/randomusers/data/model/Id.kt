package com.tinchoob.randomusers.data.model

import com.squareup.moshi.Json

class Id {

    @Json(name = "name")
    var name: String? = null
    @Json(name = "value")
    var value: String? = null

}
