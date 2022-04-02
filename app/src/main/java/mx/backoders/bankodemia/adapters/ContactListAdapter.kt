package mx.backoders.bankodemia.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.model.Contacts.YourContact
import mx.backoders.bankodemia.common.utils.PaymentType
import mx.backoders.bankodemia.databinding.ItemSendListBinding

class ContactListAdapter(private val items: ArrayList<YourContact>) :
    RecyclerView.Adapter<ContactListAdapter.ContactListViewHolder>() {
    class ContactListViewHolder(private val binding: ItemSendListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setInfo(item: YourContact) {
            with(binding) {
                contactListName.text = item.shortName
                contactListId.text = item.user.id.substring(0..15)

                contactListInfo.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putSerializable("paymentType", PaymentType.PAYMENT)
                    bundle.putString("contactID", item.user.id)
                    bundle.putString("contactFullName", "${item.user.name} ${item.user.lastName}")
                    binding.root.findNavController()
                        .navigate(R.id.action_contactListFragment_to_makeTransactionFragment, bundle)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListViewHolder {
        val binding =
            ItemSendListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactListViewHolder, position: Int) {
        holder.setInfo(items[position])
    }

    override fun getItemCount(): Int = items.size
}