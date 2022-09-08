package com.example.loginv1.model

data class Despesas (
    var categoria : String ?= null,
    var status : String ?= null,
    var valor : String ?= null,
    var dataVencimento : String ?= null,
    var descricao : String ?= null
)