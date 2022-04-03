package mx.backoders.bankodemia.ui.singup.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.utils.*
import mx.backoders.bankodemia.databinding.FragmentCellphoneBinding
import mx.backoders.bankodemia.ui.singup.viewmodel.SignUpViewModel

class CellphoneFragment : Fragment() {

    private var _binding: FragmentCellphoneBinding? = null
    private val binding get() = _binding!!
    private lateinit var tietPhone: TextInputEditText
    private lateinit var tilPhone: TextInputLayout

    private val signUpViewModel: SignUpViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressedCallbackHandler()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCellphoneBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initializeObservers()
    }

    private fun initializeObservers() {
        with(signUpViewModel){
            phone.observe(viewLifecycleOwner){
                tietPhone.setText(it)
            }
        }
    }

    private fun initUI() {
        tietPhone = binding.phonePhoneEdittextTiet
        tilPhone = binding.phonePhoneEdittextTil
        binding.phoneContinueButton.setOnClickListener {
            if (!isEmpty(requireActivity().getApplicationContext(), tietPhone, tilPhone) &&
                !addLengthChecker(
                    requireActivity().getApplicationContext(), tietPhone, tilPhone,
                    PhoneLenght.UniversalLength.length
                )
            ) {
                it.findNavController().navigate(R.id.action_cellphoneFragment_to_intro_Identity)
                signUpViewModel.setUserPhone(tietPhone.text.toString())
            }
        }
        binding.returnLogin.setOnClickListener {
            it.findNavController().navigateUp()
        }
    }

    private fun onBackPressedCallbackHandler() {
        findNavController().navigateUp()
        if (tietPhone.text.toString().isNotEmpty() && tietPhone.text.toString().isNotBlank())
            signUpViewModel.setUserPhone(tietPhone.text.toString())
    }
}