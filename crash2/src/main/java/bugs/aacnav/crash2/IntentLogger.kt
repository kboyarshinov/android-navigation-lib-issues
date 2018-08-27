package bugs.aacnav.crash2

import android.content.ComponentName
import android.content.Intent
import android.os.BadParcelableException
import android.os.Bundle
import android.util.Log
import java.util.*

object IntentLogger {
    private val FLAGS = HashMap<Int, String>()

    init {
        FLAGS[Intent.FLAG_ACTIVITY_CLEAR_TASK] = "FLAG_ACTIVITY_CLEAR_TASK"
        FLAGS[Intent.FLAG_ACTIVITY_SINGLE_TOP] = "FLAG_ACTIVITY_SINGLE_TOP"
        FLAGS[Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT] = "FLAG_ACTIVITY_BROUGHT_TO_FRONT"
        FLAGS[Intent.FLAG_ACTIVITY_CLEAR_TOP] = "FLAG_ACTIVITY_CLEAR_TOP"
        FLAGS[Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS] = "FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS"
        FLAGS[Intent.FLAG_ACTIVITY_FORWARD_RESULT] = "FLAG_ACTIVITY_FORWARD_RESULT"
        FLAGS[Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY] = "FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY"
        FLAGS[Intent.FLAG_ACTIVITY_MULTIPLE_TASK] = "FLAG_ACTIVITY_MULTIPLE_TASK"
        FLAGS[Intent.FLAG_ACTIVITY_NEW_DOCUMENT] = "FLAG_ACTIVITY_NEW_DOCUMENT"
        FLAGS[Intent.FLAG_ACTIVITY_NEW_TASK] = "FLAG_ACTIVITY_NEW_TASK"
        FLAGS[Intent.FLAG_ACTIVITY_NO_ANIMATION] = "FLAG_ACTIVITY_NO_ANIMATION"
        FLAGS[Intent.FLAG_ACTIVITY_NO_HISTORY] = "FLAG_ACTIVITY_NO_HISTORY"
        FLAGS[Intent.FLAG_ACTIVITY_NO_USER_ACTION] = "FLAG_ACTIVITY_NO_USER_ACTION"
        FLAGS[Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP] = "FLAG_ACTIVITY_PREVIOUS_IS_TOP"
        FLAGS[Intent.FLAG_ACTIVITY_REORDER_TO_FRONT] = "FLAG_ACTIVITY_REORDER_TO_FRONT"
        FLAGS[Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED] = "FLAG_ACTIVITY_RESET_TASK_IF_NEEDED"
        FLAGS[Intent.FLAG_ACTIVITY_RETAIN_IN_RECENTS] = "FLAG_ACTIVITY_RETAIN_IN_RECENTS"
        FLAGS[Intent.FLAG_ACTIVITY_TASK_ON_HOME] = "FLAG_ACTIVITY_TASK_ON_HOME"
        FLAGS[Intent.FLAG_DEBUG_LOG_RESOLUTION] = "FLAG_DEBUG_LOG_RESOLUTION"
        FLAGS[Intent.FLAG_EXCLUDE_STOPPED_PACKAGES] = "FLAG_EXCLUDE_STOPPED_PACKAGES"
        FLAGS[Intent.FLAG_FROM_BACKGROUND] = "FLAG_FROM_BACKGROUND"
        FLAGS[Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION] = "FLAG_GRANT_PERSISTABLE_URI_PERMISSION"
        FLAGS[Intent.FLAG_GRANT_PREFIX_URI_PERMISSION] = "FLAG_GRANT_PREFIX_URI_PERMISSION"
        FLAGS[Intent.FLAG_GRANT_READ_URI_PERMISSION] = "FLAG_GRANT_READ_URI_PERMISSION"
        FLAGS[Intent.FLAG_GRANT_WRITE_URI_PERMISSION] = "FLAG_GRANT_WRITE_URI_PERMISSION"
        FLAGS[Intent.FLAG_INCLUDE_STOPPED_PACKAGES] = "FLAG_INCLUDE_STOPPED_PACKAGES"
        FLAGS[Intent.FLAG_RECEIVER_NO_ABORT] = "FLAG_RECEIVER_NO_ABORT"
        FLAGS[Intent.FLAG_RECEIVER_REGISTERED_ONLY] = "FLAG_RECEIVER_REGISTERED_ONLY"
        FLAGS[Intent.FLAG_RECEIVER_REPLACE_PENDING] = "FLAG_RECEIVER_REPLACE_PENDING"
        FLAGS[Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET] = "FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET"
    }

    fun dump(tag: String, intent: Intent?) {
        if (intent == null) {
            Log.v(tag, "no intent found")
            return
        }
        val extras = intent.extras
        Log.v(tag, "Intent[@" + Integer.toHexString(intent.hashCode()) + "] content:")
        Log.v(tag, "Action   : " + intent.action)
        Log.v(tag, "Category : " + intent.categories)
        Log.v(tag, "Data     : " + intent.dataString)
        dumpComponentName(tag, intent.component)
        dumpFlags(tag, intent.flags)
        Log.v(tag, "HasExtras: " + hasExtras(extras))
        dumpExtras(tag, extras)
    }

    fun dumpComponentName(tag: String, componentName: ComponentName?) {
        if (componentName != null)
            Log.v(tag, "Component: " + componentName.packageName + "/" + componentName.className)
        else
            Log.v(tag, "Component: null")
    }

    fun dumpFlags(tag: String, flags: Int) {
        Log.v(tag, "Flags    : " + Integer.toBinaryString(flags))
        for (flag in FLAGS.keys) {
            if (flag and flags != 0) {
                Log.v(tag, "Flag     : " + FLAGS[flag])
            }
        }
    }

    fun dumpExtras(tag: String, extras: Bundle?) {
        if (extras == null) {
            return
        }
        for (key in extras.keySet()) {
            val value = extras.get(key)
            if (value is Bundle) {
                dumpExtras(tag, value)
            } else {
                try {
                    Log.v(tag, "Extra[" + key + "] :" + extras.get(key).toString())
                } catch (e: BadParcelableException) {
                    Log.w(tag, "Extra contains unknown class instance for [$key]: ", e)
                }

            }
        }
    }

    fun hasExtras(extras: Bundle?): Boolean {
        try {
            return extras != null && !extras.isEmpty
        } catch (e: BadParcelableException) {
            Log.w("IntentLogger", "Extra contains unknown class instance: ", e)
            return true
        }

    }
}