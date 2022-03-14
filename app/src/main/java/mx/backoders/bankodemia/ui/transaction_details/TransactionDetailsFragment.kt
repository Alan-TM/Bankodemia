package mx.backoders.bankodemia.ui.transaction_details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.backoders.bankodemia.R

class TransactionDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = TransactionDetailsFragment()
    }

    private lateinit var viewModel: TransactionDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.transaction_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TransactionDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}