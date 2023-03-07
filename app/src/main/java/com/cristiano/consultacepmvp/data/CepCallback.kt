package com.cristiano.consultacepmvp.data

import com.cristiano.consultacepmvp.model.Address

interface CepCallback {
    fun onComplete()
    fun onSuccess(response: Address)
    fun onError(message: String)
}