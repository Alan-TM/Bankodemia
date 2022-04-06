package mx.backoders.bankodemia.ui.login.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.preferences.SharedPreferencesInstance
import mx.backoders.bankodemia.common.utils.checkForInternet
import mx.backoders.bankodemia.common.utils.showSnack
import mx.backoders.bankodemia.databinding.FragmentTokenValidBinding
import mx.backoders.bankodemia.ui.login.viewmodel.LoginViewModel
import mx.backoders.bankodemia.ui.main.HomeActivity

class TokenValidFragment : Fragment() {

    private var _binding: FragmentTokenValidBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTokenValidBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isStillValidToken()
        loginObservers()
    }

    private fun isStillValidToken() {
        if (!checkForInternet(requireActivity().applicationContext)) {
            SharedPreferencesInstance.clearAllPreferences()
            findNavController().navigate(R.id.action_tokenValidFragment_to_welcomeFragment)
        } else {
            if (SharedPreferencesInstance.exists("token"))
                loginViewModel.isStillValidToken()
            else {
                findNavController().navigate(R.id.action_tokenValidFragment_to_welcomeFragment)
            }
        }
    }

    private fun loginObservers() {
        loginViewModel.isTokenExpired.observe(viewLifecycleOwner) { isTokenExpired ->
            if (!isTokenExpired) {
                openHomeActivity()
            } else {
                findNavController().navigate(R.id.action_tokenValidFragment_to_welcomeFragment)
                showErrorMessage()
            }
        }
    }

    fun openHomeActivity() {
        val intent = Intent(activity, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun showErrorMessage() {
        showSnack(
            binding.root,
            getString(R.string.error_no_authorized),
            Snackbar.LENGTH_INDEFINITE,
            getString(R.string.accept)
        ) {
            SharedPreferencesInstance.clearAllPreferences()
        }
    }
}