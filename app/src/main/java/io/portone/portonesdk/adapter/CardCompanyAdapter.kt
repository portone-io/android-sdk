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
import io.portone.portonesdk.databinding.ItemSpinnerBinding
import io.portone.sdk.android.type.CardCompany

class CardCompanyAdapter(
    private val cardCompanies: MutableList<CardCompany> = mutableListOf()
) : RecyclerView.Adapter<CardCompanyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardCompanyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSpinnerBinding.inflate(inflater, parent, false)
        return CardCompanyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardCompanyViewHolder, position: Int) {
        val deleteClickListener = {
            cardCompanies.removeAt(position)
            notifyDataSetChanged()
        }
        val cardCompanyChangedListener = { card: CardCompany ->
            cardCompanies[position] = card
        }
        holder.bind(cardCompanies[position], cardCompanyChangedListener, deleteClickListener)

    }

    fun getItems() = cardCompanies
    fun addItems(cardCompany: CardCompany) {
        cardCompanies.add(cardCompany)
        notifyItemInserted(itemCount)
    }


    override fun getItemCount(): Int = cardCompanies.size
}

class CardCompanyViewHolder(
    private val binding: ItemSpinnerBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        cardCompany: CardCompany,
        cardCompanyChangeListener: (CardCompany) -> Unit,
        deleteClickListener: () -> Unit
    ) {
        val cardCompanySpinner = binding.spinner
        ArrayAdapter.createFromResource(
            binding.root.context,
            R.array.card_company_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            cardCompanySpinner.adapter = adapter
        }
        binding.spinner.setSelection(getItemIndex(binding.spinner, cardCompany.toString()))
        binding.spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (binding.spinner.selectedItem.toString() != "미선택") {
                    cardCompanyChangeListener.invoke(CardCompany.valueOf(binding.spinner.selectedItem.toString()))
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