package io.portone.portonesdk

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import io.portone.portonesdk.databinding.ActivityLoadPaymentUiTestBinding
import io.portone.sdk.android.PortOne
import io.portone.sdk.android.payment.PaymentCallback
import io.portone.sdk.type.entity.PaymentUIType
import io.portone.sdk.type.response.PaymentResponse
import io.portone.sdk.type.request.LoadPaymentUIRequest
import io.portone.sdk.type.entity.Address
import io.portone.sdk.type.entity.Country
import io.portone.sdk.type.entity.Currency
import io.portone.sdk.type.entity.Customer
import io.portone.sdk.type.entity.Gender

class LoadPaymentUITestActivity : BaseActivity<ActivityLoadPaymentUiTestBinding>() {
    private val loadPaymentUIActivityResultLauncher =
        PortOne.registerForLoadPaymentUI(this, callback = object :
            PaymentCallback {
            override fun onSuccess(response: PaymentResponse) {
                AlertDialog.Builder(this@LoadPaymentUITestActivity)
                    .setTitle("결제 성공")
                    .setMessage(response.toString())
                    .show()
            }

            override fun onFail(response: PaymentResponse) {
                AlertDialog.Builder(this@LoadPaymentUITestActivity)
                    .setTitle("결제 실패")
                    .setMessage(response.toString())
                    .show()
            }

        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSpinners()
        binding.btnLoadPaymentUi.setOnClickListener {
            PortOne.loadPaymentUI(
                this,
                request = LoadPaymentUIRequest(
                    uiType = PaymentUIType.PAYPAL_SPB,
                    storeId = binding.etStoreId.text.toString(),
                    paymentId = binding.etPaymentId.text.toString(),
                    orderName = binding.etOrderName.text.toString(),
                    channelKey = binding.etChannelKey.text.toString(),
                    totalAmount = binding.etTotalAmount.text.toString().toLong(),
                    currency = Currency.valueOf(binding.etCurrency.text.toString()),
                    customer = customer(),
                    taxFreeAmount = null,
                    vatAmount = null,
                    noticeUrls = null,
                    confirmUrl = null,
                    appScheme = null,
                    locale = null,
                    offerPeriod = null,
                    products = null,
                    isCulturalExpense = null,
                    country = null,
                    customData = null,
                    bypass = null,
                    isEscrow = null,
                    productType = null,
                    storeDetails = null,
                    shippingAddress = null,
                    promotionGroupId = null,
                    promotionIds = null
                ),
                resultLauncher = loadPaymentUIActivityResultLauncher,
            )
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

    private fun setSpinners() {
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

    override fun setViewBinding(inflater: LayoutInflater): ActivityLoadPaymentUiTestBinding {
        return ActivityLoadPaymentUiTestBinding.inflate(inflater)
    }
}