package mx.backoders.bankodemia.ui.singup.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.databinding.FragmentSignUpBinding
import mx.backoders.bankodemia.databinding.FragmentWelcomeBinding

class SingUpFragment : Fragment() {

    private var _binding : FragmentSignUpBinding? = null
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
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUI()
//        initComponents()
//        loginObservers()
    }

    private fun initializeUI() {
        binding.returnLogin.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.signupContinueButton.setOnClickListener {
            findNavController().navigate(R.id.action_singUpFragment2_to_personalDetailsFragment)
        }
//        setupVisibilityComponents()
    }

}