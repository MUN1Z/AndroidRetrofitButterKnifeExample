package services

import models.Person
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Muniz on 24/09/2016.
 */

interface Service {
    @GET("?ext")
    fun GetPerson(): Call<Person>
}
