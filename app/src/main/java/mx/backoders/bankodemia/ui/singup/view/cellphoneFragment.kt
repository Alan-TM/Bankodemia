package mx.backoders.bankodemia.ui.singup.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputEditText
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.utils.isEmptyTiet
import mx.backoders.bankodemia.databinding.FragmentCellphoneBinding

class cellphoneFragment : Fragment() {

    private var _binding: FragmentCellphoneBinding? = null
    private val binding get() = _binding!!
    private lateinit var tietPhone: TextInputEditText


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
    }

    private fun initUI() {
        tietPhone = binding.phonePhoneEdittextTil
        binding.phoneContinueButton.setOnClickListener{
            if (!isEmptyTiet(requireActivity().getApplicationContext(),tietPhone)){
                it.findNavController().navigate(R.id.action_cellphoneFragment_to_intro_Identity)
                }
        }
        binding.returnLogin.setOnClickListener{
            it.findNavController().navigateUp()
        }
    }
}