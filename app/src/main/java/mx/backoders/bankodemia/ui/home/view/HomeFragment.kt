package mx.backoders.bankodemia.ui.home.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.adapters.HomeTransactionsAdapter
import mx.backoders.bankodemia.common.utils.PaymentType
import mx.backoders.bankodemia.common.utils.checkForInternet
import mx.backoders.bankodemia.common.utils.currencyParser
import mx.backoders.bankodemia.common.utils.showSnack
import mx.backoders.bankodemia.databinding.FragmentHomeBinding
import mx.backoders.bankodemia.ui.home.viewmodel.HomeViewModel

@RequiresApi(Build.VERSION_CODES.O)
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var myFullName = ""
    private var myID = ""

    private val homeViewModel: HomeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!checkForInternet(requireActivity().applicationContext)) {
            showSnack(
                binding.root,
                getString(R.string.error_no_internet),
                Snackbar.LENGTH_INDEFINITE
            )
        } else {
            homeViewModel.getUserProfile()
        }
        userProfileObserver()
        initializeUI()
    }

    override fun onResume() {
        super.onResume()
        if (!checkForInternet(requireActivity().applicationContext)) {
            showSnack(binding.root, getString(R.string.error_no_internet), Snackbar.LENGTH_INDEFINITE)
        } else {
            homeViewModel.getUserProfile()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun userProfileObserver() {
        homeViewModel.userProfileResponse.observe(viewLifecycleOwner) { profile ->
            binding.availableMoneyTextView.text =
                profile.data.balance?.let { balance -> currencyParser(balance) }
            myFullName = "${profile.data.user.name} ${profile.data.user.lastName}"
            myID = profile.data.user.id
            recyclerSetup()
        }

        homeViewModel.isLoading.observe(viewLifecycleOwner, ::loadingIndicator)
    }

    private fun loadingIndicator(visibility: Boolean) {
        binding.homeLoadingContainer.isVisible = visibility
        binding.homeContainer.isVisible = !visibility
    }

    private fun recyclerSetup() {
        with(binding.transactionsRecyclerView) {
            adapter =
                HomeTransactionsAdapter(requireContext(), homeViewModel.transactionItemsForRecycler)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun initializeUI() {
        with(binding) {
            sendButton.setOnClickListener {
                findNavController().navigate(R.id.action_nav_home_to_contactListFragment)
            }

            getButton.setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable("paymentType", PaymentType.DEPOSIT)
                bundle.putString("contactID", myID)
                bundle.putString("contactFullName", myFullName)
                findNavController().navigate(
                    R.id.action_nav_home_to_makeTransactionFragment,
                    bundle
                )
            }
        }

        setupVisibilityComponents()
    }

    private fun setupVisibilityComponents() {
        with(homeViewModel) {
            bottomNavIsVisible(true)
            topToolbarIsVisible(true)
        }
    }
}