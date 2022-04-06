package mx.backoders.bankodemia.ui.services.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import mx.backoders.bankodemia.adapters.ServicesAdapter
import mx.backoders.bankodemia.common.model.services.Services
import mx.backoders.bankodemia.databinding.FragmentServicesBinding
import mx.backoders.bankodemia.ui.home.viewmodel.HomeViewModel
import mx.backoders.bankodemia.ui.services.viewmodel.ServicesViewModel

class ServicesFragment : Fragment() {

    private var _binding: FragmentServicesBinding? = null

    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by activityViewModels()

    private val servicesViewModel: ServicesViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentServicesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        servicesViewModel.fetchServices()

        initializeObservers()
    }

    private fun initializeObservers() {
        servicesViewModel.servicesList.observe(viewLifecycleOwner, ::setupRecycler)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecycler(services: ArrayList<Services>){
        binding.servicesRecyclerView.apply{
            adapter = ServicesAdapter(services)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}