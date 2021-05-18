package br.com.weather.forecast.ml.domain.use_case

import br.com.weather.forecast.ml.domain.to.PointTO
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class TrigonometryUseCaseTest {

    private val trigonometryUseCase = TrigonometryUseCase()

    @ParameterizedTest(name = "Check if 3 Point are aligned. Case [{index}] Points: \"{argumentsWithNames}\"")
    @MethodSource("argumentsAlignedPoints")
    fun `should return TRUE when points are aligned`(pointA: PointTO, pointB: PointTO, pointC: PointTO) {
        assertTrue(trigonometryUseCase.isAlignPoints(pointA, pointB, pointC));
    }

    @ParameterizedTest(name = "Check if 3 Point are NOT aligned. Case [{index}] Points: \"{argumentsWithNames}\"")
    @MethodSource("argumentsNotAlignedPoints")
    fun `should return FALSE when points are not aligned`(pointA: PointTO, pointB: PointTO, pointC: PointTO) {
        assertFalse(trigonometryUseCase.isAlignPoints(pointA, pointB, pointC));
    }

    @ParameterizedTest(name = "Check if a point is inside of a triangle. Case [{index}] Points: \"{argumentsWithNames}\"")
    @MethodSource("argumentsPointInsideTriangle")
    fun `should return TRUE when a point is inside of a triangle`(
        trianglePointA: PointTO,
        trianglePointB: PointTO,
        trianglePointC: PointTO,
        point: PointTO
    ) {
        assertTrue(trigonometryUseCase.isInsideTriangle(trianglePointA, trianglePointB, trianglePointC, point))
    }

    @ParameterizedTest(name = "Check if a point is NOT inside of a triangle. Case [{index}] Points: \"{argumentsWithNames}\"")
    @MethodSource("argumentsPointNotInsideTriangle")
    fun `should return FALSE when a point is not inside of a triangle`(
        trianglePointA: PointTO,
        trianglePointB: PointTO,
        trianglePointC: PointTO,
        point: PointTO
    ) {
        assertFalse(trigonometryUseCase.isInsideTriangle(trianglePointA, trianglePointB, trianglePointC, point))
    }

    companion object {
        @JvmStatic
        fun argumentsAlignedPoints(): Stream<Arguments> =
            Stream.of(
                Arguments { arrayOf(PointTO(1.0, 1.0), PointTO(1.0, 2.0), PointTO(1.0, 3.0)) },
                Arguments { arrayOf(PointTO(5.0, 6.0), PointTO(6.0, 8.0), PointTO(7.0, 10.0)) },
                Arguments { arrayOf(PointTO(-1.0, -1.0), PointTO(-1.0, -2.0), PointTO(-1.0, -3.0)) },
                Arguments { arrayOf(PointTO(1.0, 0.0), PointTO(2.0, 0.0), PointTO(3.0, 0.0)) }

            )

        @JvmStatic
        fun argumentsNotAlignedPoints(): Stream<Arguments> =
            Stream.of(
                Arguments { arrayOf(PointTO(0.0, 0.0), PointTO(1.0, 2.0), PointTO(-1.0, -3.0)) },
                Arguments { arrayOf(PointTO(25.0, 9.0), PointTO(1.0, 75.0), PointTO(3.0, 1.0)) },
                Arguments { arrayOf(PointTO(-99.0, 0.0), PointTO(-1.0, 0.0), PointTO(-1.0, 5.0)) }
            )

        @JvmStatic
        fun argumentsPointInsideTriangle(): Stream<Arguments> =
            Stream.of(
                Arguments { arrayOf(PointTO(1.0, 0.0), PointTO(2.0, 2.0), PointTO(-1.0, 2.0), PointTO(1.0, 1.5)) }
            )

        @JvmStatic
        fun argumentsPointNotInsideTriangle(): Stream<Arguments> =
            Stream.of(
                Arguments { arrayOf(PointTO(-99.0, 0.0), PointTO(-1.0, 0.0), PointTO(-1.0, 5.0), PointTO(5.0, 2.0)) }
            )
    }

}