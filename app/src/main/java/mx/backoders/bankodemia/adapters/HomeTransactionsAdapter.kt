package mx.backoders.bankodemia.adapters

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.ui.home.viewmodel.HomeViewModel
import java.text.NumberFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

@RequiresApi(Build.VERSION_CODES.O)
class HomeTransactionsAdapter(val context: Context, val items: ArrayList<TransactionListItem>) : RecyclerView.Adapter<HomeTransactionsAdapter.TransactionViewHolder>() {

    private val DATE = 0
    private val TRANSACTION_INFO = 1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType){
            DATE -> DateViewHolder(inflater.inflate(R.layout.layout_home_recycler_date, parent, false))
            else -> TransactionItemViewHolder(inflater.inflate(R.layout.layout_home_recycler_transaction, parent , false))
        }
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.setInfo(items[position])
        holder.isEven(context, position % 2 == 0)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when(items[position]){
            is TransactionListItem.DateItem -> DATE
            is TransactionListItem.TransactionItem -> TRANSACTION_INFO
        }
    }

    abstract class TransactionViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        abstract fun setInfo(item: TransactionListItem)

        fun isEven(context: Context, isEven: Boolean){
            view.background = if(isEven) ColorDrawable(context.getColor(R.color.bg_recycler_transaction)) else ColorDrawable(context.getColor(R.color.white))
        }
    }

    class DateViewHolder(view: View) : TransactionViewHolder(view){
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

        val timeFormatter = DateTimeFormatter.ofPattern("h:mm a")
        val currencyFormatter = NumberFormat.getCurrencyInstance()

        override fun setInfo(item: TransactionListItem) {
            val transItem = (item as TransactionListItem.TransactionItem)
            conceptText.text = transItem.transaction.concept
            hourText.text = ZonedDateTime.parse(transItem.transaction.createdAt).format(timeFormatter)
            currencyFormatter.maximumFractionDigits = 2
            currencyFormatter.currency = Currency.getInstance("USD")
            amountText.text = currencyFormatter.format(transItem.transaction.amount)
            income.text = if(item.transaction.isIncome) "+" else "-"
        }
    }

}