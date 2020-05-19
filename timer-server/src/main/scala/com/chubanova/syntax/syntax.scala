package com.chubanova.syntax

import com.chubanova.model.TimeProject
import com.google.protobuf.Timestamp

import scala.language.implicitConversions

package object syntax {

   implicit def toGrpcTimer(block : TimeProject) : com.chubanova.grpc.AddTimes ={

     com.chubanova.grpc.AddTimes.newBuilder().setProject(
       com.chubanova.grpc.Project.newBuilder().setName(block.project).setSubProject(block.subProject)
     ).setTime(block.time).build()

  }


}
