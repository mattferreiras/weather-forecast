package br.com.weather.forecast.ml.domain.to

data class PlanetTO(
    val name: PlanetNameEnum,
    val angularSpeed: Int,
    val direction: RotationDirectionEnum,
    val sunDistance: Int,
    var position: PointTO? = null
)