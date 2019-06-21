package com.tinchoob.randomusers.data.model

import com.squareup.moshi.Json

class Login {

    @Json(name = "uuid")
    var uuid: String? = null
    @Json(name = "username")
    var username: String? = null
    @Json(name = "password")
    var password: String? = null
    @Json(name = "salt")
    var salt: String? = null
    @Json(name = "md5")
    var md5: String? = null
    @Json(name = "sha1")
    var sha1: String? = null
    @Json(name = "sha256")
    var sha256: String? = null

}
