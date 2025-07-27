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
import io.portone.sdk.type.request.IssueBillingKeyAndPayRequest
import io.portone.sdk.type.request.IssueBillingKeyAndPayRequestUnionMobile
import io.portone.sdk.type.response.IssueBillingKeyAndPayResponse
import io.portone.sdk.type.entity.Address
import io.portone.sdk.type.entity.Bank
import io.portone.sdk.type.entity.BillingKeyAndPayMethod
import io.portone.sdk.type.entity.CardCompany
import io.portone.sdk.type.entity.Carrier
import io.portone.sdk.type.entity.CashReceiptType
import io.portone.sdk.type.entity.Country
import io.portone.sdk.type.entity.Currency
import io.portone.sdk.type.entity.Customer
import io.portone.sdk.type.entity.EasyPayProvider
import io.portone.sdk.type.entity.Gender
import io.portone.sdk.type.entity.GiftCertificateType

import kotlinx.serialization.json.Json
import java.time.Instant
import java.util.UUID

class IssueBillingKeyAndPayTestActivity : BaseActivity<ActivityIssueBillingKeyAndPayTestBinding>() {
    private val issueBillingKeyAndPayActivityResultLauncher =
        PortOne.registerForIssueBillingKeyAndPay(this, callback = object :
            IssueBillingKeyAndPayCallback {
            override fun onSuccess(response: IssueBillingKeyAndPayResponse) {
                AlertDialog.Builder(this@IssueBillingKeyAndPayTestActivity)
                    .setTitle("빌링키 발급 및 결제 성공")
                    .setMessage(response.toString())
                    .show()
            }

            override fun onFail(response: IssueBillingKeyAndPayResponse) {
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
                totalAmount = binding.etTotalAmount.text.toString().toLong(),
                currency = Currency.valueOf(binding.etCurrency.text.toString()),
                customer = customer(),
                billingKeyAndPayMethod = BillingKeyAndPayMethod.MOBILE,
                taxFreeAmount = null,
                vatAmount = null,
                products = null,
                windowType = null,
                noticeUrls = null,
                locale = null,
                isCulturalExpense = null,
                customData = null,
                offerPeriod = null,
                appScheme = null,
                productType = null,
                storeDetails = null,
                country = null,
                bypass = null,
                popup = null,
                iframe = null,
                mobile = IssueBillingKeyAndPayRequestUnionMobile(
                    carrier = Carrier.valueOf(binding.spinnerCarrier.selectedItem.toString()),
                    avaliableCarriers = null
                )
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
            customerId = if (!binding.etCustomerId.text.isNullOrEmpty()) {
                binding.etCustomerId.text.toString()
            } else null,
            fullName = if (!binding.etCustomerFullName.text.isNullOrEmpty()) {
                binding.etCustomerFullName.text.toString()
            } else null,
            firstName = if (!binding.etCustomerFirstName.text.isNullOrEmpty()) {
                binding.tvCustomerFirstName.text.toString()
            } else null,
            lastName = if (!binding.etCustomerLastName.text.isNullOrEmpty()) {
                binding.tvCustomerLastName.text.toString()
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
                )
            } else null,
            zipcode = if (!binding.etCustomerZipcode.text.isNullOrEmpty()) binding.etCustomerZipcode.text.toString() else null,
            gender = if (binding.spinnerGender.selectedItemPosition != 0) {
                Gender.valueOf(binding.spinnerGender.selectedItem.toString())
            } else null,
            birthYear = if (!binding.etCustomerBirthYear.text.isNullOrEmpty()) binding.etCustomerBirthYear.text.toString() else null,
            birthMonth = if (!binding.etCustomerBirthMonth.text.isNullOrEmpty()) binding.etCustomerBirthMonth.text.toString() else null,
            birthDay = if (!binding.etCustomerBirthDay.text.isNullOrEmpty()) binding.etCustomerBirthDay.text.toString() else null,
            firstNameKana = null,
            lastNameKana = null


        )
    }
}