package br.com.antoniojoseuchoa.catalogobaseinvestimentos.presenter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.antoniojoseuchoa.catalogobaseinvestimentos.R
import br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.model.Bitcoin
import br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.model.Currencies
import br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.model.DOWJONES
import br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.model.IBOVESPA
import br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.model.NASDAQ
import br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.model.NIKKEI
import br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.model.Stocks
import br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.model.Taxe
import br.com.antoniojoseuchoa.catalogobaseinvestimentos.databinding.ActivityMainBinding
import br.com.antoniojoseuchoa.catalogobaseinvestimentos.presenter.AdapterTaxas
import br.com.antoniojoseuchoa.catalogobaseinvestimentos.presenter.viewmodel.ViewModelFinance
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModelFinance by viewModels<ViewModelFinance>()

      private lateinit var listaTaxas : MutableList<Taxe>
      private lateinit var currencies: Currencies
      private lateinit var stocks: Stocks
      private lateinit var bitcoin: Bitcoin


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        configurarSlide()
        configuraTelaCheia()


        //clique do botoes
        binding.buttoIbovespa.setOnClickListener(this)
        binding.buttonNasdaq.setOnClickListener(this)
        binding.buttonDownJones.setOnClickListener(this)
        binding.buttonNikei.setOnClickListener(this)

        binding.swipeRefresh.setOnRefreshListener {
            atualizaDadosTela()
        }


    }

    fun atualizaDadosTela(){
        viewModelFinance.finance.observe(this) { states ->
            when (states) {
                ViewModelFinance.States.Loading -> {
                       binding.swipeRefresh.isRefreshing = true
                }

                is ViewModelFinance.States.OnError -> {
                    val error = states.erro
                    //binding.tvTituloTaxas.text = error.toString()
                    binding.swipeRefresh.isRefreshing = false
                }

                is ViewModelFinance.States.OnSucess -> {
                    val finance = states.finance

                    stocks = finance.results.stocks
                    listaTaxas = finance.results.taxes.toMutableList()
                    currencies = finance.results.currencies
                    bitcoin = finance.results.bitcoin


                    preencherCartaoAcoes(stocks.IBOVESPA)
                    configuraRecyclerView( listaTaxas )
                    recuperarCurrencies( currencies )
                    popularInfoBitcoin( bitcoin )

                    binding.swipeRefresh.isRefreshing = false
                }
            }
        }
    }


    override fun onStart() {
        atualizaDadosTela()
        super.onStart()

    }

    fun configuraTelaCheia(){
        val decoration = window.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        decoration.systemUiVisibility = uiOptions

    }

    fun configurarSlide(){
        val imageList = ArrayList<SlideModel>() // Create image list

        imageList.add(SlideModel("https://img.freepik.com/fotos-gratis/trabalhadores-de-escritorio-usando-graficos-de-financas_23-2150408669.jpg?w=1380&t=st=1698802136~exp=1698802736~hmac=272f56f4f8b14c30b4b5fd4094c491362e2e68a95b3a5169d451b0709378fa62", "Planeje seu investimentos", scaleType = ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel("https://img.freepik.com/fotos-gratis/closeup-de-economista-usando-calculadora-ao-passar-por-contas-e-impostos-no-escritorio_637285-3156.jpg?w=1380&t=st=1698802230~exp=1698802830~hmac=9c15d4652bbdff5d4269972c1069a11db0e6f3675be8f95270856c0d29726d4a", "Estude quais são os melhores itens para investir", scaleType = ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel("https://img.freepik.com/fotos-gratis/conceito-de-negocio-com-holografia-grafica_23-2149160929.jpg?w=1380&t=st=1698802279~exp=1698802879~hmac=db21f7f48cfb9efc0bfa167cd60c018ca0e50b8041757a4f69e78610dc25c737", "Observe as principais taxas do mercado.", scaleType = ScaleTypes.CENTER_CROP))


        binding.imageSlider.setImageList( imageList )
        binding.imageSlider.startSliding(10000)
    }

    override fun onClick(button: View?) {
        when(button?.id){
            R.id.buttoIbovespa ->{
                preencherCartaoAcoes( stocks.IBOVESPA  )
            }
            R.id.buttonNasdaq->{
                preencherCartaoAcoes( stocks.NASDAQ  )
            }

            R.id.buttonDownJones -> {
              preencherCartaoDownJones(stocks.DOWJONES)
            }

            R.id.buttonNikei ->{
                preencherCartaoNikei( stocks.NIKKEI )
            }

        }
    }


    fun preencherCartaoAcoes(bolsa: IBOVESPA){
            binding.apply {
                tvNomeBolsa.text = bolsa.name
                tvLocalicao.text = bolsa.location
                tvPontos.text = "Pontos: ${bolsa.points}"
                tvVariacao.text = "Variação : ${bolsa.variation}"
            }
    }

    fun preencherCartaoAcoes(bolsa: NASDAQ){
           binding.apply {
               tvNomeBolsa.text = bolsa.name
               tvLocalicao.text = bolsa.location
               tvPontos.text = "Pronto: ${bolsa.points}"
               tvVariacao.text = "Variação: ${bolsa.variation}"
         }
    }

    fun preencherCartaoDownJones(bolsa: DOWJONES){
        binding.apply {
            tvNomeBolsa.text = bolsa.name
            tvLocalicao.text = bolsa.location
            tvPontos.text = "Pontos: ${bolsa.points}"
            tvVariacao.text = "Variação : ${bolsa.variation}"
        }
    }

    fun preencherCartaoNikei(bolsa: NIKKEI){
        binding.apply {
            tvNomeBolsa.text = bolsa.name
            tvLocalicao.text = bolsa.location
            tvPontos.text = "Pontos: ${bolsa.points}"
            tvVariacao.text = "Variação : ${bolsa.variation}"
        }
    }


    fun configuraRecyclerView(list: MutableList<Taxe>){

        var adapterTaxas = AdapterTaxas(list)
        binding.rvTaxas.adapter = adapterTaxas
        binding.rvTaxas.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
    }


    fun recuperarCurrencies(currencies: Currencies){
        var resultado = ""
        resultado = "Moeda: ${currencies.ARS.name} - Preço: ${currencies.ARS.buy} - Venda: ${currencies.ARS.sell} - Variação: ${currencies.ARS.variation}    "+
                "Moeda: ${currencies.ARS.name} - Preço: ${currencies.ARS.buy} - Venda: ${currencies.ARS.sell} - Variação: ${currencies.ARS.variation}    " +
                "Moeda: ${currencies.ARS.name} - Preço: ${currencies.ARS.buy} - Venda: ${currencies.ARS.sell} - Variação: ${currencies.ARS.variation}    "

        binding.tvRecuperarMoedas.text = resultado
    }


    fun popularInfoBitcoin(bitcoin: Bitcoin){
           binding.includeInfoBitcoin.apply {

                  //blockchaininfo
                  tvNomeBitcoin.text = bitcoin.blockchain_info.name
                  tvCompraBitcoin.text = "Compra: ${bitcoin.blockchain_info.buy}"
                  tvVendaBitcoin.text = "Venda: ${bitcoin.blockchain_info.sell}"
                  tvVariacaoBitcoin.text = "Variação: ${bitcoin.blockchain_info.variation}"

                  //mercadobitcoin
                  tvNomeMercadoBitcoin.text = bitcoin.mercadobitcoin.name
                  tvCompraMercadoBitcoin.text = "Compra: ${bitcoin.mercadobitcoin.buy}"
                  tvVendaMercadoBitcoin.text = "Venda: ${bitcoin.mercadobitcoin.sell}"
                  tvVariacaoMercadoBitcoin.text = "Variação: ${bitcoin.mercadobitcoin.variation}"

                  //coinbase
                  tvNomeCoinbase.text = bitcoin.coinbase.name
                  tvCompraCoinbase.text = "Compra: ${bitcoin.coinbase.buy}"
                  tvVendaCoinbase.text = "Venda: ${bitcoin.coinbase.sell}"
                  tvVariacaoCoinbase.text = "Variação: ${bitcoin.coinbase.variation}"

                  //bisstamp
                  tvNomeMercadoFoxbit.text = bitcoin.bitstamp.name
                  tvCompraMercadoFoxbit.text = "Compra: ${bitcoin.bitstamp.buy}"
                  tvVendaMercadoFoxbit.text = "Venda: ${bitcoin.bitstamp.sell}"
                  tvVariacaoFoxbit.text = "Variação: ${bitcoin.bitstamp.variation}"
          }

    }

}