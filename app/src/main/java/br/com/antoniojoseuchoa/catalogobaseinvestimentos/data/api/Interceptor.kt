package br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.api

import br.com.antoniojoseuchoa.catalogobaseinvestimentos.Constantes
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.lang.reflect.Method

class InterceptorFinance: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requesicao = chain.request().newBuilder()

//        requesicao.addHeader(
//            "key", "11ab6990"
//        )

        val url = chain.request().url().newBuilder()
        val novaUrl = url.query("${Constantes.KEY_API}").build()

        requesicao.url( novaUrl )

        return chain.proceed( requesicao.build() )

    }
}