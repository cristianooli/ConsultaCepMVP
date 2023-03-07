package com.cristiano.consultacepmvp.presentation

import com.cristiano.consultacepmvp.model.Address
import com.cristiano.consultacepmvp.MainActivity
import com.cristiano.consultacepmvp.data.CepRemoteDataSource
import com.cristiano.consultacepmvp.data.CepCallback

class CepPresenter(private val view: MainActivity,private val dataSource: CepRemoteDataSource):CepCallback {

    fun findCep(cep:String){
        view.showProgress()
        dataSource.findCep(cep,this)
    }

    override fun onComplete() {
        view.hideProgress()
    }

    override fun onSuccess(response: Address) {
        view.showAddress(response)
    }

    override fun onError(message: String) {
        view.showFailure(message)
    }
}