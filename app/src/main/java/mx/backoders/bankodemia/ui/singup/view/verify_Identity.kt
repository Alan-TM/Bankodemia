package mx.backoders.bankodemia.ui.singup.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.utils.IdentityImageType
import mx.backoders.bankodemia.common.utils.IdentityImageType.*
import mx.backoders.bankodemia.databinding.FragmentVerifyIdentityBinding
import mx.backoders.bankodemia.ui.singup.viewmodel.SignUpViewModel

class verify_Identity : Fragment() {

    private var _binding: FragmentVerifyIdentityBinding? = null
    private val binding get() = _binding!!

    private val signUpViewModel: SignUpViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVerifyIdentityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUI()
    }

    private fun initializeUI() {
        with(binding) {
            verifyIdentityPassportCar.setOnClickListener {
                findNavController().navigate(R.id.action_verify_Identity_to_passport)
                signUpViewModel.setIdentityImageType(PASSPORT)
            }

            verifyIdentityDocumentmigratoryCard.setOnClickListener {
                findNavController().navigate(R.id.action_verify_Identity_to_immigration_document)
                signUpViewModel.setIdentityImageType(MIGRATION_FORM)
            }

            ineIneCard.setOnClickListener {
                findNavController().navigate(R.id.action_verify_Identity_to_ine)
                signUpViewModel.setIdentityImageType(INE)
            }
        }
    }
}