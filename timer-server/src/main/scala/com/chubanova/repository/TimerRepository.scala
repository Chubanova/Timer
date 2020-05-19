package com.chubanova.repository

import java.util.Date

import com.chubanova.grpc.WatchStatisticFilter
import com.google.protobuf.Timestamp
import org.mongodb.scala.MongoDatabase
import org.mongodb.scala.bson.collection.immutable.Document
import org.mongodb.scala.model.Filters._

import scala.concurrent.ExecutionContext

class TimerRepository(val mongoDatabase: MongoDatabase) {

  val spendTimes = mongoDatabase.getCollection("spendTimes")

  def addTime(times: Long, project: String, subproject: String)(implicit executionContext: ExecutionContext) ={

    val timesData = Document(
      "times" -> times,
      "project" -> project,
      "subproject" -> subproject,
      "updated" -> new Date()
    )

    spendTimes.insertOne(timesData).toFuture()
    findAllTimesByProject(project, subproject)
  }


  def startTimer(start: Int, project: String, subproject: String) = ???

  def watchStatistic(period: Int, project: Int, projectList: Array[WatchStatisticFilter.Project], from: Timestamp, to: Timestamp) = ???


  private def findAllTimesByProject(project: String, subproject: String)={
    spendTimes.find(and(equal("project",project), equal("subproject", subproject)))
  }
}
