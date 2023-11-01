package br.com.antoniojoseuchoa.catalogobaseinvestimentos.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.model.Finance
import br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.repository.RepositoryFinance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ViewModelFinance @Inject constructor(private val repositoryFinance: RepositoryFinance): ViewModel() {

        private val _finance = MutableLiveData<States>()
        var finance: LiveData<States> = _finance

        init {
            getFinance()
        }

        fun getFinance(){
              _finance.value = States.Loading

              viewModelScope.launch {
                   try {
                       _finance.postValue(States.OnSucess(repositoryFinance.getFinance()))
                   }catch (ex: Exception){
                       _finance.postValue( States.OnError("${ex.message}") )
                   }
              }
        }

    sealed class States{
        object Loading: States()
        class OnSucess(var finance: Finance): States()
        class OnError(var erro: String): States()
    }

}


