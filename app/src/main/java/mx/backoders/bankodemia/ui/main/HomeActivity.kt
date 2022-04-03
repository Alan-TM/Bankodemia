package mx.backoders.bankodemia.ui.main

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.model.login.UserLoginResponse
import mx.backoders.bankodemia.common.preferences.SharedPreferencesInstance
import mx.backoders.bankodemia.databinding.ActivityHomeBinding
import mx.backoders.bankodemia.ui.home.view.DialogHelp
import mx.backoders.bankodemia.ui.home.view.SignOut
import mx.backoders.bankodemia.ui.home.viewmodel.HomeViewModel
import mx.backoders.bankodemia.ui.transactions.viewmodel.TransactionsViewModel

@RequiresApi(Build.VERSION_CODES.O)
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val makeTransactionViewModel: TransactionsViewModel by viewModels()

//    private lateinit var sharedPreferences: SharedPreferencesInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //this should be added in the login activity
//        sharedPreferences = SharedPreferencesInstance.getInstance(this)
//        sharedPreferences.saveSession(UserLoginResponse("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2MjFmYTNmYjhjZTZjNDc4ZDBlMWI5OTEiLCJpYXQiOjE2NDg0ODgxMTUsImV4cCI6MTY0ODU3NDUxNX0.69ZgcnR781dtTPEFl-9yXn5go5bAaRz9oV8ff7j3t-I", "24h"))
        //sharedPreferences.getPreference("token")?.let{ Log.e("SharedPreferences", it) }
        //sharedPreferences.getPreference("expiresIn")?.let{ Log.e("SharedPreferences", it) }
        initUI()
        navigationSetup()
        initializeObservers()
    }

    private fun initUI() {
        binding.infoHomeButton.setOnClickListener {
            showHelpDialog()
        }
        binding.userHomeButton.setOnClickListener {
            showSignOutDialog()
        }

    }

    private fun showSignOutDialog() {
        val newFragment = SignOut()
        newFragment.show(supportFragmentManager, "signout")
    }

    fun showHelpDialog() {
        val newFragment = DialogHelp()
        newFragment.show(supportFragmentManager, "help")
    }

    private fun initializeObservers() {
        with(viewModel) {
            bottomNavIsVisible.observe(this@HomeActivity) { binding.navView.isVisible = it }
            topToolbarIsVisible.observe(this@HomeActivity) { binding.actionBar.isVisible = it }
            androidNavigationBarIsVisible.observe(this@HomeActivity) {
                setHideAndroidNavigationBar(
                    it
                )
            }
        }
    }

    private fun navigationSetup() {
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_home)
        navView.setupWithNavController(navController)
    }

    private fun setHideAndroidNavigationBar(hide: Boolean) {
        if (hide) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        }
    }
}