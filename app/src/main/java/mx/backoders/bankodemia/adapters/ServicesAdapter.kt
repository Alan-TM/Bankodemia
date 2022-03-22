package mx.backoders.bankodemia.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import mx.backoders.bankodemia.common.model.Services.Services
import mx.backoders.bankodemia.databinding.ItemServicesBinding

class ServicesAdapter(private val items: ArrayList<Services>) : RecyclerView.Adapter<ServicesAdapter.ServicesViewHolder>() {
    class ServicesViewHolder(private val binding: ItemServicesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setInfo(item: Services){
            binding.serviceTitleTextView.text = item.title
            binding.serviceDescriptionTextView.text = item.description

            binding.servicesCardView.setOnClickListener {
                Toast.makeText(binding.root.context, item.title, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder {
        val binding = ItemServicesBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ServicesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServicesViewHolder, position: Int) {
        holder.setInfo(items[position])
    }

    override fun getItemCount(): Int = items.size
}