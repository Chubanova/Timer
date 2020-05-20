package com.chubanova.repository

import java.util.Date

import com.chubanova.grpc.WatchStatisticFilter
import com.chubanova.model.TimeForProject
import com.google.protobuf.Timestamp
import org.mongodb.scala.MongoDatabase

import org.mongodb.scala.model.Filters._

import scala.concurrent.ExecutionContext

class TimerRepository(val mongoDatabase: MongoDatabase) {
  /**
   *  Needs to insert, update objects to Mongo
   */
  import org.bson.codecs.configuration.CodecRegistries._
  import org.mongodb.scala.bson.codecs.DEFAULT_CODEC_REGISTRY
  import org.mongodb.scala.bson.codecs.Macros._

  private val customCodecs = fromProviders(classOf[TimeForProject])

  private val codecRegistry = fromRegistries(customCodecs, DEFAULT_CODEC_REGISTRY)

  val spendTimes = mongoDatabase.getCollection[TimeForProject]("spendTimes")// this need to insert scala objects not BSON Document
    .withCodecRegistry(codecRegistry)

  def addTime(times: Long, project: String, subproject: String)(implicit executionContext: ExecutionContext) ={
    val timesData = TimeForProject(project,subproject,times, new Date())
    spendTimes.insertOne(timesData).toFuture()

    findAllTimesByProject(project, subproject)
  }


  def startTimer(start: Int, project: String, subproject: String) = ???

  def watchStatistic(period: Int, project: Int, projectList: Array[WatchStatisticFilter.Project], from: Timestamp, to: Timestamp) = ???


  private def findAllTimesByProject(project: String, subproject: String)={

//    spendTimes.aggregate(Seq(Aggregates.group("$project",
//      Accumulators.sum("times", 1)))).toFuture() //TODO Make normal sum
    spendTimes.find(and(equal("project", project), equal("subProject", subproject))).toFuture()

  }
}
