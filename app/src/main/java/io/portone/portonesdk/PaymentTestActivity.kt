package io.portone.portonesdk

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import com.google.android.material.tabs.TabLayoutMediator
import io.portone.portonesdk.adapter.InputTypeAdapter
import io.portone.portonesdk.databinding.ActivityTestPaymentBinding

class PaymentTestActivity : BaseActivity<ActivityTestPaymentBinding>() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setInputTypeFragment()
    }

    fun setInputTypeFragment() {
        binding.vpRequestPayment.adapter = InputTypeAdapter(this)
        TabLayoutMediator(binding.tlInputType, binding.vpRequestPayment) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "파라미터 입력"
                }
                1 -> {
                    tab.text = "JSON 입력"
                }
            }
        }.attach()

    }
    override fun setViewBinding(inflater: LayoutInflater): ActivityTestPaymentBinding {
        return ActivityTestPaymentBinding.inflate(inflater)
    }

}