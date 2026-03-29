package edu.ucne.antillasaquadexapp.presentation.especies.list

import edu.ucne.antillasaquadexapp.domain.model.Especie

data class EspecieListUiState(
    val isLoading: Boolean = false,
    val especies: List<Especie> = emptyList(),
    val especiesFiltradas: List<Especie> = emptyList(),
    val busqueda: String = "",
    val filtroTipo: FiltroTipo = FiltroTipo.TODOS,
    val error: String? = null
)