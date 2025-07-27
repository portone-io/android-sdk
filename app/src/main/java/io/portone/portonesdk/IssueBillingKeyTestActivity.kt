package io.portone.portonesdk

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import io.portone.portonesdk.databinding.ActivityIssueBillingKeyTestBinding
import io.portone.sdk.android.PortOne
import io.portone.sdk.android.issuebillingkey.IssueBillingKeyCallback
import io.portone.sdk.type.request.IssueBillingKeyRequest
import io.portone.sdk.type.request.IssueBillingKeyRequestUnionCard
import io.portone.sdk.type.request.IssueBillingKeyRequestUnionMobile
import io.portone.sdk.type.request.IssueBillingKeyRequestUnionEasyPay
import io.portone.sdk.type.response.IssueBillingKeyResponse
import io.portone.sdk.type.entity.Address
import io.portone.sdk.type.entity.BillingKeyMethod
import io.portone.sdk.type.entity.Country
import io.portone.sdk.type.entity.Currency
import io.portone.sdk.type.entity.Customer
import io.portone.sdk.type.entity.Gender

class IssueBillingKeyTestActivity : BaseActivity<ActivityIssueBillingKeyTestBinding>() {
    private val issueBillingKeyActivityResultLauncher =
        PortOne.registerForIssueBillingKeyActivity(this, callback = object :
            IssueBillingKeyCallback {
            override fun onSuccess(response: IssueBillingKeyResponse) {
                AlertDialog.Builder(this@IssueBillingKeyTestActivity)
                    .setTitle("빌링키 발급 성공")
                    .setMessage(response.toString())
                    .show()
            }

            override fun onFail(response: IssueBillingKeyResponse) {
                AlertDialog.Builder(this@IssueBillingKeyTestActivity)
                    .setTitle("빌링키 발급 실패")
                    .setMessage(response.toString())
                    .show()
            }

        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSpinners()
        binding.btnIssueBillingKey.setOnClickListener {
            try {
                PortOne.requestIssueBillingKey(
                    this,
                    request = IssueBillingKeyRequest(
                        storeId = binding.etStoreId.text.toString(),
                        issueId = binding.etIssueId.text.toString(),
                        issueName = binding.etIssueName.text.toString(),
                        channelKey = binding.etChannelKey.text.toString(),
                        displayAmount = binding.etDisplayAmount.text.toString().toLong(),
                        currency = Currency.valueOf(binding.etCurrency.text.toString()),
                        customer = customer(),
                        billingKeyMethod = when (val billingKeyMethod = binding.spinnerBillingKeyMethod.selectedItem.toString()) {
                            "CARD" -> BillingKeyMethod.CARD
                            "MOBILE" -> BillingKeyMethod.MOBILE
                            "EASY_PAY" -> BillingKeyMethod.EASY_PAY
                            "PAYPAL" -> BillingKeyMethod.PAYPAL
                            else -> {
                                val errorText = "invalid BillingKeyMethod ${billingKeyMethod}!"
                                Toast.makeText(this, errorText, Toast.LENGTH_SHORT).show()
                                throw Exception(errorText)
                            }
                        },
                        bypass = null, // TODO
                        windowType = null,
                        noticeUrls = null,
                        appScheme = null,
                        locale = null,
                        customData = null,
                        offerPeriod = null,
                        popup = null,
                        iframe = null,
                        productType = null,
                        card = when (binding.spinnerBillingKeyMethod.selectedItem.toString()) {
                            "CARD" -> IssueBillingKeyRequestUnionCard(
                                cardCompany = null
                            )
                            else -> null
                        },
                        mobile = when (binding.spinnerBillingKeyMethod.selectedItem.toString()) {
                            "MOBILE" -> IssueBillingKeyRequestUnionMobile(
                                carrier = null,
                                avaliableCarriers = null
                            )
                            else -> null
                        },
                        easyPay = when (binding.spinnerBillingKeyMethod.selectedItem.toString()) {
                            "EASY_PAY" -> IssueBillingKeyRequestUnionEasyPay(
                                availableCards = null,
                                easyPayProvider = null,
                                availablePayMethods = null
                            )
                            else -> null
                        },
                        paypal = null
                    ),
                    resultLauncher = issueBillingKeyActivityResultLauncher
                )
            } catch (e: Exception) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
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
    private fun setSpinners() {
        val billingKeyMethodSpinner = binding.spinnerBillingKeyMethod
        ArrayAdapter.createFromResource(
            this,
            R.array.billing_key_methods_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            billingKeyMethodSpinner.adapter = adapter
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
    override fun setViewBinding(inflater: LayoutInflater): ActivityIssueBillingKeyTestBinding {
        return ActivityIssueBillingKeyTestBinding.inflate(inflater)
    }
}