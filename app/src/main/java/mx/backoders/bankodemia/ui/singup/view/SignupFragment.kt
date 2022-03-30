package mx.backoders.bankodemia.ui.singup.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.databinding.FragmentSignupBinding


class SignupFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentSignupBinding? = null
    private val binding get () = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signupContinueButton.setOnClickListener{
            it.findNavController().navigate(R.id.action_signupFragment_to_personalDetailsFragment)
        }
    }


    }
