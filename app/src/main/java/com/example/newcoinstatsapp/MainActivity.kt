package com.example.newcoinstatsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.newcoinstatsapp.adapter.ViewPagerFragmentStateAdapter
import com.example.newcoinstatsapp.viewModel.CoinViewModel
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    var coinViewModel: CoinViewModel? = null
    var viewPager: ViewPager2? = null
    var tabLayout: TabLayout? = null
    var fragmentStateAdapter: ViewPagerFragmentStateAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createViewModel()
        createTabLayout()
    }

    fun createViewModel() {
        coinViewModel = ViewModelProvider(this).get(CoinViewModel::class.java)
    }

    fun createTabLayout() {
        viewPager = findViewById(R.id.view_pager2)
        tabLayout = findViewById(R.id.tab_layout)
        fragmentStateAdapter = ViewPagerFragmentStateAdapter(supportFragmentManager, lifecycle)

        viewPager?.adapter = fragmentStateAdapter

        tabLayout?.addTab(tabLayout!!.newTab().setText(getString(R.string.all_title_text)))
        tabLayout?.addTab(tabLayout!!.newTab().setText(getString(R.string.favorite_title_text)))

        tabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab == null) {
                    return
                }
                viewPager?.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        viewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout?.selectTab(tabLayout?.getTabAt(position))
            }
        })
    }
}