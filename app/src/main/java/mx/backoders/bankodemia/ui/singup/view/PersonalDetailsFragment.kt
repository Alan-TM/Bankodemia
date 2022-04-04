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
import mx.backoders.bankodemia.databinding.FragmentPersonalDetailsBinding

class PersonalDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentPersonalDetailsBinding? = null
    private val binding get() = _binding!!

    // Name
    private lateinit var tietName: TextInputEditText
    private lateinit var tilName: TextInputLayout

    // LastName
    private lateinit var tietLastName: TextInputEditText
    private lateinit var tilLastName: TextInputLayout

    // Occupation
    private lateinit var tietOccupation: TextInputEditText
    private lateinit var tilOccupation: TextInputLayout

    // Birthday
    private lateinit var tietBirthday: TextInputEditText
    private lateinit var tilBirthday: TextInputLayout


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
        tietName = binding.personaldetailNameTiet
        tilName = binding.personaldetailNameTil
        tietLastName = binding.personaldetailLastnameTiet
        tilLastName = binding.personaldetailLastnameTil
        tietOccupation = binding.personaldetailOccupationTiet
        tilOccupation = binding.personaldetailOccupationTil
        tietBirthday = binding.personaldetailBirthdayTiet
        tilBirthday = binding.personaldetailBirthdayTil

        binding.personaldetailsContinueButton.setOnClickListener {
            if (!isEmpty(requireActivity().getApplicationContext(), tietName, tilName) &&
                !isEmpty(
                    requireActivity().getApplicationContext(),
                    tietLastName,
                    tilLastName
                ) &&
                !isEmpty(
                    requireActivity().getApplicationContext(),
                    tietOccupation,
                    tilOccupation
                ) &&
                !isEmpty(requireActivity().getApplicationContext(), tietBirthday, tilBirthday)
            ) {
                it.findNavController()
                    .navigate(R.id.action_personalDetailsFragment_to_cellphoneFragment)
            }
        }
        binding.returnLogin.setOnClickListener {
            it.findNavController().navigateUp()
        }
    }


}