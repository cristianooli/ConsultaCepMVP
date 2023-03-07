package com.cristiano.consultacepmvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.cristiano.consultacepmvp.data.CepRemoteDataSource
import com.cristiano.consultacepmvp.model.Address
import com.cristiano.consultacepmvp.presentation.CepPresenter

class MainActivity : AppCompatActivity() {
    private lateinit var editConsultaCEP : EditText
    private lateinit var editLogradouro : EditText
    private lateinit var editComplemento : EditText
    private lateinit var editCidade : EditText
    private lateinit var editBairro : EditText
    private lateinit var editUF : EditText
    private lateinit var editIBGE : EditText
    private lateinit var editDDD : EditText
    private lateinit var editSiafi : EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var btnConsultar : Button
    private lateinit var presenter: CepPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataSource = CepRemoteDataSource()
        presenter = CepPresenter(this,dataSource)

        editConsultaCEP = findViewById(R.id.editConsultaCEP) as EditText
        editLogradouro = findViewById(R.id.editLogradouro) as EditText
        editComplemento = findViewById(R.id.editComplemento) as EditText
        editCidade = findViewById(R.id.editCidade) as EditText
        editBairro = findViewById(R.id.editBairro) as EditText
        editUF = findViewById(R.id.editUF) as EditText
        editIBGE = findViewById(R.id.editIBGE) as EditText
        editDDD = findViewById(R.id.editDDD) as EditText
        editSiafi = findViewById(R.id.editSiafi) as EditText

        progressBar = findViewById(R.id.progress_bar)
        btnConsultar = findViewById(R.id.btnConsulta) as Button

        //coloca o focus no consultaCep
        editConsultaCEP.requestFocus()
        //inicia o progressbar desligado
        progressBar.visibility = View.GONE

        btnConsultar.setOnClickListener(View.OnClickListener setOnClickListener@{
            if(!validate()){
                Toast.makeText(this,"O campo cep deve ser preenchido!",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            presenter.findCep(editConsultaCEP.text.trim().toString())
        })
    }

    private fun validate(): Boolean {
        return (editConsultaCEP.text.trim().toString().isNotEmpty()
                && !editConsultaCEP.text.toString().startsWith("0")
               )
    }

    fun showAddress(response: Address){
        editLogradouro.setText(response.logradouro)
        editComplemento.setText(response.complemento)
        editBairro.setText(response.bairro)
        editCidade.setText(response.localidade)
        editUF.setText(response.uf)
        editIBGE.setText(response.ibge)
        editDDD.setText(response.ddd)
        editSiafi.setText(response.siafi)
    }

    fun showProgress(){
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgress(){
        progressBar.visibility = View.GONE
    }

    fun showFailure(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}