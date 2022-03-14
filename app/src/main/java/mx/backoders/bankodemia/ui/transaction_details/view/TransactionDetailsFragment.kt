package mx.backoders.bankodemia.ui.transaction_details.view

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.ui.home.viewmodel.HomeViewModel
import mx.backoders.bankodemia.ui.transaction_details.viewmodel.TransactionDetailsViewModel

class TransactionDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = TransactionDetailsFragment()
    }

    private lateinit var viewModel: TransactionDetailsViewModel
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.transaction_details_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        homeViewModel.bottomNavIsVisible(false)

        viewModel = ViewModelProvider(this).get(TransactionDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}