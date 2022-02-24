package npo.kib.dictionarysearch.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import npo.kib.dictionarysearch.R
import npo.kib.dictionarysearch.databinding.DictionaryItemBinding
import java.util.*

class DictionaryAdapter(private val itemList: List<String>) :
    RecyclerView.Adapter<DictionaryAdapter.ItemHolder>(), Filterable {

    private var filteredItemList: List<String>

    init {
        filteredItemList = itemList
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = DictionaryItemBinding.bind(itemView)
        fun bind(name: String) = with(binding) {
            itemName.text = name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.dictionary_item, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(filteredItemList[position])
    }

    override fun getItemCount() = filteredItemList.size

    override fun getFilter() = object : Filter() {
        override fun performFiltering(constraintChar: CharSequence?): FilterResults {
            val constraint = constraintChar.toString()

            val filteredValues = if (constraint.isEmpty()) {
                itemList
            } else {
                val filteredItems = LinkedList<String>()
                itemList.forEach { item ->
                    if (item.contains(constraint, true)) {
                        filteredItems.add(item)
                    }
                }
                filteredItems.toList()
            }

            val filterResults = FilterResults()
            filterResults.values = filteredValues
            return filterResults
        }

        @SuppressLint("NotifyDataSetChanged")
        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraintChar: CharSequence?, filterResults: FilterResults?) {
            filteredItemList = filterResults?.values as List<String>
            notifyDataSetChanged()
        }
    }
}