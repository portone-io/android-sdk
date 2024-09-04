package io.portone.portonesdk.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import io.portone.portonesdk.JsonInputPaymentTestFragment
import io.portone.portonesdk.ParameterInputPaymentTestFragment

class InputTypeAdapter(activity : FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                ParameterInputPaymentTestFragment()
            }
            1-> {
                JsonInputPaymentTestFragment()
            }
            else -> {
                ParameterInputPaymentTestFragment()
            }
        }
    }
}