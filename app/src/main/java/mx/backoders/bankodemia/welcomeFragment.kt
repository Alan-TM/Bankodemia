package mx.backoders.bankodemia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import mx.backoders.bankodemia.databinding.FragmentWelcomeBinding


class welcomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWelcomeBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNewAccount.setOnClickListener{
            it.findNavController().navigate(R.id.action_welcomeFragment_to_signupFragment)
            }

        binding.btnLogin.setOnClickListener{
            it.findNavController().navigate(R.id.action_welcomeFragment_to_login3)
        }
    }

}