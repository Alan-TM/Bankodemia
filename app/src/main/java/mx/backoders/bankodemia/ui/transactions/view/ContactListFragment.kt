package mx.backoders.bankodemia.ui.transactions.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.adapters.ContactListAdapter
import mx.backoders.bankodemia.common.model.Contacts.ListMyContactsResponse
import mx.backoders.bankodemia.databinding.FragmentSendListUsersBinding
import mx.backoders.bankodemia.databinding.FragmentServicesBinding
import mx.backoders.bankodemia.ui.home.viewmodel.HomeViewModel
import mx.backoders.bankodemia.ui.transactions.viewmodel.ContactListViewModel

class ContactListFragment : Fragment() {
    private var _binding: FragmentSendListUsersBinding? = null

    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by activityViewModels()
    private val contactsListViewModel: ContactListViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSendListUsersBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUI()
        initializeObservers()
    }

    override fun onResume() {
        super.onResume()
        contactsListViewModel.getContactList()
    }

    private fun initializeObservers() {
        contactsListViewModel.contactListResponse.observe(viewLifecycleOwner, ::setupRecycler)
    }

    private fun setupRecycler(listMyContactsResponse: ListMyContactsResponse) {
        binding.contactListRecycler.apply {
            adapter = ContactListAdapter(listMyContactsResponse.data.contacts)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeUI(){
        binding.imageViewBackButtonFragmentSendList.setOnClickListener {
            findNavController().navigateUp()
        }

        setupVisibilityComponents()
    }

    private fun setupVisibilityComponents(){
        homeViewModel.bottomNavIsVisible(false)
        homeViewModel.topToolbarIsVisible(false)
    }
}