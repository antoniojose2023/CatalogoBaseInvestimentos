package br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.repository

import br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.model.Finance

interface RepositoryFinance {
    suspend fun getFinance(): Finance
}