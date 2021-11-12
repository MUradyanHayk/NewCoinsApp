package com.example.newcoinstatsapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.newcoinstatsapp.fragment.AllCoinsFragment
import com.example.newcoinstatsapp.fragment.FavoriteCoinsFragment

class ViewPagerFragmentStateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AllCoinsFragment()
            1 -> FavoriteCoinsFragment()
            else -> AllCoinsFragment()
        }
    }

}