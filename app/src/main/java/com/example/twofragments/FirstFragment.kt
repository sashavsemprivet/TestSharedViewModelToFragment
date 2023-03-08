package com.example.twofragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.twofragments.databinding.FragmentFirstBinding

class FirstFragment : Fragment(), ClickAdapter {

    lateinit var binding: FragmentFirstBinding
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val adapter = CatAdapter(mutableListOf<Cat>(), this)

        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getCats()
        lifecycleScope.launchWhenStarted {
            viewModel.stateListCat.collect {
                adapter.addData(it)
            }
        }
    }

    override fun onClick(cat: Cat) {
        viewModel.update(cat)
    }
}