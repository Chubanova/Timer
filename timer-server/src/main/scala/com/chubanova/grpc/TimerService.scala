package com.chubanova.grpc

import com.chubanova.interactor.TimerInteractor
import io.grpc.Status
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService
import org.springframework.beans.factory.annotation.Autowired
import scala.concurrent.ExecutionContext.Implicits.global

import scala.collection.JavaConverters._
import scala.util.{Failure, Success}

@GRpcService
class TimerService(@Autowired val timerInteractor: TimerInteractor = null) extends TimerServiceGrpc.TimerServiceImplBase {

  override def addTimes(request: AddTimes, responseObserver: StreamObserver[Response]): Unit = {
    val times = request.getTime
    val project = request.getProject.getName
    val subproject = request.getProject.getSubProject
    timerInteractor.addTime(times, project, subproject).onComplete {
      case Success(Seq()) =>
        responseObserver.onNext(Response.newBuilder().build())
        responseObserver.onCompleted()
      case Success(x) =>
        responseObserver.onNext(
          Response
            .newBuilder()
            .setAllTime(x.map(t => t.time).sum)
            .setProject(Project
              .newBuilder()
              .setName(project)
              .setSubProject(subproject)
              .build())
            .build())
        responseObserver.onCompleted()
      case Failure(exception) =>
        responseObserver.onError(Status.INTERNAL.withDescription(exception.getMessage).asException())
    }
  }

  override def startTimer(request: StartTimer, responseObserver: StreamObserver[Response]): Unit = {
    val start = request.getStarterValue
    val project = request.getProject.getName
    val subproject = request.getProject.getSubProject
    timerInteractor.startTimer(start, project, subproject).onComplete {
      case Success(Seq()) =>
        responseObserver.onNext(Response.newBuilder().build())
        responseObserver.onCompleted()
      case Success(x) =>
        responseObserver.onNext(Response.newBuilder().build())
        responseObserver.onCompleted()
      case Failure(exception) =>
        responseObserver.onError(Status.INTERNAL.withDescription(exception.getMessage).asException())
    }
  }

  override def watchStatisticFilter(request: WatchStatisticFilter, responseObserver: StreamObserver[Statistics]): Unit = {
    val period: Int = request.getPeriodValue
    val project: Int = request.getProjectsValue
    val projectList = request.getProjectList.asScala.toArray
    val from = request.getFrom
    val to = request.getTo
    //    timerInteractor.watchStatistic(period, project, projectList,from, to ).onComplete {
    //      case Success(Seq()) =>
    //        responseObserver.onNext(Response.newBuilder().build())
    //        responseObserver.onCompleted()
    //      case Success(x) =>
    //        responseObserver.onNext(Response.newBuilder().build())
    //        responseObserver.onCompleted()
    //      case Failure(exception) =>
    //        responseObserver.onError(Status.INTERNAL.withDescription(exception.getMessage).asException())
    //    }
  }
}
