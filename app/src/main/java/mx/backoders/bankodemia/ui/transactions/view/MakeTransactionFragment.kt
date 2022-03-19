package mx.backoders.bankodemia.ui.transactions.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import mx.backoders.bankodemia.databinding.FragmentMakeTransactionBinding

class MakeTransactionFragment : Fragment() {
    private var _binding: FragmentMakeTransactionBinding? = null

    private val binding get() = _binding!!
    private lateinit var contactID: String

    override fun onCreate(savedInstanceState: Bundle?) {

        arguments?.let{
            contactID = it.getString("contactID").toString()
        }
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMakeTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(requireContext(), contactID, Toast.LENGTH_LONG).show()

        initializeUI()
    }

    private fun initializeUI(){
        binding.makeTransactionBackButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}