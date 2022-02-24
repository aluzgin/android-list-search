package npo.kib.dictionarysearch.ui

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import npo.kib.dictionarysearch.databinding.DictionaryFragmentBinding
import npo.kib.dictionarysearch.ui.adapters.DictionaryAdapter


class DictionaryFragment : Fragment() {
    companion object {
        fun newInstance() = DictionaryFragment()
    }

    private var _binding: DictionaryFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DictionaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DictionaryFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[DictionaryViewModel::class.java]

        val adapter = DictionaryAdapter(viewModel.getAllCountries())

        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    adapter.filter.filter(query)
                    return false
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    adapter.filter.filter(query)
                    return false
                }
            })
            return root
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}