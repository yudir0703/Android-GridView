package com.yudi.test2.app.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.yudi.test2.R
import com.yudi.test2.api.models.category.CategoryItem
import com.yudi.test2.api.models.users.UserItem
import com.yudi.test2.app.base.BaseFragment
import com.yudi.test2.app.ui.adapter.CategoryAdapter
import com.yudi.test2.app.ui.adapter.UsersAdapter
import com.yudi.test2.api.interfaces.OnItemClickListener
import com.yudi.test2.api.interfaces.ScrollListener
import com.yudi.test2.app.ui.viewmodel.ExploreViewModel
import com.yudi.test2.databinding.Menu1FragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * @author Yudi Rahmat
 */


class Menu1Fragment : BaseFragment() {
    private val homeViewModel: ExploreViewModel by viewModel()
    private lateinit var binding: Menu1FragmentBinding
    private lateinit var mContext: Context

    private lateinit var homeAdapter : UsersAdapter
    private var listData: ArrayList<UserItem?>? = ArrayList()

    private lateinit var categoryAdapter : CategoryAdapter
    private var listCategory: ArrayList<CategoryItem?>? = ArrayList()

    private var currentTotal: Int = 0
    private var lastPage: Int = 1
    var catSelected: String = ""

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding = DataBindingUtil.inflate<Menu1FragmentBinding>( inflater, R.layout.menu1_fragment, container, false).apply {}

        (activity as? AppCompatActivity)?.supportActionBar?.hide()

        setupCategoryAdapter()
        setupHomeAdapter()
        fetchCategories()
        initListener()
        fetchUsers()

        return binding.root
    }

    private fun fetchUsers() {
        listData?.clear()

        catSelected = "a"
        homeViewModel.loadUsers(catSelected, lastPage)
        homeViewModel.getUsers().observe(viewLifecycleOwner, Observer {
            homeAdapter.notifyDataSetChanged()
            it?.let {
                it1 -> listData?.addAll(it1)
                homeAdapter.notifyDataSetChanged()
            }

            updateTabCount(listData?.size!!)
        })
    }

    private fun fetchCategories() {
        listCategory?.clear()

        homeViewModel.loadCategory(getString(R.string.api_url_category))
        homeViewModel.getCategory().observe(viewLifecycleOwner, Observer {
            categoryAdapter.notifyDataSetChanged()
            it?.let {
                it1 -> listCategory?.addAll(it1)
                categoryAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun fetchByCategory(category: String) {
        lastPage        = 1
        currentTotal    = 0
        catSelected     = category

        listData?.clear()
        homeViewModel.loadUsers(category, lastPage)
    }

    private fun setupHomeAdapter() {
        homeAdapter     = UsersAdapter(listData)

        binding.rvList.layoutManager = GridLayoutManager(context, 3)
        binding.rvList.adapter = homeAdapter
        homeAdapter.notifyDataSetChanged()
    }

    private fun setupCategoryAdapter() {
        categoryAdapter = CategoryAdapter(listCategory, object :
            OnItemClickListener {
            override fun onItemClicked(position: Int) {
                val itemData = listCategory?.get(position)
                itemData?.name?.let { fetchByCategory(it.toLowerCase()) }
            }
        })

        binding.rvCategory.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL ,false)
        binding.rvCategory.adapter = categoryAdapter
        categoryAdapter.notifyDataSetChanged()

        ViewCompat.setNestedScrollingEnabled(binding.rvCategory, false);
        ViewCompat.setNestedScrollingEnabled(binding.rvList, false);
    }

    /* setup interface listener from MainFragment
    * NestedScrollView Listener */
    private fun initListener() {
        val fragment: MainFragment? = this.parentFragment as MainFragment?
        fragment?.setScrolledListener(object :
            ScrollListener {
            override fun onScrolledTop() { }

            override fun onScrolledBottom() {
                val mLayoutManager = binding.rvList.layoutManager as LinearLayoutManager
                val currentLastItemPosition = mLayoutManager.findLastVisibleItemPosition()
                if (listData?.size != currentTotal &&
                    listData?.size!! > 1 &&
                    currentLastItemPosition >= listData?.size!! - 1) {
                    currentTotal = listData?.size!!

                    lastPage++
                    homeViewModel.loadUsers(catSelected, lastPage)
                }
            }

        })
    }

    private fun updateTabCount(count: Int) {
        val fragment: MainFragment? = this.parentFragment as MainFragment?
        fragment?.updateTabTitle(0, count.toString())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}
