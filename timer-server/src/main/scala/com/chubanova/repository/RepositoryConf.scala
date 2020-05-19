package com.chubanova.repository

import com.typesafe.config.ConfigFactory
import org.mongodb.scala.MongoClient
import org.springframework.context.annotation.{Bean, Configuration}
import scala.concurrent.ExecutionContext.Implicits.global

@Configuration
class RepositoryConf {
  @Bean
  def conf(): TimerRepository = {

    val uri = ConfigFactory.load().getString("database.uri")
    val asyncType = ConfigFactory.load().getString("database.type")
    val name = ConfigFactory.load().getString("database.name")

    val client: MongoClient = MongoClient(uri)
    val dbBlocks  = client.getDatabase(name)
    dbBlocks.createCollection("timer").toFuture().foreach {
      _ => System.out.println("Database initialized")
    }

    new TimerRepository(dbBlocks)
  }
}

