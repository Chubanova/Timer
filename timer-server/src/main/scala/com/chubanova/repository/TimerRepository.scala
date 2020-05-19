package com.chubanova.repository

import java.time.LocalDateTime
import java.util.Date

import ch.rasc.bsoncodec.time.ZonedDateTimeStringCodec
import com.chubanova.grpc.WatchStatisticFilter
import com.google.protobuf.Timestamp
import org.mongodb.scala.MongoDatabase
import org.mongodb.scala.bson.collection.immutable.Document

import scala.concurrent.ExecutionContext

class TimerRepository(val mongoDatabase: MongoDatabase) {
  import org.bson.codecs.configuration.CodecRegistries
  import org.bson.codecs.configuration.CodecRegistries._

  import ch.rasc.bsoncodec.math._
  import ch.rasc.bsoncodec.time._
  import org.bson.codecs.configuration.CodecRegistries
  import org.bson.codecs.configuration.CodecRegistries._
  import org.mongodb.scala.bson.codecs.DEFAULT_CODEC_REGISTRY
  import org.mongodb.scala.bson.codecs.Macros._
//
//  private val customCodecs = fromProviders(
//    classOf[ZonedDateTimeStringCodec]
//  )
//
//  private val javaCodecs = CodecRegistries.fromCodecs(
//    new LocalDateTime()
//)

//  private val codecRegistry = fromRegistries(customCodecs, javaCodecs, DEFAULT_CODEC_REGISTRY)

  val spendTimes = mongoDatabase.getCollection("spendTimes")
//    .withCodecRegistry(codecRegistry)

  def addTime(times: Timestamp, project: String, subproject: String)(implicit executionContext: ExecutionContext) ={

    val timesData = Document(
      "times" -> times.getNanos,
      "project" -> project,
      "subproject" -> subproject,
      "updated" -> new Date()
    )

    spendTimes.insertOne(timesData).toFuture()
  }

  def startTimer(start: Int, project: String, subproject: String) = ???

  def watchStatistic(period: Int, project: Int, projectList: Array[WatchStatisticFilter.Project], from: Timestamp, to: Timestamp) = ???

}
