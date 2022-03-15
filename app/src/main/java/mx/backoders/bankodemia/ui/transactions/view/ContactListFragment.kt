package mx.backoders.bankodemia.ui.transactions.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.databinding.FragmentSendListUsersBinding
import mx.backoders.bankodemia.databinding.FragmentServicesBinding
import mx.backoders.bankodemia.ui.home.viewmodel.HomeViewModel

class ContactListFragment : Fragment() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_send_list_users)
//    }

    private var _binding: FragmentSendListUsersBinding? = null

    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by activityViewModels()



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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeUI(){
        binding.contactsBackButton.setOnClickListener {
            findNavController().navigate(R.id.action_contactListFragment_to_nav_home)
        }

        setupVisibilityComponents()
    }

    private fun setupVisibilityComponents(){
        homeViewModel.bottomNavIsVisible(false)
        homeViewModel.topToolbarIsVisible(false)
    }
}