package mx.backoders.bankodemia.ui.singup.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
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
        }
    }

    private fun initUI() {
        tietPhone = binding.phonePhoneEdittextTiet
        tilPhone = binding.phonePhoneEdittextTil
        binding.phoneContinueButton.setOnClickListener {
            if (!isEmpty(requireActivity().getApplicationContext(), tietPhone, tilPhone) &&
                !addLengthChecker(
                    requireActivity().getApplicationContext(), tietPhone, tilPhone,
                    PhoneLenght.Local.length
                )
            ) {
                it.findNavController().navigate(R.id.action_cellphoneFragment_to_intro_Identity)
            }
        }
        binding.returnLogin.setOnClickListener {
            it.findNavController().navigateUp()
        }

        // DropDown Rama Artesanal
        val array_de_strings = resources.getStringArray(R.array.lada_phones)
        val arrayAdapterRama =                          // Se declara el adapter
            ArrayAdapter(
                requireActivity().applicationContext,   // Contexto del adapter
                android.R.layout.simple_list_item_1,    // Layout para desplegar la lista
                array_de_strings.sortedArray()            // Arreglo de strings
            )
        binding.ladaPhone.setAdapter(arrayAdapterRama)  // Se asigna el adapter
        binding.ladaPhone.setOnItemClickListener { parent, view, position, id ->
            val value = arrayAdapterRama.getItem(position)
                ?: ""    // Se obtiene el valor del item de la lista
//        Toast.makeText(
//            requireContext(),
//            "$position selected with value $value",
//            Toast.LENGTH_LONG
//        ).show()
        }
    }
}