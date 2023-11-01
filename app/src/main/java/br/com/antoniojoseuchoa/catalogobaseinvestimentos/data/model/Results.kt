package br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.model

data class Results(
    val available_sources: List<String>,
    val bitcoin: Bitcoin,
    val currencies: Currencies,
    val stocks: Stocks,
    val taxes: List<Taxe>
)