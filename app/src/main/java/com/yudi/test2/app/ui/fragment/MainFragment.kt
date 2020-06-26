package com.yudi.test2.app.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.yudi.test2.R
import com.yudi.test2.api.interfaces.ScrollListener
import com.yudi.test2.app.base.BaseFragment
import com.yudi.test2.app.common.setImage
import com.yudi.test2.databinding.MainFragmentBinding


/**
 * @author Yudi Rahmat
 */


class MainFragment : BaseFragment() {
    private lateinit var binding: MainFragmentBinding
    private lateinit var mContext: Context

    private lateinit var listener: ScrollListener

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding = DataBindingUtil.inflate<MainFragmentBinding>( inflater, R.layout.main_fragment, container, false).apply {}

        (activity as? AppCompatActivity)?.supportActionBar?.hide()
        initView()

        return binding.root
    }

    private fun initView() {
        initHeader()
        initViewpager()
        initTablayout()
    }

    private fun initHeader() {
        val photo = getString(R.string.tv_label_sample_0)
        setImage(true, binding.ivPreview, photo, mContext)

        binding.collapsingToolbar.title = getString(R.string.toolbar_title_1)
        binding.collapsingToolbar.setCollapsedTitleTextColor(ContextCompat.getColor(mContext, R.color.colorWhite))
        binding.collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(mContext, R.color.transparent))

        binding.nvContent.setOnScrollChangeListener { v: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if (v.getChildAt(v.childCount - 1) != null) {
                if (scrollY >= v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight &&
                    scrollY > oldScrollY
                ) {
                    if(listener != null) listener.onScrolledBottom()
                }
            }
        }
    }

    private fun initTablayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            tab.icon = when (position) {
                0 -> mContext.getDrawable(R.drawable.ic_action_cart)
                else -> mContext.getDrawable(R.drawable.ic_menu_add)
            }

            tab.text = when (position) {
                0 -> getString(R.string.tab_title_0)
                else -> getString(R.string.tab_title_0)
            }
        }.attach()
    }

    private fun initViewpager() {
        binding.viewpager.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> {
                        Menu1Fragment()
                    }
                    else -> {
                        Menu2Fragment()
                    }
                }
            }

            override fun getItemCount(): Int {
                return 2
            }
        }
        binding.viewpager.isUserInputEnabled = false // disable swipe
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    fun updateTabTitle(index: Int, label: String) {
        binding.tabLayout.getTabAt(index)?.text = label
    }

    fun setScrolledListener(listener: ScrollListener) {
        this.listener = listener
    }
}
