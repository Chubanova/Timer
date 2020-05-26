package com.chubanova.repository

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging
import org.mongodb.scala.MongoClient
import org.springframework.context.annotation.{Bean, Configuration}

import scala.concurrent.ExecutionContext.Implicits.global

@Configuration
class RepositoryConf extends LazyLogging{
  @Bean
  def conf(): TimerRepository = {

    val uri = ConfigFactory.load().getString("database.uri")
    val name = ConfigFactory.load().getString("database.name")
    val collectionName = ConfigFactory.load().getString("database.timer")

    val client: MongoClient = MongoClient(uri)
    val dbBlocks  = client.getDatabase(name)
    dbBlocks.createCollection(collectionName).toFuture().foreach {
      _ => logger.info("Database initialized")
    }

    new TimerRepository(dbBlocks)
  }
}

