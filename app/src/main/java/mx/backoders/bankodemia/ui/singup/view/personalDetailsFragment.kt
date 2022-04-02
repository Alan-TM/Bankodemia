package mx.backoders.bankodemia.ui.singup.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.utils.isEmpty
import mx.backoders.bankodemia.common.utils.isEmptyTiet
import mx.backoders.bankodemia.databinding.FragmentPersonalDetailsBinding

class personalDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentPersonalDetailsBinding? = null
    private val binding get() = _binding!!

    // Name
    private lateinit var tietName: TextInputEditText
    // LastName
    private lateinit var tietLastName: TextInputEditText
    // Occupation
    private lateinit var tietOcuupation: TextInputEditText
    // Birthday
    private lateinit var tietBirthday: TextInputEditText



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPersonalDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       initUI()
    }

    private fun initUI() {
        tietName= binding.personaldetailNameTexteditTil
        tietLastName = binding.personaldetailLastnameTexteditTil
        tietOcuupation = binding.personaldetailOcupationTextedit
        tietBirthday = binding.personaldetailBirthdayTexteditTil

        binding.personaldetailsContinueButton.setOnClickListener{
            if (!isEmptyTiet(requireActivity().getApplicationContext(),tietName) &&
                !isEmptyTiet(requireActivity().getApplicationContext(),tietLastName) &&
                !isEmptyTiet(requireActivity().getApplicationContext(),tietOcuupation) &&
                !isEmptyTiet(requireActivity().getApplicationContext(),tietBirthday) ) {
                it.findNavController().navigate(R.id.action_personalDetailsFragment_to_cellphoneFragment)
            }
        }
        binding.returnLogin.setOnClickListener{
            it.findNavController().navigateUp()
        }
    }


}