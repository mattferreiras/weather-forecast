package br.com.weather.forecast.ml.domain.use_case

import br.com.weather.forecast.ml.domain.to.PointTO
import org.springframework.stereotype.Component
import kotlin.math.abs

@Component
class TrigonometryUseCase {

    fun isAlignPoints(pointA: PointTO, pointB: PointTO, pointC: PointTO): Boolean {
        return (calcAlignPoints(pointA, pointB, pointC) == 0.0)
    }

    fun isInsideTriangle(
        trianglePointA: PointTO,
        trianglePointB: PointTO,
        trianglePointC: PointTO,
        point: PointTO
    ): Boolean {
        val areaTriangle = calcAreaTriangle(trianglePointA, trianglePointB, trianglePointC)
        val areaTriangleA = calcAreaTriangle(point, trianglePointB, trianglePointC)
        val areaTriangleB = calcAreaTriangle(trianglePointA, point, trianglePointC)
        val areaTriangleC = calcAreaTriangle(trianglePointA, trianglePointB, point)

        return (areaTriangle == (areaTriangleA + areaTriangleB + areaTriangleC))
    }

    private fun calcAlignPoints(pointA: PointTO, pointB: PointTO, pointC: PointTO): Double {
        //Align points -- x0(y1-y2)+x1(y2-y0)+x2(y0-y1)

        return (pointA.x * (pointB.y - pointC.y) + pointB.x * (pointC.y - pointA.y) + pointC.x * (pointA.y - pointB.y))
    }

    private fun calcAreaTriangle(pointA: PointTO, pointB: PointTO, pointC: PointTO): Double {
        //Triangle area -- 1/2[x0(y1-y2)+x1(y2-y0)+x2(y0-y1)]

        return abs(calcAlignPoints(pointA, pointB, pointC) / 2)
    }

}