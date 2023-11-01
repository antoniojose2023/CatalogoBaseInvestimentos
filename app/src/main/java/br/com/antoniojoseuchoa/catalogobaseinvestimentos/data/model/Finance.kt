package br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.model

data class Finance(
    val `by`: String,
    val execution_time: Int,
    val from_cache: Boolean,
    val results: Results,
    val valid_key: Boolean
)