package com.chubanova.grpc

import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService

@GRpcService
class TimerService extends TimerServiceGrpc.TimerServiceImplBase{
  override def addTimes(request: AddTimes, responseObserver: StreamObserver[Response]): Unit = super.addTimes(request, responseObserver)

  override def startTimer(request: StartTimer, responseObserver: StreamObserver[Response]): Unit = super.startTimer(request, responseObserver)

  override def watchStatisticFilter(request: WatchStatisticFilter, responseObserver: StreamObserver[Statistics]): Unit = super.watchStatisticFilter(request, responseObserver)
}
