package com.chubanova.model

import java.util.Date

case class TimeForProject(
                           project: String,
                           subProject: String,
                           time: Long,
                           updated: Date)

case class Timer(
                  project: String,
                  subProject: String,
                  updated: Date)


