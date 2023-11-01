package br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.api

import br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.model.Finance
import retrofit2.Response
import retrofit2.http.GET

interface ApiHGBrasil {

    @GET("finance")
    suspend fun getFinance(): Response<Finance>

}