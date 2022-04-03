package mx.backoders.bankodemia

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import mx.backoders.bankodemia.common.utils.IdentityImageType
import mx.backoders.bankodemia.common.utils.IdentityImageType.*
import mx.backoders.bankodemia.databinding.FragmentIneBinding
import mx.backoders.bankodemia.ui.singup.viewmodel.SignUpViewModel
import java.io.File
import java.io.IOException

private const val CAMERA_REQUEST_CODE = 100
private const val AUTHORITY_FILE_PROVIDER = "mx.backoders.bankodemia.fileprovider"

@RequiresApi(Build.VERSION_CODES.O)
class Ine : Fragment() {

    private var _binding: FragmentIneBinding? = null
    private val binding get() = _binding!!

    private val signUpViewModel: SignUpViewModel by activityViewModels()

    private var photoFile: File? = null
    private lateinit var currentPhotoPath: String
    private lateinit var photoURI: Uri

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                photoFile?.let {
                    signUpViewModel.setIdentityImageType(INE)
                    signUpViewModel.setIdentityImage(signUpViewModel.decodeImageForAPI(it))
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUI()
        initializeObservers()
    }

    private fun initializeUI() {
        with(binding){
            ineIneCard.setOnClickListener { permissionSetup() }

            returnLogin.setOnClickListener { findNavController().navigateUp() }

            ineUploadinformationButton.setOnClickListener { findNavController().navigate(R.id.action_ine_to_create_Password) }
        }
    }

    private fun initializeObservers(){
        with(signUpViewModel){
            identityImageIne.observe(viewLifecycleOwner){
                binding.ineUploadinformationButton.isEnabled = !it.isNullOrBlank()
            }
        }
    }

    private fun permissionSetup() {
        val cameraPermission =
            ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA)
        if (cameraPermission == PackageManager.PERMISSION_GRANTED) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.CAMERA),
                CAMERA_REQUEST_CODE
            )
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun startCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireContext().packageManager)?.also {
                photoFile = try {
                    createImageFile()
                } catch (e: IOException) {
                    null
                }

                photoFile?.also { file ->
                    photoURI = FileProvider.getUriForFile(
                        requireContext(),
                        AUTHORITY_FILE_PROVIDER,
                        file
                    )
                }
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                takePictureIntent.putExtra("CAMERA_REQUEST_CODE", CAMERA_REQUEST_CODE)
                startForResult.launch(takePictureIntent)
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp = signUpViewModel.createTimeStampForImage()
        val storageDir: File? = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            timeStamp,
            ".jpeg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }
}