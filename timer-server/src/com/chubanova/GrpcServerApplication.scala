package com.chubanova

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.{EnableAutoConfiguration, SpringBootApplication}
import org.springframework.boot.autoconfigure.jdbc.{DataSourceAutoConfiguration, DataSourceTransactionManagerAutoConfiguration}
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration

@SpringBootApplication
@EnableAutoConfiguration(exclude = Array(classOf[DataSourceAutoConfiguration],
  classOf[DataSourceTransactionManagerAutoConfiguration],
  classOf[HibernateJpaAutoConfiguration]))
class GrpcServerApplication {
}

object GrpcServerApplication extends App {
  SpringApplication.run(classOf[GrpcServerApplication], args: _*)
}
