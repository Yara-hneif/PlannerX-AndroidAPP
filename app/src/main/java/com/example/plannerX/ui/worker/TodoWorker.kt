package com.example.plannerX.ui.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.plannerX.common.Constant.KEY_TASK
import com.example.plannerX.ui.common.NotificationHelper

class TodoWorker(
    val context: Context,
    val params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {
        NotificationHelper(context).createNotification(
            inputData.getString(KEY_TASK).toString()
        )

        return Result.success()
    }
}