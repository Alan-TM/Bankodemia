package mx.backoders.bankodemia.ui.home.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import mx.backoders.bankodemia.adapters.HomeTransactionsAdapter
import mx.backoders.bankodemia.adapters.TransactionListItem
import mx.backoders.bankodemia.common.model.User.UserFullProfileResponse
import mx.backoders.bankodemia.common.utils.currencyParser
import mx.backoders.bankodemia.databinding.FragmentHomeBinding
import mx.backoders.bankodemia.ui.home.viewmodel.HomeViewModel
import java.text.DecimalFormat

@RequiresApi(Build.VERSION_CODES.O)
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userProfileObserver()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun userProfileObserver(){
        homeViewModel.userProfileResponse.observe(viewLifecycleOwner){ profile ->
            binding.availableMoneyTextView.text = profile.data.balance?.let { it -> currencyParser(it) }
            recyclerSetup()
        }


    }

    private fun recyclerSetup() {
        with(binding.transactionsRecyclerView){
            adapter = HomeTransactionsAdapter(requireContext(), homeViewModel.transactionItemsForRecycler)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}