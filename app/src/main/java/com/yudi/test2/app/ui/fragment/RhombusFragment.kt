package com.yudi.test2.app.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.yudi.test2.R
import com.yudi.test2.app.base.BaseFragment
import com.yudi.test2.app.common.Common
import com.yudi.test2.databinding.RhombusFragmentBinding


/**
 * Created by Yudi Rahmat
 */
class RhombusFragment : BaseFragment() {
    private lateinit var binding: RhombusFragmentBinding
    private lateinit var mContext: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding = DataBindingUtil.inflate<RhombusFragmentBinding>( inflater, R.layout.rhombus_fragment, container, false).apply {}

        (activity as? AppCompatActivity)?.supportActionBar?.hide()
        initView()

        return binding.root
    }

    private fun initView() {
        binding.btnCheck.setOnClickListener {
            binding.etInput.text.toString().toIntOrNull()?.let { it1 -> showResults(it1) }
        }

    }

    private fun showResults(numberTemp: Int) {
        var value: String = ""

        if(numberTemp < 5 || numberTemp > 100) {
            Common.showToast(mContext, "Please input number 5 - 100")
            return
        }

        var modResult: Int  = numberTemp % 2
        var number: Int = modResult + (numberTemp / 2)

        if(modResult == 0) number++
        for (i in 0 until number) {
            for (j in i downTo 0) {
                if(modResult == 0 && i == 0 && j == 0) {  // if mod result 0
                    value += ""
                } else {
                    if (i == j || j == 0)
                        value += "*"
                    else
                        value += "  "
                }
            }
            value += "\n"
        }

        if(modResult == 0) number++
        for (i in 0 until number - 1) {
            for (j in i until number - 1) {
                if(modResult == 0 && i == j && j == (number - 2)) { // if mod result 0
                    value += ""
                } else {
                    if (i == j || j == (number - 2))
                        value += "*"
                    else
                        value += "  "
                }
            }
            value += "\n"
        }

        binding.tvResult.text = value
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

}
