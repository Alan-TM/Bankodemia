package mx.backoders.bankodemia.ui.singup.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.utils.checkForInternet
import mx.backoders.bankodemia.common.utils.showSnack
import mx.backoders.bankodemia.databinding.FragmentIntroIdentityBinding

class IntroIdentity : Fragment() {

    private var _binding: FragmentIntroIdentityBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIntroIdentityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.introductionidentityAcceptButton.setOnClickListener {
            if (!checkForInternet(requireActivity().getApplicationContext())) {
                showSnack(binding.root, getString(R.string.error_no_internet), Snackbar.LENGTH_INDEFINITE)
            } else {
                findNavController().navigate(R.id.action_intro_Identity_to_verify_Identity)
            }
        }
        binding.returnLogin.setOnClickListener{
            it.findNavController().navigateUp()
        }
    }
}