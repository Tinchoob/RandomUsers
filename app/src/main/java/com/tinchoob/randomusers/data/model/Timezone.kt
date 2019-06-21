package com.tinchoob.randomusers.data.model

import com.squareup.moshi.Json

class Timezone {

    @Json(name = "offset")
    var offset: String? = null
    @Json(name = "description")
    var description: String? = null

}
