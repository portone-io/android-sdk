package io.portone.portonesdk

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import io.portone.portonesdk.adapter.CardCompanyAdapter
import io.portone.portonesdk.adapter.CarrierAdapter
import io.portone.portonesdk.databinding.ActivityIssueBillingKeyAndPayTestBinding
import io.portone.sdk.android.PortOne
import io.portone.sdk.android.issuebillingkeyandpay.IssueBillingKeyAndPayCallback
import io.portone.sdk.android.issuebillingkeyandpay.IssueBillingKeyAndPayRequest
import io.portone.sdk.android.issuebillingkeyandpay.IssueBillingKeyAndPayResponse
import io.portone.sdk.android.type.Address
import io.portone.sdk.android.type.Amount
import io.portone.sdk.android.type.Bank
import io.portone.sdk.android.type.BillingKeyAndPayMethod
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
import io.portone.sdk.android.type.PaymentMethod

import kotlinx.serialization.json.Json
import java.time.Instant
import java.util.UUID

class IssueBillingKeyAndPayTestActivity : BaseActivity<ActivityIssueBillingKeyAndPayTestBinding>() {
    private val issueBillingKeyAndPayActivityResultLauncher =
        PortOne.registerForIssueBillingKeyAndPay(this, callback = object :
            IssueBillingKeyAndPayCallback {
            override fun onSuccess(response: IssueBillingKeyAndPayResponse.Success) {
                AlertDialog.Builder(this@IssueBillingKeyAndPayTestActivity)
                    .setTitle("빌링키 발급 및 결제 성공")
                    .setMessage(response.toString())
                    .show()
            }

            override fun onFail(response: IssueBillingKeyAndPayResponse.Fail) {
                AlertDialog.Builder(this@IssueBillingKeyAndPayTestActivity)
                    .setTitle("빌링키 발급 및 결제 실패")
                    .setMessage(response.toString())
                    .show()
            }

        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSpinners()

        binding.btnIssueBillingKeyAndPay.setOnClickListener {
            startIssueBillingKeyAndPayRequestActivity()
        }
        binding.etPaymentId.setText(UUID.randomUUID().toString())

        setAdapters()
    }

    private fun startIssueBillingKeyAndPayRequestActivity() {
        try {
            PortOne.requestIssueBillingKeyAndPay(
                this,
                request = getIssueBillingKeyAndPayRequestByPayMethod(),
                resultLauncher = issueBillingKeyAndPayActivityResultLauncher
            )
        } catch (e: Exception) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun setAdapters() {
        val carrierAdapter = CarrierAdapter()
        binding.rvAvailableCarriers.layoutManager = LinearLayoutManager(this)
        binding.rvAvailableCarriers.adapter = carrierAdapter
        binding.btnAddCarriers.setOnClickListener {
            carrierAdapter.addItems(Carrier.SKT)
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
        val carrierSpinner = binding.spinnerCarrier
        ArrayAdapter.createFromResource(
            this,
            R.array.carrier_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            carrierSpinner.adapter = adapter
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
    }

    override fun setViewBinding(inflater: LayoutInflater): ActivityIssueBillingKeyAndPayTestBinding {
        return ActivityIssueBillingKeyAndPayTestBinding.inflate(inflater)
    }

    private fun getIssueBillingKeyAndPayRequestByPayMethod(): IssueBillingKeyAndPayRequest {
        return when (val payMethod = binding.spinnerPayMethod.selectedItem.toString()) {
            "MOBILE" -> IssueBillingKeyAndPayRequest(
                storeId = binding.etStoreId.text.toString(),
                paymentId = binding.etPaymentId.text.toString(),
                orderName = binding.etOrderName.text.toString(),
                channelKey = binding.etChannelKey.text.toString(),
                amount = Amount(
                    total = binding.etTotalAmount.text.toString().toLong(),
                    currency = Currency.valueOf(binding.etCurrency.text.toString()),
                ),
                customer = customer(),
                method = BillingKeyAndPayMethod.Mobile(
                    carrier = Carrier.valueOf(binding.spinnerCarrier.selectedItem.toString()),
                    availableCarriers = null
                ),
                bypass = if (!binding.etBypass.text.isNullOrEmpty()) binding.etBypass.text.toString() else null
            )

            else -> {
                val errorText = "invalid PayMethod ${payMethod}!"
                Toast.makeText(this, errorText, Toast.LENGTH_SHORT).show()
                throw Exception(errorText)
            }
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
}