package mx.backoders.bankodemia.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.backoders.bankodemia.common.model.Contacts.YourContact
import mx.backoders.bankodemia.databinding.ItemSendListBinding

class ContactListAdapter(private val items: ArrayList<YourContact>) : RecyclerView.Adapter<ContactListAdapter.ContactListViewHolder>() {
    class ContactListViewHolder(private val binding: ItemSendListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setInfo(item: YourContact) {
            with(binding){
                contactListName.text = item.shortName
                contactListId.text = item.id
                //TODO check this with mentor
                contactListBank.text = "PLACEHOLDER"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListViewHolder {
        val binding = ItemSendListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactListViewHolder, position: Int) {
        holder.setInfo(items[position])
    }

    override fun getItemCount(): Int = items.size
}