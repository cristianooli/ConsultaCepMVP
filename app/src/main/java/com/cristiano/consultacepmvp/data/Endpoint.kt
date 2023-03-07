package com.cristiano.consultacepmvp.data

import com.cristiano.consultacepmvp.model.Address

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Endpoint{

    @GET("/ws/{cep}/json/")
    fun getCep(@Path(value = "cep") cep: String) : Call<Address>
}