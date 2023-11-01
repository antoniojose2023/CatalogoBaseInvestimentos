package br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.repository

import android.util.Log
import br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.api.ApiHGBrasil
import br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.model.Finance
import retrofit2.Response
import javax.inject.Inject

class RepositoryFinanceImpl @Inject constructor(private val apiHGBrasil: ApiHGBrasil): RepositoryFinance {
    override suspend fun getFinance(): Finance {
          //var response: Response<Finance>? = null
          var finance: Finance? = null

          try {
               val response = apiHGBrasil.getFinance()
               if(response != null){
                   finance = response.body()
                   return finance!!
               }
          }catch (ex: Exception){
              Log.i("TAG2", "getFinance: "+ex.message)
          }

         return finance!!

    }
}