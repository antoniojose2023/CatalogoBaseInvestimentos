package br.com.antoniojoseuchoa.catalogobaseinvestimentos.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import br.com.antoniojoseuchoa.catalogobaseinvestimentos.data.model.Taxe
import br.com.antoniojoseuchoa.catalogobaseinvestimentos.databinding.ItemTaxasBinding

class AdapterTaxas(val list: MutableList<Taxe>): RecyclerView.Adapter<AdapterTaxas.ViewHolderTaxas>() {

    class ViewHolderTaxas(val binding: ItemTaxasBinding): ViewHolder(binding.root){
         fun bind(item: Taxe){
                binding.apply {
                      tvCDIItem.text = "CDI --> ${item.cdi}%"
                      tvCDIDaily.text = "CDI DAILY --> ${item.cdi_daily}%"
                      tvSelic.text = "SELIC --> ${item.selic}%"

                }
         }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTaxas {
         val layoutInflater = LayoutInflater.from(parent.context)
         val binding = ItemTaxasBinding.inflate( layoutInflater, parent, false )
         return ViewHolderTaxas( binding )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolderTaxas, position: Int) {
        val item = list[position]
        holder.bind( item )

    }

}