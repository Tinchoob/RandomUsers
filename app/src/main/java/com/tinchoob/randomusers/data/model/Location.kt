package com.tinchoob.randomusers.data.model

import com.squareup.moshi.Json

class Location {

    @Json(name = "street")
    var street: String? = null
    @Json(name = "city")
    var city: String? = null
    @Json(name = "state")
    var state: String? = null
    @Json(name = "postcode")
    var postcode: String? = null
    @Json(name = "coordinates")
    var coordinates: Coordinates? = null
    @Json(name = "timezone")
    var timezone: Timezone? = null

}
