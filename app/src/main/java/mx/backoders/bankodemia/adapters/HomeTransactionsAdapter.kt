package mx.backoders.bankodemia.adapters

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.utils.currencyParser
import mx.backoders.bankodemia.common.utils.timeParser
import mx.backoders.bankodemia.ui.home.viewmodel.HomeViewModel
import java.text.NumberFormat
import java.time.Month
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

private const val DATE = 0
private const val TRANSACTION_INFO = 1

private const val NOT_INCOME = "-"
private const val IS_INCOME = "+"

@RequiresApi(Build.VERSION_CODES.O)
class HomeTransactionsAdapter(val context: Context, private val items: ArrayList<TransactionListItem>) :
    RecyclerView.Adapter<HomeTransactionsAdapter.TransactionViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            DATE -> DateViewHolder(
                inflater.inflate(
                    R.layout.layout_home_recycler_date,
                    parent,
                    false
                )
            )
            else -> TransactionItemViewHolder(
                inflater.inflate(
                    R.layout.layout_home_recycler_transaction,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.setInfo(items[position])
        holder.isEven(context, position % 2 == 0)
        when(holder){
            is TransactionItemViewHolder -> holder.onClick(items[position])
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is TransactionListItem.DateItem -> DATE
            is TransactionListItem.TransactionItem -> TRANSACTION_INFO
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

    class DateViewHolder(view: View) : TransactionViewHolder(view) {
        val dateText = view.findViewById<TextView>(R.id.date_recycler)

        override fun setInfo(item: TransactionListItem) {
            val date = item as TransactionListItem.DateItem
            dateText.text = date.date
        }
    }

    class TransactionItemViewHolder(view: View) : TransactionViewHolder(view) {
        val conceptText = view.findViewById<TextView>(R.id.transaction_item_concept)
        val hourText = view.findViewById<TextView>(R.id.transaction_item_hour)
        val amountText = view.findViewById<TextView>(R.id.transaction_item_price)
        val income = view.findViewById<TextView>(R.id.transaction_item_is_income)
        val transactionContainer: ConstraintLayout = view.findViewById(R.id.constraint_recycler_transaction)


        override fun setInfo(item: TransactionListItem) {
            val transItem = (item as TransactionListItem.TransactionItem)
            conceptText.text = transItem.transaction.concept
            hourText.text = timeParser(transItem.transaction.createdAt)
            amountText.text = currencyParser(transItem.transaction.amount)
            income.text = if (item.transaction.isIncome) IS_INCOME else NOT_INCOME
        }

        fun onClick(item: TransactionListItem){
            val transactionID = (item as TransactionListItem.TransactionItem).transaction._id
            val bundle = Bundle()
            bundle.putString("transactionID", transactionID)
            transactionContainer.setOnClickListener{
                view.findNavController().navigate(R.id.action_nav_home_to_transactionDetailsFragment, bundle)
            }
        }
    }

}