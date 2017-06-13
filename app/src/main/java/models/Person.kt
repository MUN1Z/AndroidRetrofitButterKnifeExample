package models

import com.google.gson.annotations.SerializedName

/**
 * Created by Felip on 13/06/2017.
 */

class Person {

    @SerializedName("name")
    var name: String? = null

    @SerializedName("surname")
    var surname: String? = null

    @SerializedName("gender")
    var gender: String? = null

    @SerializedName("region")
    var region: String? = null

    @SerializedName("age")
    var age: String? = null

    @SerializedName("phone")
    var phone: String? = null

    @SerializedName("photo")
    var photo: String? = null
}