package mx.backoders.bankodemia.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.preferences.SharedPreferencesInstance
import mx.backoders.bankodemia.common.utils.logi
import mx.backoders.bankodemia.databinding.ActivityMainBinding
import mx.backoders.bankodemia.databinding.FragmentLoginBinding
import mx.backoders.bankodemia.ui.home.view.HomeFragment
import mx.backoders.bankodemia.ui.home.viewmodel.HomeViewModel
import mx.backoders.bankodemia.ui.login.view.LoginFragment
import mx.backoders.bankodemia.ui.login.viewmodel.LoginViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var shared: SharedPreferencesInstance
    private val loginViewModel: LoginViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()
        showLoginFragment()
//        showHomeFragment()
        navigationSetup()
    }

    private fun initComponents() {
        // TODO Robert Do something here?
        loginObservers()
    }
    private fun loginObservers() {

        logi("Robe: Entro MAIN loginObservers")
        loginViewModel.success.observe(this){ success ->
            if (loginViewModel.success.value == true){
                logi("Robe: Entro al chidoo")
            }else{
                logi("Robe: Entro al chafaaa")
            }
        }

//        loginViewModel.login.observe(this) { login ->
//            shared.saveSession(login)
//        }
//
//        loginViewModel.tokenExpired.observe(this) { tokenExpirado ->
//            if (tokenExpirado) {
//                // regresarlo al login
//            }
//        }
    }

    private fun showLoginFragment() {
        try {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainFragmentContainer, LoginFragment())
                .commit()
//            if(loginViewModel.success){
//                showHomeFragment()
//            }
        } catch (e: Exception) {
//            showErrorFragment()
        }
    }


    private fun showHomeFragment() {
        try {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainFragmentContainer, HomeFragment())
                .commit()
            homeViewModel.getUserProfile()
        } catch (e: Exception) {
//            showErrorFragment()
        }
    }

    private fun navigationSetup() {
        val navView: BottomNavigationView = binding.navView
//        val navController = binding.nav_view
        val navController = findNavController(R.id.mainFragmentContainer)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.nav_home, R.id.nav_card, R.id.nav_services
//            )
//        )
//        //setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
    }
}