package com.cristiano.consultacepmvp.model

data class Address(
    var cep : String,
    var logradouro : String,
    var complemento : String,
    var localidade : String,
    var bairro : String,
    var uf : String,
    var ibge : String,
    var gia: String,
    var ddd: String,
    var siafi: String
)

