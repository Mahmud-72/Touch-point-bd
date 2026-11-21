package com.example.service

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.example.R
import com.example.data.ShieldStateHolder

class TouchLockWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        appWidgetIds.forEach { widgetId ->
            appWidgetManager.updateAppWidget(widgetId, buildRemoteViews(context))
        }
    }

    companion object {
        fun updateAllWidgets(context: Context) {
            runCatching {
                val manager = AppWidgetManager.getInstance(context)
                val component = ComponentName(context, TouchLockWidgetProvider::class.java)
                val ids = manager.getAppWidgetIds(component)
                if (ids.isNotEmpty()) {
                    val views = buildRemoteViews(context)
                    ids.forEach { manager.updateAppWidget(it, views) }
                }
            }
        }

        private fun buildRemoteViews(context: Context): RemoteViews {
            val runtime = ShieldStateHolder.runtimeState.value
            val statusText = when {
                runtime.isScreenLocked -> context.getString(R.string.widget_locked)
                runtime.isServiceRunning -> context.getString(R.string.widget_armed)
                else -> context.getString(R.string.widget_tap_lock)
            }

            val clickIntent = Intent(context, ShortcutActionActivity::class.java).apply {
                action = ShortcutActionActivity.ACTION_WIDGET_LOCK
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            val pendingIntent = PendingIntent.getActivity(
                context,
                301,
                clickIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            return RemoteViews(context.packageName, R.layout.widget_touch_lock).apply {
                setTextViewText(R.id.widget_status_text, statusText)
                setOnClickPendingIntent(R.id.widget_root, pendingIntent)
            }
        }
    }
}
