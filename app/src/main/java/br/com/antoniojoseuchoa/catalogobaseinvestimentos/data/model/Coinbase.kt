package br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.model

data class Coinbase(
    val buy: Double,
    val format: List<String>,
    val last: Double,
    val name: String,
    val sell: Double,
    val variation: Double
)