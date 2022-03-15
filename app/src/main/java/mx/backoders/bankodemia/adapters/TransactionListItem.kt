package mx.backoders.bankodemia.adapters

import mx.backoders.bankodemia.common.model.Transactions.Transaction

sealed class TransactionListItem {
    class DateItem(val date: String) : TransactionListItem()
    class TransactionItem(val transaction: Transaction) : TransactionListItem()
}
