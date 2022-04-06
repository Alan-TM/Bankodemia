package mx.backoders.bankodemia.ui.transactions.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.adapters.ContactListAdapter
import mx.backoders.bankodemia.common.model.contacts.ListMyContactsResponse
import mx.backoders.bankodemia.common.utils.ErrorManager
import mx.backoders.bankodemia.common.utils.checkForInternet
import mx.backoders.bankodemia.common.utils.showSnack
import mx.backoders.bankodemia.databinding.FragmentSendListUsersBinding
import mx.backoders.bankodemia.ui.home.viewmodel.HomeViewModel
import mx.backoders.bankodemia.ui.transactions.viewmodel.ContactListViewModel

class ContactListFragment : Fragment() {
    private var _binding: FragmentSendListUsersBinding? = null

    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by activityViewModels()
    private val contactsListViewModel: ContactListViewModel by viewModels()

    private lateinit var errorManager: ErrorManager

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSendListUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUI()
        initializeObservers()
    }

    override fun onResume() {
        super.onResume()
        errorManager = ErrorManager(requireView())
        if (!checkForInternet(requireActivity().applicationContext)) {
            loadingIndicator(true)
            showSnack(
                binding.root,
                getString(R.string.error_no_internet),
                Snackbar.LENGTH_INDEFINITE
            )
        } else {
            contactsListViewModel.getContactList()
        }
    }

    private fun initializeObservers() {
        with(contactsListViewModel) {
            contactListResponse.observe(viewLifecycleOwner, ::setupRecycler)

            isLoading.observe(viewLifecycleOwner, ::loadingIndicator)

            errorResponse.observe(viewLifecycleOwner){
                errorManager(it)
            }
        }
    }

    private fun setupRecycler(listMyContactsResponse: ListMyContactsResponse) {
        binding.contactListRecycler.apply {
            adapter = ContactListAdapter(listMyContactsResponse.data.contacts)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun loadingIndicator(visible: Boolean){
        binding.loadingIndicatorProgressBar.isVisible = visible
        binding.contactListRecycler.isVisible = !visible
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeUI(){
        with(binding) {
            imageViewBackButtonFragmentSendList.setOnClickListener {
                findNavController().navigateUp()
            }

            imageViewAddUsers.setOnClickListener {
                findNavController().navigate(R.id.action_contactListFragment_to_addAccountFragment2)
            }
        }

        setupVisibilityComponents()
    }

    private fun setupVisibilityComponents(){
        homeViewModel.bottomNavIsVisible(false)
        homeViewModel.topToolbarIsVisible(false)
    }
}