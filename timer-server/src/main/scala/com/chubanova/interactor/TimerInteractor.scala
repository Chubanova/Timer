package com.chubanova.interactor

import com.chubanova.grpc.WatchStatisticFilter
import com.chubanova.repository.TimerRepository
import com.google.protobuf.Timestamp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import scala.concurrent.ExecutionContext

@Service
class TimerInteractor(@Autowired timerRepository: TimerRepository) {
  def watchStatistic(period: Int, project: Int, projectList: Array[WatchStatisticFilter.Project], from: Timestamp, to: Timestamp) = {
    timerRepository.watchStatistic(period, project, projectList, from, to)
  }

  def startTimer(start: Int, project: String, subproject: String)  = {
    timerRepository.startTimer(start, project, subproject)
  }
  def addTime(times: Long, project: String, subproject: String)(implicit executionContext: ExecutionContext) = {
    timerRepository.addTime(times, project, subproject)
  }

}
