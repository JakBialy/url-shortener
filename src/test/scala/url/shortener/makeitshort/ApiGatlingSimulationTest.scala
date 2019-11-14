package url.shortener.makeitshort

import java.util.concurrent.TimeUnit

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._

import scala.concurrent.duration.Duration

class ApiGatlingSimulationTest extends Simulation {

  object EnterWebsite {
    val enter: ChainBuilder = exec(http("Go into home page")
          .get("http://localhost:8080")
          .check(status.is(200))
      ).pause(Duration.apply(1000, TimeUnit.MILLISECONDS))
  }

  object InsertUrl {
    val insertUrl: ChainBuilder = exec(
            http("insert url")
              .post("http://localhost:8080/createUrl")
              .formParamMap(Map("url" -> "https://www.baeldung.com/spring-boot-favicon",
                "typeOfEngine" -> "Base64Engine"))
              .check(status.is(200))
          ).pause(Duration.apply(500, TimeUnit.MILLISECONDS))
  }

  val scn: ScenarioBuilder = scenario("Enter Website and insert URL")
    .repeat(20) {
      exec(EnterWebsite.enter, InsertUrl.insertUrl)
    }

  setUp(
    scn.inject(rampUsers(50).during(20))
  )



}