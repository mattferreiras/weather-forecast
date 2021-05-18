package br.com.weather.forecast.ml.domain.use_case

import br.com.weather.forecast.ml.domain.to.PlanetTO
import br.com.weather.forecast.ml.domain.to.PointTO
import org.springframework.stereotype.Component
import kotlin.math.cos
import kotlin.math.roundToLong
import kotlin.math.sin

@Component
class PlanetRotationUseCase {

    fun calcPlanetPositionByDay(planet: PlanetTO, dayNumber: Int) {
        val speedDegrees = ((planet.angularSpeed * planet.direction.mutiplyFactor) * dayNumber).toDouble()
        val speedRadians = Math.toRadians(speedDegrees)
        val planetPosition = planet.position ?: PointTO(0.0, planet.sunDistance.toDouble())

        val newPositionX = ((planetPosition.x * cos(speedRadians)) - (planetPosition.y * sin(speedRadians))).roundToLong()
        val newPositionY = ((planetPosition.y * cos(speedRadians)) + (planetPosition.x * sin(speedRadians))).roundToLong()

        planet.position = PointTO(newPositionX.toDouble(), newPositionY.toDouble())
    }
}
