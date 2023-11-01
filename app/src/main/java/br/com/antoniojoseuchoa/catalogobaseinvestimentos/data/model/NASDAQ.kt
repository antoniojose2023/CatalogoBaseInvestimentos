package br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.model

data class NASDAQ(
    val location: String,
    val name: String,
    val points: Double,
    val variation: Double
)