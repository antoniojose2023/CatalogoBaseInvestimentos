package br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.model

data class Bitcoin(
    val bitstamp: Bitstamp,
    val blockchain_info: BlockchainInfo,
    val coinbase: Coinbase,
    val foxbit: Foxbit,
    val mercadobitcoin: Mercadobitcoin
)