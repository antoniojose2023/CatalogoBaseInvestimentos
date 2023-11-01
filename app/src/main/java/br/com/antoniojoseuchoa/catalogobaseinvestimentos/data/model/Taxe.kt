package br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.model

data class Taxe(
    val cdi: Double,
    val cdi_daily: Double,
    val daily_factor: Double,
    val date: String,
    val selic: Double,
    val selic_daily: Double
)