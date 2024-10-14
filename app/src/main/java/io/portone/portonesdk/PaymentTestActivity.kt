package io.portone.portonesdk

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import io.portone.portonesdk.adapter.CardCompanyAdapter
import io.portone.portonesdk.adapter.CarrierAdapter
import io.portone.portonesdk.adapter.FreeInstallmentPlanAdapter
import io.portone.portonesdk.databinding.ActivityTestPaymentBinding
import io.portone.sdk.android.payment.PaymentCallback
import io.portone.sdk.android.payment.PaymentRequest
import io.portone.sdk.android.payment.PaymentResponse
import io.portone.sdk.android.PortOne
import io.portone.sdk.android.type.Address
import io.portone.sdk.android.type.Amount
import io.portone.sdk.android.type.Bank
import io.portone.sdk.android.type.BirthDate
import io.portone.sdk.android.type.CardCompany
import io.portone.sdk.android.type.Carrier
import io.portone.sdk.android.type.CashReceiptType
import io.portone.sdk.android.type.Country
import io.portone.sdk.android.type.Currency
import io.portone.sdk.android.type.Customer
import io.portone.sdk.android.type.EasyPayProvider
import io.portone.sdk.android.type.Gender
import io.portone.sdk.android.type.GiftCertificateType
import io.portone.sdk.android.type.Installment
import io.portone.sdk.android.type.PaymentMethod
import java.time.Instant
import java.util.UUID

class PaymentTestActivity : BaseActivity<ActivityTestPaymentBinding>() {
    private val paymentActivityResultLauncher =
        PortOne.registerForPaymentActivity(this, callback = object :
            PaymentCallback {
            override fun onSuccess(response: PaymentResponse.Success) {
                AlertDialog.Builder(this@PaymentTestActivity)
                    .setTitle("결제 성공")
                    .setMessage(response.toString())
                    .show()
            }

            override fun onFail(response: PaymentResponse.Fail) {
                AlertDialog.Builder(this@PaymentTestActivity)
                    .setTitle("결제 실패")
                    .setMessage(response.toString())
                    .show()
            }

        })

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnPayment.setOnClickListener {
            startPaymentActivity()
        }
        binding.etPaymentId.setText(UUID.randomUUID().toString())
        setSpinners()
        setAdapters()
    }


    override fun setViewBinding(inflater: LayoutInflater): ActivityTestPaymentBinding {
        return ActivityTestPaymentBinding.inflate(inflater)
    }

    private fun startPaymentActivity() {
        try {
            PortOne.requestPayment(
                this,
                request = getPaymentRequestByPayMethod(),
                resultLauncher = paymentActivityResultLauncher
            )
        } catch (e: Exception) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun getPaymentRequestByPayMethod(): PaymentRequest {
        return when (val payMethod = binding.spinnerPayMethod.selectedItem.toString()) {
            "CARD" -> PaymentRequest(
                storeId = binding.etStoreId.text.toString(),
                paymentId = binding.etPaymentId.text.toString(),
                orderName = binding.etOrderName.text.toString(),
                channelKey = binding.etChannelKey.text.toString(),
                amount = Amount(
                    total = binding.etTotalAmount.text.toString().toLong(),
                    currency = Currency.valueOf(binding.etCurrency.text.toString()),
                ),
                customer = customer(),
                method = PaymentMethod.Card(
                    cardCompany = if (binding.spinnerCardCompany.selectedItemPosition != 0) {
                        CardCompany.valueOf(binding.spinnerCardCompany.selectedItem.toString())
                    } else null,
                    installment = installment(),
                    useCardPoint = binding.cbUseCardPoint.isChecked,
                    useAppCardOnly = binding.cbUseAppCardOnly.isChecked,
                    availableCards = (binding.rvAvailableCards.adapter as CardCompanyAdapter).getItems(),
                    useFreeInterestFromMall = binding.cbUseFreeInterestFromMall.isChecked,
                    useInstallment = null,
                ),
                bypass = if (!binding.etBypass.text.isNullOrEmpty()) binding.etBypass.text.toString() else null
            )

            "VIRTUAL_ACCOUNT" -> PaymentRequest(
                storeId = binding.etStoreId.text.toString(),
                paymentId = binding.etPaymentId.text.toString(),
                orderName = binding.etOrderName.text.toString(),
                channelKey = binding.etChannelKey.text.toString(),
                amount = Amount(
                    total = binding.etTotalAmount.text.toString().toLong(),
                    currency = Currency.valueOf(binding.etCurrency.text.toString()),
                ),
                customer = customer(),
                method = PaymentMethod.VirtualAccount(
                    cashReceiptType =
                    if (binding.spinnerCashReceiptType.selectedItemPosition != 0) {
                        CashReceiptType.valueOf(binding.spinnerCashReceiptType.selectedItem.toString())
                    } else null,
                    accountExpiry = if (!binding.etValidHours.text.isNullOrEmpty()) {
                        PaymentMethod.VirtualAccount.AccountExpiry.ValidHours(
                            binding.etValidHours.text.toString().toInt()
                        )
                    } else if (!binding.etDueDate.text.isNullOrEmpty()) {
                        PaymentMethod.VirtualAccount.AccountExpiry.DueDate(Instant.parse(binding.etDueDate.text.toString()))
                    } else null
                ),
                bypass = if (!binding.etBypass.text.isNullOrEmpty()) binding.etBypass.text.toString() else null
            )

            "TRANSFER" -> PaymentRequest(
                storeId = binding.etStoreId.text.toString(),
                paymentId = binding.etPaymentId.text.toString(),
                orderName = binding.etOrderName.text.toString(),
                channelKey = binding.etChannelKey.text.toString(),
                amount = Amount(
                    total = binding.etTotalAmount.text.toString().toLong(),
                    currency = Currency.valueOf(binding.etCurrency.text.toString()),
                ),
                customer = customer(),
                method = PaymentMethod.Transfer(
                    cashReceiptType = CashReceiptType.valueOf(binding.spinnerTransferCashReceiptType.selectedItem.toString()),
                    customerIdentifier = binding.etTransferCustomerIdentifier.text.toString(),
                    bankCode = Bank.valueOf(binding.spinnerTransferBankCode.selectedItem.toString()),
                ),
                bypass = if (!binding.etBypass.text.isNullOrEmpty()) binding.etBypass.text.toString() else null
            )

            "MOBILE" -> PaymentRequest(
                storeId = binding.etStoreId.text.toString(),
                paymentId = binding.etPaymentId.text.toString(),
                orderName = binding.etOrderName.text.toString(),
                channelKey = binding.etChannelKey.text.toString(),
                amount = Amount(
                    total = binding.etTotalAmount.text.toString().toLong(),
                    currency = Currency.valueOf(binding.etCurrency.text.toString()),
                ),
                customer = customer(),
                method = PaymentMethod.Mobile(
                    carrier = Carrier.valueOf(binding.spinnerCarrier.selectedItem.toString()),
                    availableCarriers = null
                ),
                bypass = if (!binding.etBypass.text.isNullOrEmpty()) binding.etBypass.text.toString() else null
            )

            "GIFT_CERTIFICATE" -> PaymentRequest(
                storeId = binding.etStoreId.text.toString(),
                paymentId = binding.etPaymentId.text.toString(),
                orderName = binding.etOrderName.text.toString(),
                channelKey = binding.etChannelKey.text.toString(),
                amount = Amount(
                    total = binding.etTotalAmount.text.toString().toLong(),
                    currency = Currency.valueOf(binding.etCurrency.text.toString()),
                ),
                customer = customer(),
                method = PaymentMethod.GiftCertificate(
                    giftCertificateType = GiftCertificateType.valueOf(binding.spinnerGiftCertificateType.selectedItem.toString()),
                ),
                bypass = if (!binding.etBypass.text.isNullOrEmpty()) binding.etBypass.text.toString() else null
            )

            "EASY_PAY" ->
                PaymentRequest(
                    storeId = binding.etStoreId.text.toString(),
                    paymentId = binding.etPaymentId.text.toString(),
                    orderName = binding.etOrderName.text.toString(),
                    channelKey = binding.etChannelKey.text.toString(),
                    amount = Amount(
                        total = binding.etTotalAmount.text.toString().toLong(),
                        currency = Currency.valueOf(binding.etCurrency.text.toString()),
                    ),
                    method = PaymentMethod.EasyPay(
                        easyPayProvider = EasyPayProvider.valueOf(binding.spinnerEasyPayProvider.selectedItem.toString()),
                        installment = installment()
                    ),
                    customer = customer(),
                    bypass = if (!binding.etBypass.text.isNullOrEmpty()) binding.etBypass.text.toString() else null,

                    )

            else -> {
                val errorText = "invalid PayMethod ${payMethod}!"
                Toast.makeText(this, errorText, Toast.LENGTH_SHORT).show()
                throw Exception(errorText)
            }
        }
    }

    private fun setSpinners() {
        val payMethodSpinner = binding.spinnerPayMethod
        ArrayAdapter.createFromResource(
            this,
            R.array.payment_methods_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            payMethodSpinner.adapter = adapter
        }
        payMethodSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (payMethodSpinner.selectedItem.toString()) {
                    "CARD" -> {
                        enableCardParameters()
                        enableInstallmentParameters()
                        disableTransferParameters()
                        disableMobileParameters()
                        disableEasyPayParameters()
                        disableVirtualAccountParameters()
                        disableGiftCertificateParameters()
                    }

                    "GIFT_CERTIFICATE" -> {
                        enableGiftCertificateParameters()
                        disableCardParameters()
                        disableMobileParameters()
                        disableEasyPayParameters()
                        disableVirtualAccountParameters()
                        disableTransferParameters()
                        disableInstallmentParameters()
                    }

                    "MOBILE" -> {
                        enableMobileParameters()
                        disableCardParameters()
                        disableTransferParameters()
                        disableEasyPayParameters()
                        disableVirtualAccountParameters()
                        disableGiftCertificateParameters()
                        disableInstallmentParameters()

                    }

                    "TRANSFER" -> {
                        enableTransferParameters()
                        disableCardParameters()
                        disableMobileParameters()
                        disableEasyPayParameters()
                        disableVirtualAccountParameters()
                        disableGiftCertificateParameters()
                        disableInstallmentParameters()
                    }

                    "VIRTUAL_ACCOUNT" -> {
                        enableVirtualAccountParameters()
                        disableCardParameters()
                        disableMobileParameters()
                        disableEasyPayParameters()
                        disableTransferParameters()
                        disableGiftCertificateParameters()
                        disableInstallmentParameters()
                    }

                    "EASY_PAY" -> {
                        enableEasyPayParameters()
                        enableInstallmentParameters()
                        disableCardParameters()
                        disableMobileParameters()
                        disableVirtualAccountParameters()
                        disableTransferParameters()
                        disableGiftCertificateParameters()
                    }

                    "미선택" -> {
                        disableEasyPayParameters()
                        disableCardParameters()
                        disableMobileParameters()
                        disableVirtualAccountParameters()
                        disableTransferParameters()
                        disableGiftCertificateParameters()
                        disableInstallmentParameters()
                    }

                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        val easyPayProviderSpinner = binding.spinnerEasyPayProvider
        ArrayAdapter.createFromResource(
            this,
            R.array.easy_pay_provider_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            easyPayProviderSpinner.adapter = adapter
        }

        val cashReceiptTypeSpinner = binding.spinnerCashReceiptType
        ArrayAdapter.createFromResource(
            this,
            R.array.cash_receipt_type_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            cashReceiptTypeSpinner.adapter = adapter
        }

        val transferCashReceiptTypeSpinner = binding.spinnerTransferCashReceiptType
        ArrayAdapter.createFromResource(
            this,
            R.array.cash_receipt_type_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            transferCashReceiptTypeSpinner.adapter = adapter
        }

        val genderSpinner = binding.spinnerGender
        ArrayAdapter.createFromResource(
            this,
            R.array.gender_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            genderSpinner.adapter = adapter
        }

        val countrySpinner = binding.spinnerCountry
        ArrayAdapter.createFromResource(
            this,
            R.array.country_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            countrySpinner.adapter = adapter
        }
        val cardCompanySpinner = binding.spinnerCardCompany
        ArrayAdapter.createFromResource(
            this,
            R.array.card_company_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            cardCompanySpinner.adapter = adapter
        }


        val carrierSpinner = binding.spinnerCarrier
        ArrayAdapter.createFromResource(
            this,
            R.array.carrier_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            carrierSpinner.adapter = adapter
        }


        val giftCertificateTypeSpinner = binding.spinnerGiftCertificateType
        ArrayAdapter.createFromResource(
            this,
            R.array.gift_certificate_type_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            giftCertificateTypeSpinner.adapter = adapter
        }
        val bankSpinner = binding.spinnerTransferBankCode
        ArrayAdapter.createFromResource(
            this,
            R.array.bank_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            bankSpinner.adapter = adapter
        }
    }

    private fun disableCardParameters() {
        binding.clCardSpecific.visibility = GONE
    }

    private fun disableGiftCertificateParameters() {
        binding.clGiftCertificateSpecific.visibility = GONE
    }

    private fun disableVirtualAccountParameters() {
        binding.clVirtualAccountSpecific.visibility = GONE
    }

    private fun disableMobileParameters() {
        binding.clMobileSpecific.visibility = GONE
    }

    private fun disableEasyPayParameters() {
        binding.clEasyPaySpecific.visibility = GONE
    }

    private fun disableTransferParameters() {
        binding.clTransferSpecific.visibility = GONE
    }

    private fun disableInstallmentParameters() {
        binding.clInstallment.visibility = GONE
    }

    private fun enableInstallmentParameters() {
        binding.clInstallment.visibility = VISIBLE
    }

    private fun enableCardParameters() {
        binding.clCardSpecific.visibility = VISIBLE
    }

    private fun enableGiftCertificateParameters() {
        binding.clGiftCertificateSpecific.visibility = VISIBLE
    }

    private fun enableVirtualAccountParameters() {
        binding.clVirtualAccountSpecific.visibility = VISIBLE
    }

    private fun enableTransferParameters() {
        binding.clTransferSpecific.visibility = VISIBLE
    }

    private fun enableMobileParameters() {
        binding.clMobileSpecific.visibility = VISIBLE
    }

    private fun enableEasyPayParameters() {
        binding.clEasyPaySpecific.visibility = VISIBLE
    }

    private fun setAdapters() {
        val cardAdapter = CardCompanyAdapter()
        binding.rvAvailableCards.layoutManager = LinearLayoutManager(this)
        binding.rvAvailableCards.adapter = cardAdapter
        binding.btnAddCards.setOnClickListener {
            cardAdapter.addItems(CardCompany.BC_CARD)
        }
        val carrierAdapter = CarrierAdapter()
        binding.rvAvailableCarriers.layoutManager = LinearLayoutManager(this)
        binding.rvAvailableCarriers.adapter = carrierAdapter
        binding.btnAddCarriers.setOnClickListener {
            carrierAdapter.addItems(Carrier.SKT)
        }

        val freeInstallmentPlanAdapter = FreeInstallmentPlanAdapter()
        binding.rvFreeInstallmentPlans.layoutManager = LinearLayoutManager(this)
        binding.rvFreeInstallmentPlans.adapter = freeInstallmentPlanAdapter
        binding.btnAddFreeInstallmentPlans.setOnClickListener {
            freeInstallmentPlanAdapter.addItems(
                Installment.FreeInstallmentPlan(
                    months = listOf(1, 2),
                    cardCompany = CardCompany.BC_CARD
                )
            )
        }
    }

    private fun customer(): Customer {
        return Customer(
            id = if (!binding.etCustomerId.text.isNullOrEmpty()) {
                binding.etCustomerId.text.toString()
            } else null,
            name = if (!binding.etCustomerFullName.text.isNullOrEmpty()) {
                Customer.Name.Full(binding.etCustomerFullName.text.toString())
            } else if (!binding.etCustomerFirstName.text.isNullOrEmpty() || !binding.etCustomerLastName.text.isNullOrEmpty()) {
                Customer.Name.Separated(
                    firstName = binding.tvCustomerFirstName.text.toString(),
                    lastName = binding.tvCustomerLastName.text.toString(),
                )
            } else null,
            phoneNumber = if (!binding.etCustomerPhoneNumber.text.isNullOrEmpty()) {
                binding.etCustomerPhoneNumber.text.toString()
            } else null,
            email = if (!binding.etCustomerEmail.text.isNullOrEmpty()) {
                binding.etCustomerEmail.text.toString()
            } else null,
            address = if (!binding.etCustomerAddressLine1.text.isNullOrEmpty() && !binding.etCustomerAddressLine2.text.isNullOrEmpty()) {
                Address(
                    country = if (binding.spinnerCountry.selectedItemPosition != 0) {
                        Country.valueOf(binding.spinnerCountry.selectedItem.toString())
                    } else null,
                    addressLine1 = binding.etCustomerAddressLine1.text.toString(),
                    addressLine2 = binding.etCustomerAddressLine2.text.toString(),
                    city = binding.etCustomerCity.text.toString(),
                    province = binding.etCustomerProvince.text.toString(),
                    zipcode = binding.etCustomerZipcode.text.toString(),
                )
            } else null,
            gender = if (binding.spinnerGender.selectedItemPosition != 0) {
                Gender.valueOf(binding.spinnerGender.selectedItem.toString())
            } else null,
            birthDate = BirthDate(
                birthYear = if (!binding.etCustomerBirthYear.text.isNullOrEmpty()) binding.etCustomerBirthYear.text.toString()
                    .toInt() else null,
                birthMonth = if (!binding.etCustomerBirthMonth.text.isNullOrEmpty()) binding.etCustomerBirthMonth.text.toString()
                    .toInt() else null,
                birthDay = if (!binding.etCustomerBirthDay.text.isNullOrEmpty()) binding.etCustomerBirthDay.text.toString()
                    .toInt() else null
            )


        )
    }

    private fun installment(): Installment {
        val adapter = binding.rvFreeInstallmentPlans.adapter as FreeInstallmentPlanAdapter
        return Installment(
            monthOption = if (!binding.etFixedMonth.text.isNullOrEmpty()) {
                Installment.MonthOption.FixedMonth(
                    month = binding.etFixedMonth.text.toString().toInt()
                )
            } else if (!binding.etAvailableMonthList.text.isNullOrEmpty()) {
                Installment.MonthOption.AvailableMonths(
                    months = binding.etAvailableMonthList.text.toString().split(",")
                        .map { it.toInt() }
                )
            } else null,
            freeInstallmentPlans = if (adapter.itemCount != 0) {
                adapter.getItems()
            } else null
        )
    }
}