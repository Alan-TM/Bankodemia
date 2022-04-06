package mx.backoders.bankodemia.ui.singup.view

import android.os.Bundle
<<<<<<< HEAD
import android.util.Log
=======
>>>>>>> develop
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
import android.widget.ArrayAdapter
import android.widget.Toast
=======
>>>>>>> develop
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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

<<<<<<< HEAD
=======
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressedCallbackHandler()
            }
        })
    }

>>>>>>> develop
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

<<<<<<< HEAD
    override fun onStop() {
        super.onStop()
        if (tietPhone.text.toString().isNotBlank())
            signUpViewModel.setUserPhone(tietPhone.text.toString())
    }

    private fun initializeObservers() {
        with(signUpViewModel) {
            phone.observe(viewLifecycleOwner) {
                tietPhone.setText(it)
            }

            phoneLada.observe(viewLifecycleOwner){
                binding.ladaPhone.setText(it)
                setupDropDownLadas()
            }
=======
    private fun initializeObservers() {
        with(signUpViewModel){
            phone.observe(viewLifecycleOwner){
                tietPhone.setText(it)
            }
>>>>>>> develop
        }
    }

    private fun initUI() {
        tietPhone = binding.phonePhoneEdittextTiet
        tilPhone = binding.phonePhoneEdittextTil
        binding.phoneContinueButton.setOnClickListener {
            if (!isEmpty(requireActivity().getApplicationContext(), tietPhone, tilPhone) &&
                !addLengthChecker(
                    requireActivity().getApplicationContext(), tietPhone, tilPhone,
<<<<<<< HEAD
                    PhoneLenght.Local.length
                )
            ) {
                it.findNavController().navigate(R.id.action_cellphoneFragment_to_intro_Identity)
=======
                    PhoneLenght.UniversalLength.length
                )
            ) {
                it.findNavController().navigate(R.id.action_cellphoneFragment_to_intro_Identity)
                signUpViewModel.setUserPhone(tietPhone.text.toString())
>>>>>>> develop
            }
        }
        binding.returnLogin.setOnClickListener {
            it.findNavController().navigateUp()
        }
    }

<<<<<<< HEAD
    private fun setupDropDownLadas(){
        val array_de_strings = resources.getStringArray(R.array.lada_phones)
        val arrayAdapterRama =
            ArrayAdapter(
                requireActivity().applicationContext,
                android.R.layout.simple_list_item_1,
                array_de_strings.sortedArray()
            )
        binding.ladaPhone.setAdapter(arrayAdapterRama)
        binding.ladaPhone.setOnItemClickListener { parent, view, position, id ->
            signUpViewModel.setUserPhoneLada(arrayAdapterRama.getItem(position).toString())
        }
=======
    private fun onBackPressedCallbackHandler() {
        findNavController().navigateUp()
        if (tietPhone.text.toString().isNotEmpty() && tietPhone.text.toString().isNotBlank())
            signUpViewModel.setUserPhone(tietPhone.text.toString())
>>>>>>> develop
    }
}