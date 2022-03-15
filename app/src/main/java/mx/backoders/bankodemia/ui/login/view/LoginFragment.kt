package mx.backoders.bankodemia.ui.login.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.dto.LoginDto
import mx.backoders.bankodemia.common.preferences.SharedPreferencesInstance
import mx.backoders.bankodemia.common.utils.*
import mx.backoders.bankodemia.databinding.FragmentHomeBinding
import mx.backoders.bankodemia.databinding.FragmentLoginBinding
import mx.backoders.bankodemia.ui.login.viewmodel.LoginViewModel

class LoginFragment : Fragment() {
//
//    lateinit var shared: SharedPreferencesInstance
//    private var _binding: FragmentLoginBinding? = null
//    private val binding get() = _binding!!
//    private lateinit var tietEmail: TextInputEditText
//    private lateinit var tilEmail: TextInputLayout
//    private lateinit var tietPassword: TextInputEditText
//    private lateinit var tilPassword: TextInputLayout
//    val loginViewModel: LoginViewModel by viewModels()
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentLoginBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//        logi("Robe: Oncreate fragmen login")
//        return root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        logi("Robe: Entro onViewCreated")
//        initComponents()
//        loginObservers()
//    }
//
//    private fun loginObservers() {
//            logi("Robe: Entro fragment loginObserver")
//        loginViewModel.login.observe(viewLifecycleOwner) { login ->
//            shared.saveSession(login)
//        }
//        loginViewModel.tokenExpired.observe(viewLifecycleOwner) { tokenExpirado ->
//            if (tokenExpirado) {
//                // regresarlo al login
//            }
//        }
//    }
//
//    private fun initComponents() {
//        shared = SharedPreferencesInstance.getInstance(requireActivity().getApplicationContext())
//        binding.buttonIniciarSesion.setOnClickListener {
//            startLogIn()
//        }
//
//        // Adding Listeners
//        tietEmail = binding.textInputTextEmail
//        tilEmail = binding.textInputLayoutEmail
//        addIsEmailCorrectListener(requireActivity().getApplicationContext(), tietEmail, tilEmail)
//
//        tietPassword = binding.textInputEditTextPassword
//        tilPassword = binding.textInputLayoutContrasena
//        addIsEmptyChecker(requireActivity().getApplicationContext(), tietPassword, tilPassword)
//    }
//
//    private fun startLogIn() {
//        isEmailCorrect(requireActivity().getApplicationContext(), tietEmail, tilEmail)
//        isEmpty(requireActivity().getApplicationContext(), tietPassword, tilPassword)
//
//        if (!tilEmail.isErrorEnabled && !tilPassword.isErrorEnabled) {
//            val email = tietEmail.text.toString()
//            val pass = tietPassword.text.toString()
//            loginViewModel.login(LoginDto(email, pass))
//        } else {
//            if (tietEmail.text!!.isEmpty() && tietEmail.text!!.isEmpty())
//                tilEmail.requestFocus()
//            else if (tietPassword.text!!.isEmpty())
//                tilPassword.requestFocus()
//            else
//                tilEmail.requestFocus()
//        }
//    }

}