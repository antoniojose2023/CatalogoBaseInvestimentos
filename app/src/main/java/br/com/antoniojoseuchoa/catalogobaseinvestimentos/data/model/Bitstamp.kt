package br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.model

data class Bitstamp(
    val buy: Int,
    val format: List<String>,
    val last: Int,
    val name: String,
    val sell: Int,
    val variation: Double
)