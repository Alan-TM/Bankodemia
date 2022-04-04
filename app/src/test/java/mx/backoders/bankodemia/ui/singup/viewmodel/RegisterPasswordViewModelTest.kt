package mx.backoders.bankodemia.ui.singup.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import mx.backoders.bankodemia.common.utils.PasswordError
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RegisterPasswordViewModelTest {
    private lateinit var passwordViewModel: RegisterPasswordViewModel

    @get:Rule
    var schedulers : InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)

        passwordViewModel = RegisterPasswordViewModel()
        passwordViewModel.setupMediator()

    }

    @Test
    fun `password empty returns EMPTY`(){
        val password = ""
        val expect = PasswordError.EMPTY
        passwordViewModel.isEmptyPassword(password)

        passwordViewModel.mediatorPasswordErrorLiveData.observeForever{
            assertEquals(expect, it)
        }
    }

    @Test
    fun `password consecutive characters return CONSECUTIVE_CHARACTERS`(){
        val password = "abc"
        val expect = PasswordError.CONSECUTIVE_CHARACTERS
        passwordViewModel.isValidConsecutivePassword(password)

        passwordViewModel.mediatorPasswordErrorLiveData.observeForever{
            assertEquals(expect, it)
        }

        val password2 = "cba"
        passwordViewModel.isValidConsecutivePassword(password2)
        passwordViewModel.mediatorPasswordErrorLiveData.observeForever{
            assertEquals(expect, it)
        }
    }

    @Test
    fun `password consecutive numbers return CONSECUTIVE_CHARACTERS`(){
        val password = "123"
        val expect = PasswordError.CONSECUTIVE_CHARACTERS
        passwordViewModel.isValidConsecutivePassword(password)

        passwordViewModel.mediatorPasswordErrorLiveData.observeForever{
            assertEquals(expect, it)
        }

        val password2 = "321"
        passwordViewModel.isValidConsecutivePassword(password2)
        passwordViewModel.mediatorPasswordErrorLiveData.observeForever{
            assertEquals(expect, it)
        }
    }

    @Test
    fun `password length less than 6 returns MIN_LENGTH`(){
        val password = "abc12"
        val expect = PasswordError.MIN_LENGTH

        passwordViewModel.minLengthPassword(password)
        passwordViewModel.mediatorPasswordErrorLiveData.observeForever{
            assertEquals(expect, it)
        }
    }

    @Test
    fun `password1 not same as password2 returns MATCHIN_PASSWORD`(){
        val password1 = "backoders312"
        val password2 = "backoders32"
        val expect = PasswordError.NOT_MATCHING
        passwordViewModel.isSamePassword(password1, password2)

        passwordViewModel.mediatorPasswordErrorLiveData.observeForever{
            assertEquals(expect, it)
        }
    }

    @Test
    fun `password without error returns NONE`(){
        val password = "bkodr312"
        val password2 = "bkodr312"
        val expect = PasswordError.NONE
        with(passwordViewModel) {
            isEmptyPassword(password)
            isValidConsecutivePassword(password)
            isValidRepeatedCharacters(password)
            minLengthPassword(password)
            isSamePassword(password, password2)
        }

        passwordViewModel.mediatorPasswordErrorLiveData.observeForever{
            assertEquals(expect, it)
        }
    }

    @After
    fun onAfter(){
        passwordViewModel.clearMediators()
    }
}