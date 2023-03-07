package com.cristiano.consultacepmvp.data

import com.cristiano.consultacepmvp.model.Address
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CepRemoteDataSource {

    fun findCep(cep: String, callback: CepCallback) {
        HTTPClient.retrofit()
            .create(Endpoint::class.java)
            .getCep(cep)
            .enqueue(object : Callback<Address> {
                override fun onResponse(call: Call<Address>, response: Response<Address>) {
                    if (response.isSuccessful) {
                        val address = response?.body()
                        address?.let { callback.onSuccess(it) }
                    } else {
                        val error = response.errorBody()?.toString()
                        callback.onError(error ?: "Erro desconhecido")
                    }

                    callback.onComplete()
                }

                override fun onFailure(call: Call<Address>, t: Throwable) {
                    callback.onError(t.message ?: "Erro interno")
                    callback.onComplete()
                }
            })//enqueue

    }//findAllCep
}//fim