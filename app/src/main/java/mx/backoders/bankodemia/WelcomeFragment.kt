package mx.backoders.bankodemia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import mx.backoders.bankodemia.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private var _binding : FragmentWelcomeBinding? = null
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
            findNavController().navigate(R.id.action_welcome_to_singUpFragment2)
        }
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_welcome_to_login)
        }
//        setupVisibilityComponents()
    }

}