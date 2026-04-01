package edu.ucne.antillasaquadexapp.presentation.especies.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.antillasaquadexapp.domain.model.Especie
import edu.ucne.antillasaquadexapp.domain.repository.EspecieRepository
import edu.ucne.antillasaquadexapp.util.Resource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class FiltroTipo { TODOS, ANIMAL, PLANTA }

@HiltViewModel
class EspecieListViewModel @Inject constructor(
    private val repository: EspecieRepository
) : ViewModel() {

    private val _state = MutableStateFlow(EspecieListUiState())
    val state = _state.asStateFlow()

    init {
        loadEspecies()
    }

    fun loadEspecies() {
        viewModelScope.launch {
            // No mostramos loading si ya tenemos datos, para evitar parpadeos
            if (_state.value.especies.isEmpty()) {
                _state.update { it.copy(isLoading = true, error = null) }
            }

            when (val result = repository.getEspecies()) {
                is Resource.Success -> {
                    val list = result.data ?: emptyList()
                    _state.update { it.copy(
                        isLoading = false,
                        especies = list,
                        especiesFiltradas = aplicarFiltros(list, it.busqueda, it.filtroTipo)
                    ) }
                }
                is Resource.Error -> {
                    _state.update { it.copy(isLoading = false, error = result.message) }
                }
                else -> {}
            }
        }
    }

    fun onBusquedaChange(query: String) {
        _state.update { it.copy(
            busqueda = query,
            especiesFiltradas = aplicarFiltros(it.especies, query, it.filtroTipo)
        ) }
    }

    fun onFiltroTipoChange(tipo: FiltroTipo) {
        _state.update { it.copy(
            filtroTipo = tipo,
            especiesFiltradas = aplicarFiltros(it.especies, it.busqueda, tipo)
        ) }
    }

    fun limpiarBusqueda() {
        onBusquedaChange("")
    }

    private fun aplicarFiltros(list: List<Especie>, query: String, tipo: FiltroTipo): List<Especie> {
        return list.filter { especie ->
            val coincideBusqueda = especie.nombre.contains(query, ignoreCase = true) || 
                                   especie.nombreCientifico.contains(query, ignoreCase = true)
            val coincideTipo = when (tipo) {
                FiltroTipo.TODOS -> true
                FiltroTipo.ANIMAL -> especie.esAnimal
                FiltroTipo.PLANTA -> !especie.esAnimal
            }
            coincideBusqueda && coincideTipo
        }
    }
}
