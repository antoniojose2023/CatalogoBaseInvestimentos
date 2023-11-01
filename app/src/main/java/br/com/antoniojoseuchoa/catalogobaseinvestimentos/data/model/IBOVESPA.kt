package br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.model

data class IBOVESPA(
    val location: String,
    val name: String,
    val points: Double,
    val variation: Double
)