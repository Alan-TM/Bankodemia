package mx.backoders.bankodemia.adapters

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.utils.currencyParser
import mx.backoders.bankodemia.common.utils.timeParser
import mx.backoders.bankodemia.databinding.LayoutHomeRecyclerDateBinding
import mx.backoders.bankodemia.databinding.LayoutHomeRecyclerTransactionBinding

private const val DATE = 0
private const val TRANSACTION_INFO = 1

private const val NOT_INCOME = "-"
private const val IS_INCOME = "+"

@RequiresApi(Build.VERSION_CODES.O)
class HomeTransactionsAdapter(
    val context: Context,
    private val items: ArrayList<TransactionListItem>
) :
    RecyclerView.Adapter<HomeTransactionsAdapter.TransactionViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            DATE -> DateViewHolder(
                binding = LayoutHomeRecyclerDateBinding.inflate(inflater, parent, false)
            )
            else -> TransactionItemViewHolder(
                binding = LayoutHomeRecyclerTransactionBinding.inflate(inflater, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.setInfo(items[position])
        holder.isEven(context, position % 2 == 0)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is TransactionListItem.DateItem -> DATE
            else -> TRANSACTION_INFO
        }
    }

    abstract class TransactionViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        abstract fun setInfo(item: TransactionListItem)

        fun isEven(context: Context, isEven: Boolean) {
            view.background =
                if (isEven) ColorDrawable(context.getColor(R.color.bg_recycler_transaction)) else ColorDrawable(
                    context.getColor(R.color.white)
                )
        }
    }

    class DateViewHolder(private val binding: LayoutHomeRecyclerDateBinding) :
        TransactionViewHolder(binding.root) {
        override fun setInfo(item: TransactionListItem) {
            val date = item as TransactionListItem.DateItem
            binding.dateRecycler.text = date.date
        }
    }

    class TransactionItemViewHolder(private val binding: LayoutHomeRecyclerTransactionBinding) :
        TransactionViewHolder(binding.root) {


        override fun setInfo(item: TransactionListItem) {
            val transItem = (item as TransactionListItem.TransactionItem)

            with(binding) {
                transactionItemConcept.text = transItem.transaction.concept
                transactionItemHour.text = timeParser(transItem.transaction.createdAt)
                transactionItemPrice.text = currencyParser(transItem.transaction.amount)
                transactionItemIsIncome.text = if (transItem.transaction.isIncome) IS_INCOME else NOT_INCOME

                constraintRecyclerTransaction.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("transactionID", transItem.transaction._id)
                    view.findNavController()
                        .navigate(R.id.action_nav_home_to_transactionDetailsFragment, bundle)
                }
            }
        }
    }

}