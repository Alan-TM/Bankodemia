package mx.backoders.bankodemia.adapters

import mx.backoders.bankodemia.common.model.transactions.Transaction

sealed class TransactionListItem {
    class DateItem(val date: String) : TransactionListItem()
    class TransactionItem(val transaction: Transaction) : TransactionListItem()
}
