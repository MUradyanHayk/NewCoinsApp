package com.example.newcoinstatsapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newcoinstatsapp.MainActivity
import com.example.newcoinstatsapp.R
import com.example.newcoinstatsapp.adapter.CoinsAdapter
import com.example.newcoinstatsapp.viewModel.CoinViewModel

class FavoriteCoinsFragment : Fragment() {
    var viewModel: CoinViewModel? = null
    var screen: View? = null
    var recyclerView: RecyclerView? = null
    var coinsAdapter: CoinsAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        screen = inflater.inflate(R.layout.fragment_favorite_coins, container, false)
        viewModel = (activity as? MainActivity?)?.coinViewModel

        createRecyclerView()

        return screen
    }

    private fun createRecyclerView() {
        recyclerView = screen?.findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        coinsAdapter = CoinsAdapter(requireContext(), viewModel?.getFavoriteCoins()?.value)
        recyclerView?.adapter = coinsAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel?.initialize(requireContext())
        viewModel?.download(requireContext())
        viewModel?.getFavoriteCoins()?.observe(viewLifecycleOwner) {
            coinsAdapter?.coinList = it
            coinsAdapter?.notifyDataSetChanged()
        }

        viewModel?.isFavorite?.observe(viewLifecycleOwner) {
            coinsAdapter?.notifyDataSetChanged()
        }
    }

}