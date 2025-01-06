package com.techlads.cameraexample

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit

class CameraWorkManager(
    private val context: Context, workerParams: WorkerParameters
): Worker(context, workerParams, ) {

    override fun doWork(): Result {
        startRecording()
        return Result.success()
    }

    private fun startRecording() {
    }

    private fun stopRecording() {
        // Logic to stop CameraX
    }

}

fun scheduleWork(context: Context, startTime: Long, endTime: Long) {
    val startWorkRequest = OneTimeWorkRequestBuilder<CameraWorkManager>()
        .setInitialDelay(startTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS)
        .build()

    val stopWorkRequest = OneTimeWorkRequestBuilder<CameraWorkManager>()
        .setInitialDelay(endTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS)
        .build()

    WorkManager.getInstance(context)
        .beginWith(startWorkRequest)
        .then(stopWorkRequest)
        .enqueue()
}