package mx.backoders.bankodemia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import mx.backoders.bankodemia.common.utils.checkForInternet
import mx.backoders.bankodemia.databinding.FragmentWelcomeBinding
import mx.backoders.bankodemia.common.utils.showSnack

class welcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUI()
//        initComponents()
//        loginObservers()
    }

    private fun initializeUI() {

        binding.btnNewAccount.setOnClickListener {
            if (!checkForInternet(requireActivity().getApplicationContext())) {
                showSnack(binding.root, getString(R.string.error_no_internet))
            } else {
                findNavController().navigate(R.id.action_welcomeFragment_to_signupFragment)
            }
        }
        binding.btnLogin.setOnClickListener {
            if (!checkForInternet(requireActivity().getApplicationContext())) {
                showSnack(binding.root, getString(R.string.error_no_internet))
            } else {
                findNavController().navigate(R.id.action_welcomeFragment_to_login3)
            }
        }
    }

}