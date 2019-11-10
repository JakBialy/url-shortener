package pl.piomin.services.gatling

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit

import io.gatling.core.structure.ScenarioBuilder

class ApiGatlingSimulationTest extends Simulation {

  val scn: ScenarioBuilder = scenario("addUlrs").repeat(20, "n") {
    exec(
      http("CreateCode")
        .get("http://localhost:8080/")
        .check(status.is(200))
    ).pause(Duration.apply(500, TimeUnit.MILLISECONDS))
  }

  setUp(scn.inject(atOnceUsers(5))).maxDuration(FiniteDuration.apply(10, "minutes"))



}