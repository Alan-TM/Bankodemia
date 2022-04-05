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
import mx.backoders.bankodemia.common.utils.isBirthdayValid
import mx.backoders.bankodemia.common.utils.isEmpty
import mx.backoders.bankodemia.databinding.FragmentPersonalDetailsBinding
import mx.backoders.bankodemia.ui.singup.viewmodel.SignUpViewModel

class PersonalDetailsFragment : Fragment() {
    private var _binding: FragmentPersonalDetailsBinding? = null
    private val binding get() = _binding!!

    private val signUpViewModel: SignUpViewModel by activityViewModels()

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
    //private lateinit var tilBirthday: TextInputLayout

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
        initializeObservers()
    }

    private fun initializeObservers() {
        with(signUpViewModel) {
            name.observe(viewLifecycleOwner) {
                tietName.setText(it)
            }

            lastName.observe(viewLifecycleOwner) {
                tietLastName.setText(it)
            }

            occupation.observe(viewLifecycleOwner) {
                tietOccupation.setText(it)
            }

            birthdayForView.observe(viewLifecycleOwner) {
                tietBirthday.setText(it)
            }
        }
    }

    private fun initUI() {
        tietName = binding.personaldetailNameTiet
        tilName = binding.personaldetailNameTil
        tietLastName = binding.personaldetailLastnameTiet
        tilLastName = binding.personaldetailLastnameTil
        tietOccupation = binding.personaldetailOccupationTiet
        tilOccupation = binding.personaldetailOccupationTil
        tietBirthday = binding.personaldetailBirthdayTiet
        //tilBirthday = binding.personaldetailBirthdayTil

        //TODO Make birthday edit text not editable but clickable

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
                isBirthdayValid(requireContext(), tietBirthday)
            ) {
                it.findNavController()
                    .navigate(R.id.action_personalDetailsFragment_to_cellphoneFragment)
            }
        }
        binding.returnLogin.setOnClickListener {
            it.findNavController().navigateUp()
        }

        tietBirthday.setOnClickListener {
            showPickerDialog()
        }

    }

    private fun showPickerDialog() {
        val datePicker =
            DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(requireActivity().supportFragmentManager, "date")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        signUpViewModel.setBirthdayParsers(day, month, year)
    }


    override fun onStop() {
        super.onStop()
        setUserInformationData()
    }

    private fun setUserInformationData(){
        signUpViewModel.setUserPersonalInfo(
            tietName.text.toString(),
            tietLastName.text.toString(),
            tietOccupation.text.toString(),
        )
    }

}