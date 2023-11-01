package br.com.antoniojoseuchoa.catalogobaseinvestimentos.di

import android.os.Build
import androidx.annotation.RequiresApi
import br.com.antoniojoseuchoa.catalogobaseinvestimentos.Constantes
import br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.api.ApiHGBrasil
import br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.api.InterceptorFinance
import br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.repository.RepositoryFinance
import br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.repository.RepositoryFinanceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    fun providerOkHttpCliente(): OkHttpClient{
        return OkHttpClient().newBuilder()
            .addInterceptor(InterceptorFinance())
            .readTimeout(Duration.ofSeconds(5))
            .writeTimeout(Duration.ofSeconds(5))
            .connectTimeout(Duration.ofSeconds(5))
            .build()
    }


    @Provides
    fun providerRetrofit(okHttpClient: OkHttpClient): Retrofit {
          return Retrofit.Builder()
                 .baseUrl(Constantes.BASE_URL)
                 .addConverterFactory(GsonConverterFactory.create())
                 .client(okHttpClient)
                 .build()
    }

    @Provides
    fun providerApiHGBrasil(retrofit: Retrofit): ApiHGBrasil{
            return retrofit.create( ApiHGBrasil::class.java )
    }

    @Provides
    fun providerRepositoryFinance(apiHGBrasil: ApiHGBrasil): RepositoryFinance{
            return RepositoryFinanceImpl(apiHGBrasil)
    }


}