package io.portone.portonesdk

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import io.portone.portonesdk.adapter.CardCompanyAdapter
import io.portone.portonesdk.databinding.ActivityIdentityVerificationTestBinding
import io.portone.sdk.android.PortOne
import io.portone.sdk.android.identityverification.IdentityVerificationCallback
import io.portone.sdk.android.identityverification.IdentityVerificationRequest
import io.portone.sdk.android.identityverification.IdentityVerificationResponse
import io.portone.sdk.android.type.Address
import io.portone.sdk.android.type.Amount
import io.portone.sdk.android.type.BirthDate
import io.portone.sdk.android.type.CardCompany
import io.portone.sdk.android.type.Country
import io.portone.sdk.android.type.Currency
import io.portone.sdk.android.type.Customer
import io.portone.sdk.android.type.Gender
import io.portone.sdk.android.type.PaymentMethod
import kotlinx.serialization.json.Json

class IdentityVerificationTestActivity : BaseActivity<ActivityIdentityVerificationTestBinding>() {
    private val identityVerificationActivityResultLauncher =
        PortOne.registerForIdentityVerificationActivity(this, callback = object :
            IdentityVerificationCallback {
            override fun onSuccess(response: IdentityVerificationResponse.Success) {
                AlertDialog.Builder(this@IdentityVerificationTestActivity)
                    .setTitle("본인인증 성공")
                    .setMessage(response.toString())
                    .show()
            }

            override fun onFail(response: IdentityVerificationResponse.Fail) {
                AlertDialog.Builder(this@IdentityVerificationTestActivity)
                    .setTitle("본인인증 실패")
                    .setMessage(response.toString())
                    .show()
            }

        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSpinners()
        binding.btnIdentityVerification.setOnClickListener {
            try {
                PortOne.requestIdentityVerification(
                    this,
                    request = IdentityVerificationRequest(
                        storeId = binding.etStoreId.text.toString(),
                        identityVerificationId = binding.etIdentityVerificationId.text.toString(),
                        channelKey = binding.etChannelKey.text.toString(),
                        customer = customer(),
                        bypass = if (!binding.etBypass.text.isNullOrEmpty()) binding.etBypass.text.toString() else null
                    ),
                    resultLauncher = identityVerificationActivityResultLauncher
                )
            } catch (e: Exception) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun setViewBinding(inflater: LayoutInflater): ActivityIdentityVerificationTestBinding {
        return ActivityIdentityVerificationTestBinding.inflate(inflater)
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
}