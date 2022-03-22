package mx.backoders.bankodemia.ui.services.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.backoders.bankodemia.common.model.Services.Services

class ServicesViewModel : ViewModel() {
    private val _servicesList = MutableLiveData<ArrayList<Services>>()
    val servicesList: LiveData<ArrayList<Services>> get() = _servicesList

    fun fetchServices(){
        _servicesList.value = buildListOfServices()
    }

    private fun buildListOfServices(): ArrayList<Services> {
        val dummyList = ArrayList<Services>()
        dummyList.add(Services("Recarga de celular", "Telcel, AT&T, Movistar"))
        dummyList.add(Services("Plan móvil", "AT&T, Telcel, Movistar"))
        dummyList.add(Services("Internet/TV/Télefono", "Telmex, Izzi, TotalPlay"))
        dummyList.add(Services("Gas", "Naturgy y Compañía Mexicana"))
        dummyList.add(Services("Tarjetas de crédito", "BBVA, Santander, Banorte, Banamex"))
        dummyList.add(Services("Tiendas departamentales", "Liverpool, Sears, Palacio de Hierro"))

        return dummyList
    }
}