package io.portone.portonesdk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import io.portone.portonesdk.databinding.FragmentJsonInputPaymentTestBinding
import io.portone.sdk.payment.PaymentCallback
import io.portone.sdk.payment.PaymentRequest
import io.portone.sdk.payment.PaymentResponse
import io.portone.sdk.PortOne
import kotlinx.serialization.json.Json

class JsonInputPaymentTestFragment : BaseFragment<FragmentJsonInputPaymentTestBinding>() {
    private val paymentActivityResultLauncher =
        PortOne.registerForPaymentActivity(this, callback = object : PaymentCallback {
            override fun onSuccess(response: PaymentResponse.Success) {
                AlertDialog.Builder(requireActivity())
                    .setTitle("결제 성공")
                    .setMessage(response.toString())
                    .show()
            }

            override fun onFail(response: PaymentResponse.Fail) {
                AlertDialog.Builder(requireActivity())
                    .setTitle("결제 실패")
                    .setMessage(response.toString())
                    .show()
            }

        })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnPayment.setOnClickListener {
            try {
                PortOne.requestPayment(
                    requireActivity(),
                    request = Json.decodeFromString<PaymentRequest>(binding.etPaymentJson.text.toString()),
                    resultLauncher = paymentActivityResultLauncher
                )
            } catch (e: Exception) {
                Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentJsonInputPaymentTestBinding {
        return FragmentJsonInputPaymentTestBinding.inflate(inflater, container, false)
    }

}