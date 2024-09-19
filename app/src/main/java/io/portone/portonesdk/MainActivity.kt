package io.portone.portonesdk

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import io.portone.portonesdk.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnPayment.setOnClickListener {
            startActivity(Intent(this@MainActivity, PaymentTestActivity::class.java))
        }
        binding.btnIssueBillingKey.setOnClickListener {
            startActivity(Intent(this@MainActivity, IssueBillingKeyTestActivity::class.java))
        }
        binding.btnIdentityVerification.setOnClickListener {
            startActivity(Intent(this@MainActivity, IdentityVerificationTestActivity::class.java))
        }
        binding.btnIssueBillingKeyAndPay.setOnClickListener {
            startActivity(Intent(this@MainActivity, IssueBillingKeyAndPayTestActivity::class.java))
        }
        binding.btnLoadPaymentUi.setOnClickListener {
            startActivity(Intent(this@MainActivity, LoadPaymentUITestActivity::class.java))
        }
    }

    override fun setViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }
}