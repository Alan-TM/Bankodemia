package mx.backoders.bankodemia.ui.main

import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.model.login.UserLoginResponse
import mx.backoders.bankodemia.common.preferences.SharedPreferencesInstance
import mx.backoders.bankodemia.databinding.ActivityHomeBinding
import mx.backoders.bankodemia.ui.home.viewmodel.HomeViewModel
import mx.backoders.bankodemia.ui.transactions.viewmodel.TransactionsViewModel

@RequiresApi(Build.VERSION_CODES.O)
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val makeTransactionViewModel: TransactionsViewModel by viewModels()

    private lateinit var sharedPreferences: SharedPreferencesInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //this should be added in the login activity
        sharedPreferences = SharedPreferencesInstance.getInstance(this)
        sharedPreferences.saveSession(UserLoginResponse("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2MjFmYTNmYjhjZTZjNDc4ZDBlMWI5OTEiLCJpYXQiOjE2NDgxNTQ4MDksImV4cCI6MTY0ODI0MTIwOX0.nsxQLmqQVMoPqXe8KRSMyh6G7DJ-77Zvn0HRbyFQfvU", "24h"))
        //sharedPreferences.getPreference("token")?.let{ Log.e("SharedPreferences", it) }
        //sharedPreferences.getPreference("expiresIn")?.let{ Log.e("SharedPreferences", it) }

        viewModel.getUserProfile()

        navigationSetup()
        initializeObservers()
    }

    private fun initializeObservers(){
        viewModel.bottomNavIsVisible.observe(this){ binding.navView.isVisible = it }
        viewModel.topToolbarIsVisible.observe(this){ binding.actionBar.isVisible = it }
    }

    private fun navigationSetup(){
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_home)
        navView.setupWithNavController(navController)
    }
}