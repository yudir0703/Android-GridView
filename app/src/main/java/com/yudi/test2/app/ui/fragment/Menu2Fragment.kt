package com.yudi.test2.app.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.yudi.test2.R
import com.yudi.test2.api.models.explore.DataExplore
import com.yudi.test2.app.base.BaseFragment
import com.yudi.test2.app.ui.adapter.ExploreAdapter
import com.yudi.test2.app.ui.viewmodel.ExploreViewModel
import com.yudi.test2.databinding.Menu2FragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * @author Yudi Rahmat
 */


class Menu2Fragment : BaseFragment() {
    private val exploreViewModel: ExploreViewModel by viewModel()
    private lateinit var binding: Menu2FragmentBinding
    private lateinit var mContext: Context

    private lateinit var exploreAdapter : ExploreAdapter
    private var listData: ArrayList<DataExplore?> = ArrayList()

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding = DataBindingUtil.inflate<Menu2FragmentBinding>( inflater, R.layout.menu2_fragment, container, false).apply {}

        (activity as? AppCompatActivity)?.supportActionBar?.hide()
        setupExploreAdapter(inflater, container)

        return binding.root
    }

    private fun fetchExplore() {
        listData.clear()

        exploreViewModel.loadData("")
        exploreViewModel.getExploreList().observe(this, Observer {
            it.let {
                it?.forEach {data ->
                    listData.add(data)
                }

                exploreAdapter?.notifyDataSetChanged()
            }
        })
    }

    private fun setupExploreAdapter(inflater: LayoutInflater, container: ViewGroup?) {
        exploreAdapter  = ExploreAdapter(R.layout.view_row_explore, listData)
        val view: View  = inflater.inflate(R.layout.view_nodata, container, false)

        binding.rvList.layoutManager = GridLayoutManager(context, 3)
        binding.rvList.adapter = exploreAdapter
        exploreAdapter.emptyView = view
        exploreAdapter.notifyDataSetChanged()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onResume() {
        super.onResume()

        fetchExplore()
    }

}
