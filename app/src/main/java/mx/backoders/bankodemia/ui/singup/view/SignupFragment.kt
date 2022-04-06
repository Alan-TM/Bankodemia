package mx.backoders.bankodemia.ui.singup.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.utils.*
import mx.backoders.bankodemia.databinding.FragmentSignupBinding
import mx.backoders.bankodemia.ui.singup.viewmodel.SignUpViewModel


class SignupFragment : Fragment() {
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    private val signUpViewModel: SignUpViewModel by activityViewModels()

    private lateinit var tietEmail: TextInputEditText
    private lateinit var tilEmail: TextInputLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUI()
        initializeObservers()
<<<<<<< HEAD
    }

    override fun onStop() {
        super.onStop()
        if (tietEmail.text.toString().isNotBlank())
            signUpViewModel.setUserEmail(tietEmail.text.toString())
=======
>>>>>>> develop
    }

    private fun initializeUI() {
        //Adding Listeners
        tietEmail = binding.signupWriteemailEdittextTiet
        tilEmail = binding.signupWriteemailEdittextTil
<<<<<<< HEAD

        addIsEmailCorrectListener(requireActivity().getApplicationContext(), tietEmail, tilEmail)
        //addIsEmptyChecker(requireActivity().getApplicationContext(), tietEmail, tilEmail)

        binding.signupContinueButton.setOnClickListener {
            if (!isEmpty(requireActivity().getApplicationContext(), tietEmail, tilEmail)) {
                if(!isEmailCorrect(requireContext(), tietEmail, tilEmail))
                    it.findNavController()
                        .navigate(R.id.action_signupFragment_to_personalDetailsFragment)
            }
        }
        binding.returnLogin.setOnClickListener {
            it.findNavController().navigateUp()
        }
    }

    private fun initializeObservers() {
        with(signUpViewModel) {
            email.observe(viewLifecycleOwner) {
                binding.signupWriteemailEdittextTil.editText?.setText(it)
                /*addIsEmailCorrectListener(
                    requireActivity().getApplicationContext(),
                    tietEmail,
                    tilEmail
                )*/
                //addIsEmptyChecker(requireActivity().getApplicationContext(), tietEmail, tilEmail)
=======
        addIsEmailCorrectListener(requireActivity().getApplicationContext(), tietEmail, tilEmail)
        addIsEmptyChecker(requireActivity().getApplicationContext(), tietEmail, tilEmail)

        binding.signupContinueButton.setOnClickListener {
            if (!isEmpty(requireActivity().getApplicationContext(), tietEmail, tilEmail)) {
                signUpViewModel.setUserEmail(tietEmail.text.toString())
                it.findNavController()
                    .navigate(R.id.action_signupFragment_to_personalDetailsFragment)
            }
        }
        binding.returnLogin.setOnClickListener {
            it.findNavController().navigateUp()
            signUpViewModel.setUserEmail(tietEmail.text.toString())
        }
    }

    private fun initializeObservers(){
        with(signUpViewModel){
            email.observe(viewLifecycleOwner){
                binding.signupWriteemailEdittextTil.editText?.setText(it)
                addIsEmailCorrectListener(requireActivity().getApplicationContext(), tietEmail, tilEmail)
                addIsEmptyChecker(requireActivity().getApplicationContext(), tietEmail, tilEmail)
>>>>>>> develop
            }
        }
    }
}

