package io.portone.portonesdk.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView
import io.portone.portonesdk.R
import io.portone.portonesdk.databinding.ItemFreeInstallmentPlanBinding
import io.portone.sdk.android.type.entity.CardCompany
import io.portone.sdk.android.type.entity.FreeInstallmentPlan

class FreeInstallmentPlanAdapter(
    private val freeInstallmentPlans: MutableList<FreeInstallmentPlan> = mutableListOf()
) : RecyclerView.Adapter<FreeInstallmentPlanViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FreeInstallmentPlanViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFreeInstallmentPlanBinding.inflate(inflater, parent, false)
        return FreeInstallmentPlanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FreeInstallmentPlanViewHolder, position: Int) {
        val deleteClickListener = {
            freeInstallmentPlans.removeAt(position)
            notifyDataSetChanged()
        }
        val freeInstallmentPlanChangedListener = { freeInstallmentPlan: FreeInstallmentPlan ->
            freeInstallmentPlans[position] = freeInstallmentPlan
        }
        holder.bind(freeInstallmentPlans[position],freeInstallmentPlanChangedListener, deleteClickListener)

    }

    fun getItems() = freeInstallmentPlans
    fun addItems(freeInstallmentPlan: FreeInstallmentPlan) {
        freeInstallmentPlans.add(freeInstallmentPlan)
        notifyItemInserted(itemCount)
    }


    override fun getItemCount(): Int = freeInstallmentPlans.size
}

class FreeInstallmentPlanViewHolder(
    private val binding: ItemFreeInstallmentPlanBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        freeInstallmentPlan: FreeInstallmentPlan,
        freeInstallmentPlanChangeListener: (FreeInstallmentPlan) -> Unit,
        deleteClickListener: () -> Unit
    ) {
        val cardCompanySpinner = binding.spinnerCardCompany
        ArrayAdapter.createFromResource(
            binding.root.context,
            R.array.card_company_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            cardCompanySpinner.adapter = adapter
        }
        binding.spinnerCardCompany.setSelection(getItemIndex(binding.spinnerCardCompany, freeInstallmentPlan.cardCompany.toString()))
        binding.spinnerCardCompany.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (binding.spinnerCardCompany.selectedItem.toString() != "미선택") {
                    freeInstallmentPlanChangeListener.invoke(
                        FreeInstallmentPlan(
                            cardCompany = CardCompany.valueOf(binding.spinnerCardCompany.selectedItem.toString()),
                            months = binding.etMonths.text.toString().split(",").map { it.toLong() }
                        )
                    )
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
        binding.btnDelete.setOnClickListener {
            deleteClickListener.invoke()
        }
    }

    private fun getItemIndex(spinner: Spinner, item: String): Int {
        for (i in 0..spinner.count) {
            if (spinner.getItemAtPosition(i).toString() == item)
                return i
        }
        return 0
    }
}