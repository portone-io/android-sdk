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
import io.portone.sdk.android.type.Carrier

class CarrierAdapter(private val carriers: MutableList<Carrier> = mutableListOf()) :
    RecyclerView.Adapter<CarrierViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarrierViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSpinnerBinding.inflate(inflater, parent, false)
        return CarrierViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarrierViewHolder, position: Int) {
        val deleteClickListener = {
            carriers.removeAt(position)
            notifyDataSetChanged()
        }
        val carrierChangedListener = { carrier: Carrier ->
            carriers[position] = carrier
        }
        holder.bind(carriers[position], carrierChangedListener, deleteClickListener)

    }

    fun getItems() = carriers
    fun addItems(carrier: Carrier) {
        carriers.add(carrier)
        notifyItemInserted(itemCount)
    }


    override fun getItemCount(): Int = carriers.size
}

class CarrierViewHolder(
    private val binding: ItemSpinnerBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(carrier: Carrier, carrierChangeListener: (Carrier) -> Unit, deleteClickListener: () -> Unit) {
        val carrierSpinner = binding.spinner
        ArrayAdapter.createFromResource(
            binding.root.context,
            R.array.carrier_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            carrierSpinner.adapter = adapter
        }
        binding.spinner.setSelection(getItemIndex(binding.spinner, carrier.toString()))
        binding.spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (binding.spinner.selectedItem.toString() != "미선택") {
                    carrierChangeListener.invoke(Carrier.valueOf(binding.spinner.selectedItem.toString()))
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