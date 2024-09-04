package io.portone.portonesdk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import io.portone.portonesdk.databinding.FragmentParameterInputPaymentTestBinding
import io.portone.sdk.PaymentCallback
import io.portone.sdk.PaymentRequest
import io.portone.sdk.PaymentResponse
import io.portone.sdk.PortOne
import io.portone.sdk.entity.CardCompany
import io.portone.sdk.entity.Carrier
import io.portone.sdk.entity.CashReceiptType
import io.portone.sdk.entity.Currency
import io.portone.sdk.entity.Customer
import io.portone.sdk.entity.EasyPayProvider
import io.portone.sdk.entity.GiftCertificateType
import io.portone.sdk.entity.PayMethod
import io.portone.sdk.entity.paymentmethod.Card
import io.portone.sdk.entity.paymentmethod.EasyPay
import io.portone.sdk.entity.paymentmethod.GiftCertificate
import io.portone.sdk.entity.paymentmethod.Mobile
import io.portone.sdk.entity.paymentmethod.Transfer
import io.portone.sdk.entity.paymentmethod.VirtualAccount
import java.util.UUID

class ParameterInputPaymentTestFragment : BaseFragment<FragmentParameterInputPaymentTestBinding>() {
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
            starPaymentActivity()
        }
        binding.etPaymentId.setText(UUID.randomUUID().toString())
        setSpinners()
    }

    private fun starPaymentActivity() {
        try {
            PortOne.requestPayment(
                requireActivity(),
                request = getPaymentRequestByPayMethod(),
                resultLauncher = paymentActivityResultLauncher
            )
        } catch (e: Exception) {
            Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun getPaymentRequestByPayMethod(): PaymentRequest {
        return when (val payMethod = binding.spinnerPayMethod.selectedItem.toString()) {
            "CARD" -> PaymentRequest(
                storeId = binding.etStoreId.text.toString(),
                paymentId = binding.etPaymentId.text.toString(),
                orderName = binding.etOrderName.text.toString(),
                channelKey = binding.etChannelKey.text.toString(),
                totalAmount = binding.etTotalAmount.text.toString().toInt(),
                currency = Currency.valueOf(binding.etCurrency.text.toString()),
                payMethod = PayMethod.valueOf(binding.spinnerPayMethod.selectedItem.toString()),
                customer = Customer(
                    fullName = "fewaf"
                ),
                card = Card(
                    cardCompany = if (!binding.etCardCompany.text?.toString().isNullOrEmpty()) {
                        CardCompany.valueOf(binding.etCardCompany.text.toString())
                    } else null,
                    installment = null,
                    useCardPoint = null,
                    useAppCardOnly = null,
                    availableCards = null,
                    useFreeInterestFromMall = null,
                    useInstallment = null,
                )
            )

            "VIRTUAL_ACCOUNT" -> PaymentRequest(
                storeId = binding.etStoreId.text.toString(),
                paymentId = binding.etPaymentId.text.toString(),
                orderName = binding.etOrderName.text.toString(),
                channelKey = binding.etChannelKey.text.toString(),
                totalAmount = binding.etTotalAmount.text.toString().toInt(),
                currency = Currency.valueOf(binding.etCurrency.text.toString()),
                payMethod = PayMethod.valueOf(binding.spinnerPayMethod.selectedItem.toString()),
                customer = Customer(
                    fullName = "fewaf"
                ),
                virtualAccount = VirtualAccount(
                    cashReceiptType = CashReceiptType.valueOf(binding.spinnerCashReceiptType.selectedItem.toString()),
                    accountExpiry = VirtualAccount.AccountExpiry.ValidHours(1)
                )
            )

            "TRANSFER" -> PaymentRequest(
                storeId = binding.etStoreId.text.toString(),
                paymentId = binding.etPaymentId.text.toString(),
                orderName = binding.etOrderName.text.toString(),
                channelKey = binding.etChannelKey.text.toString(),
                totalAmount = binding.etTotalAmount.text.toString().toInt(),
                currency = Currency.valueOf(binding.etCurrency.text.toString()),
                payMethod = PayMethod.valueOf(binding.spinnerPayMethod.selectedItem.toString()),
                customer = Customer(
                    fullName = "fewaf"
                ),
                transfer = Transfer(
                    cashReceiptType = null,
                    customerIdentifier = null,
                    bankCode = null,
                )
            )

            "MOBILE" -> PaymentRequest(
                storeId = binding.etStoreId.text.toString(),
                paymentId = binding.etPaymentId.text.toString(),
                orderName = binding.etOrderName.text.toString(),
                channelKey = binding.etChannelKey.text.toString(),
                totalAmount = binding.etTotalAmount.text.toString().toInt(),
                currency = Currency.valueOf(binding.etCurrency.text.toString()),
                payMethod = PayMethod.valueOf(binding.spinnerPayMethod.selectedItem.toString()),
                customer = Customer(
                    fullName = "fewaf"
                ),
                mobile = Mobile(
                    carrier = Carrier.valueOf(binding.spinnerCarrier.selectedItem.toString()),
                    availableCarriers = null
                )
            )

            "GIFT_CERTIFICATE" -> PaymentRequest(
                storeId = binding.etStoreId.text.toString(),
                paymentId = binding.etPaymentId.text.toString(),
                orderName = binding.etOrderName.text.toString(),
                channelKey = binding.etChannelKey.text.toString(),
                totalAmount = binding.etTotalAmount.text.toString().toInt(),
                currency = Currency.valueOf(binding.etCurrency.text.toString()),
                payMethod = PayMethod.valueOf(binding.spinnerPayMethod.selectedItem.toString()),
                customer = Customer(
                    fullName = "fewaf"
                ),
                giftCertificate = GiftCertificate(
                    giftCertificateType = GiftCertificateType.valueOf(binding.spinnerGiftCertificateType.selectedItem.toString()),
                ),
            )

            "EASY_PAY" ->
                PaymentRequest(
                    storeId = binding.etStoreId.text.toString(),
                    paymentId = binding.etPaymentId.text.toString(),
                    orderName = binding.etOrderName.text.toString(),
                    channelKey = binding.etChannelKey.text.toString(),
                    totalAmount = binding.etTotalAmount.text.toString().toInt(),
                    currency = Currency.valueOf(binding.etCurrency.text.toString()),
                    payMethod = PayMethod.valueOf(binding.spinnerPayMethod.selectedItem.toString()),
                    customer = Customer(
                        fullName = "fewaf"
                    ),
                    easyPay = EasyPay(
                        easyPayProvider = EasyPayProvider.valueOf(binding.spinnerEasyPayProvider.selectedItem.toString())
                    )
                )

            else -> {
                val errorText = "invalid PayMethod ${payMethod}!"
                Toast.makeText(requireContext(), errorText, Toast.LENGTH_SHORT).show()
                throw Exception(errorText)
            }
        }
    }

    private fun setSpinners() {
        val payMethodSpinner = binding.spinnerPayMethod
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.payment_methods_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            payMethodSpinner.adapter = adapter
        }

        val easyPayProviderSpinner = binding.spinnerEasyPayProvider
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.easy_pay_provider_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            easyPayProviderSpinner.adapter = adapter
        }
        val cashReceiptTypeSpinner = binding.spinnerCashReceiptType
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.cash_receipt_type_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            cashReceiptTypeSpinner.adapter = adapter
        }

        val carrierSpinner = binding.spinnerCarrier
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.carrier_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            carrierSpinner.adapter = adapter
        }

        val giftCertificateTypeSpinner = binding.spinnerGiftCertificateType
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.gift_certificate_type_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            giftCertificateTypeSpinner.adapter = adapter
        }
    }

    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentParameterInputPaymentTestBinding {
        return FragmentParameterInputPaymentTestBinding.inflate(inflater, container, false)
    }


}