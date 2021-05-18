package br.com.weather.forecast.ml.domain.use_case

import br.com.weather.forecast.ml.domain.to.PlanetNameEnum
import br.com.weather.forecast.ml.domain.to.PlanetTO
import br.com.weather.forecast.ml.domain.to.PointTO
import br.com.weather.forecast.ml.domain.to.RotationDirectionEnum
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class PlanetRotationUseCaseTest {

    private val planetRotationUseCase = PlanetRotationUseCase()

    @ParameterizedTest(name = "Check if Ferings rotation is ok. Arguments: \"{argumentsWithNames}\\")
    @MethodSource("argumentsFerengisPlanetStartPosition")
    fun `should valid new position for Ferengis`(planet: PlanetTO, dayNumber: Int) {
        planetRotationUseCase.calcPlanetPositionByDay(planet, dayNumber)
        Assertions.assertNotNull(planet.position)
        Assertions.assertEquals(-9.0, planet.position?.x)
        Assertions.assertEquals(500.0, planet.position?.y)
    }

    @ParameterizedTest(name = "Check if Ferings rotation is ok for day 2. Arguments: \"{argumentsWithNames}\\")
    @MethodSource("argumentsFerengisPlanetDay2Position")
    fun `should valid new position for Ferengis from last one`(planet: PlanetTO, dayNumber: Int) {
        planetRotationUseCase.calcPlanetPositionByDay(planet, dayNumber)
        Assertions.assertNotNull(planet.position)
        Assertions.assertEquals(-26.0, planet.position?.x)
        Assertions.assertEquals(499.0, planet.position?.y)
    }

    companion object {
        @JvmStatic
        fun argumentsFerengisPlanetStartPosition(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    PlanetTO(
                        PlanetNameEnum.FERENGIS,
                        1,
                        RotationDirectionEnum.CLOCKWISE,
                        500
                    ),
                    1
                )
            )

        @JvmStatic
        fun argumentsFerengisPlanetDay2Position(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    PlanetTO(
                        PlanetNameEnum.FERENGIS,
                        1,
                        RotationDirectionEnum.CLOCKWISE,
                        500,
                        PointTO(-9.0, 500.0)
                    ),
                    2
                )
            )
    }
}