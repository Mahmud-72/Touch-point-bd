package com.example

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.test.core.app.ApplicationProvider
import com.example.data.AppLanguage
import com.example.data.BadgeVisibilityMode
import com.example.data.BorderColorOption
import com.example.data.BorderThickness
import com.example.data.BubbleSize
import com.example.data.BubbleStyle
import com.example.data.LockSource
import com.example.data.OverlayStyle
import com.example.data.ShieldPreferences
import com.example.data.ShieldStateHolder
import com.example.data.SmartPreset
import com.example.data.UnlockMethod
import com.example.service.BootReceiver
import com.example.service.TouchShieldService
import com.example.service.TouchShieldTileService
import com.example.ui.getAppStrings
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read app name from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("TouchShield", appName)
    }

    @Test
    fun `manifest declares required permissions and components`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val pm = context.packageManager
        val pkgInfo = pm.getPackageInfo(context.packageName, PackageManager.GET_PERMISSIONS)
        val permissions = pkgInfo.requestedPermissions?.toSet().orEmpty()

        assertTrue(permissions.contains(Manifest.permission.SYSTEM_ALERT_WINDOW))
        assertTrue(permissions.contains(Manifest.permission.FOREGROUND_SERVICE))
        assertTrue(permissions.contains(Manifest.permission.READ_PHONE_STATE))
        assertTrue(permissions.contains(Manifest.permission.RECEIVE_BOOT_COMPLETED))

        assertNotNull(pm.getServiceInfo(ComponentName(context, TouchShieldService::class.java), 0))
        assertNotNull(pm.getServiceInfo(ComponentName(context, TouchShieldTileService::class.java), 0))
        assertNotNull(pm.getReceiverInfo(ComponentName(context, BootReceiver::class.java), 0))
    }

    @Test
    fun `shield state holder tracks lock countdown bubble and orientation`() {
        ShieldStateHolder.setServiceRunning(true)
        assertTrue(ShieldStateHolder.runtimeState.value.isServiceRunning)

        ShieldStateHolder.setCountdownRemaining(3)
        assertEquals(3, ShieldStateHolder.runtimeState.value.countdownRemainingSec)

        ShieldStateHolder.setBubbleTemporarilyHidden(true)
        assertTrue(ShieldStateHolder.runtimeState.value.isBubbleTemporarilyHidden)

        ShieldStateHolder.updateSensorInfo(
            hasProximity = true,
            proximityName = "Test Prox",
            maxRangeCm = 5f,
            hasAccelerometer = true,
            accelerometerName = "Test Accel"
        )
        ShieldStateHolder.updateOrientationReading(isFlatFaceUp = true, isPocketOrientationValid = false)
        assertTrue(ShieldStateHolder.runtimeState.value.isFlatFaceUp)
        assertFalse(ShieldStateHolder.runtimeState.value.isPocketOrientationValid)

        ShieldStateHolder.setScreenLocked(true, LockSource.POCKET_SENSOR)
        assertTrue(ShieldStateHolder.runtimeState.value.isScreenLocked)
        assertEquals(0, ShieldStateHolder.runtimeState.value.countdownRemainingSec)
        assertEquals(LockSource.POCKET_SENSOR, ShieldStateHolder.runtimeState.value.lockSource)

        ShieldStateHolder.incrementSessionBlockedTouches()
        assertEquals(1, ShieldStateHolder.runtimeState.value.sessionBlockedTouches)

        ShieldStateHolder.setScreenLocked(false, LockSource.NONE)
        assertFalse(ShieldStateHolder.runtimeState.value.isScreenLocked)
        ShieldStateHolder.setServiceRunning(false)
        assertFalse(ShieldStateHolder.runtimeState.value.isBubbleTemporarilyHidden)
    }

    @Test
    fun `preferences persist all pro controls presets and restore defaults`() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val prefs = ShieldPreferences(context)

        prefs.setUnlockMethod(UnlockMethod.PIN)
        prefs.setHoldDurationMs(5000L) // should clamp to 3500L
        prefs.setUnlockPin("9876")
        prefs.setUnlockPin("ab12") // invalid length after filtering -> ignored
        prefs.setLockCountdownSeconds(5)
        prefs.setHudTimeoutMs(5000L)
        prefs.setCustomDimPercent(42)
        prefs.setBorderEnabled(true)
        prefs.setBorderColor(BorderColorOption.CYAN)
        prefs.setBorderThickness(BorderThickness.BOLD)
        prefs.setBadgeVisibilityMode(BadgeVisibilityMode.ON_TOUCH)
        prefs.setBubbleSize(BubbleSize.LARGE)
        prefs.setBubbleStyle(BubbleStyle.COMPACT_PILL)
        prefs.setBubbleAutoTuck(false)
        prefs.setBubbleDoubleTapHide(false)
        prefs.setPocketDebounceMs(1500L)
        prefs.setPocketOrientationFilter(false)
        prefs.setAutoUnlockOnCall(true)
        prefs.setSessionTimerMinutes(30)
        prefs.setAutoStartOnBoot(true)
        prefs.incrementBlockedTouches(5L)
        prefs.incrementLockSessions()

        val updated = prefs.settingsFlow.first()
        assertEquals(UnlockMethod.PIN, updated.unlockMethod)
        assertEquals(3500L, updated.holdDurationMs)
        assertEquals("9876", updated.unlockPin)
        assertEquals(5, updated.lockCountdownSeconds)
        assertEquals(5000L, updated.hudTimeoutMs)
        assertEquals(42, updated.customDimPercent)
        assertEquals(OverlayStyle.SUBTLE_TINT, updated.overlayStyle)
        assertEquals(BorderColorOption.CYAN, updated.borderColor)
        assertEquals(BorderThickness.BOLD, updated.borderThickness)
        assertEquals(BadgeVisibilityMode.ON_TOUCH, updated.badgeVisibilityMode)
        assertEquals(BubbleSize.LARGE, updated.bubbleSize)
        assertEquals(BubbleStyle.COMPACT_PILL, updated.bubbleStyle)
        assertFalse(updated.bubbleAutoTuckEnabled)
        assertFalse(updated.bubbleDoubleTapHideEnabled)
        assertEquals(1500L, updated.pocketDebounceMs)
        assertFalse(updated.pocketOrientationFilterEnabled)
        assertTrue(updated.autoUnlockOnCallEnabled)
        assertEquals(30, updated.sessionTimerMinutes)
        assertTrue(updated.autoStartOnBootEnabled)
        assertEquals(5L, updated.totalBlockedTouches)
        assertEquals(1L, updated.totalLockSessions)

        // Presets
        prefs.applySmartPreset(SmartPreset.KIDS_VIDEO)
        val kids = prefs.settingsFlow.first()
        assertEquals(0, kids.customDimPercent)
        assertEquals(3000L, kids.holdDurationMs)
        assertEquals(BadgeVisibilityMode.HIDDEN, kids.badgeVisibilityMode)
        assertFalse(kids.floatingBubbleEnabled)

        prefs.applySmartPreset(SmartPreset.BATTERY_SAVER)
        val battery = prefs.settingsFlow.first()
        assertEquals(88, battery.customDimPercent)
        assertFalse(battery.keepScreenOnEnabled)
        assertEquals(UnlockMethod.SLIDE, battery.unlockMethod)

        prefs.applySmartPreset(SmartPreset.POCKET_PHOTO)
        val photo = prefs.settingsFlow.first()
        assertEquals(0, photo.customDimPercent)
        assertTrue(photo.keepScreenOnEnabled)
        assertTrue(photo.pocketAutoLockEnabled)
        assertTrue(photo.floatingBubbleEnabled)

        // Reset Stats & Restore Defaults
        prefs.resetStats()
        val afterResetStats = prefs.settingsFlow.first()
        assertEquals(0L, afterResetStats.totalBlockedTouches)
        assertEquals(0L, afterResetStats.totalLockSessions)

        prefs.setAppLanguage(AppLanguage.ENGLISH)
        prefs.restoreDefaults()
        val restored = prefs.settingsFlow.first()
        assertEquals(AppLanguage.ENGLISH, restored.appLanguage)
        assertEquals(UnlockMethod.LONG_PRESS, restored.unlockMethod)
        assertEquals(1800L, restored.holdDurationMs)
        assertEquals("1234", restored.unlockPin)
        assertEquals(0, restored.customDimPercent)
    }

    @Test
    fun `bilingual strings resolve all enums and hints`() {
        listOf(AppLanguage.BENGALI, AppLanguage.ENGLISH).forEach { lang ->
            val strings = getAppStrings(lang)
            assertEquals("TouchShield", strings.appTitle)
            assertTrue(strings.statusLocked.isNotBlank())

            UnlockMethod.entries.forEach { method ->
                assertTrue(strings.unlockMethodTitle(method).isNotBlank())
                assertTrue(strings.unlockMethodDesc(method).isNotBlank())
                assertTrue(strings.overlayUnlockHint(method, 2500L).isNotBlank())
            }
            assertTrue(strings.overlayUnlockHint(UnlockMethod.LONG_PRESS, 2500L).contains("2.5"))

            OverlayStyle.entries.forEach { style ->
                assertTrue(strings.overlayStyleTitle(style).isNotBlank())
                assertTrue(strings.overlayStyleDesc(style).isNotBlank())
            }
            SmartPreset.entries.forEach { preset ->
                assertTrue(strings.presetTitle(preset).isNotBlank())
                assertTrue(strings.presetDesc(preset).isNotBlank())
            }
            BorderThickness.entries.forEach { t ->
                assertTrue(strings.borderThicknessLabel(t).isNotBlank())
            }
            BadgeVisibilityMode.entries.forEach { m ->
                assertTrue(strings.badgeModeLabel(m).isNotBlank())
            }
            BubbleSize.entries.forEach { s ->
                assertTrue(strings.bubbleSizeLabel(s).isNotBlank())
            }
            BubbleStyle.entries.forEach { bs ->
                assertTrue(strings.bubbleStyleLabel(bs).isNotBlank())
            }
            BorderColorOption.entries.forEach { c ->
                assertTrue(strings.borderColorName(c).isNotBlank())
            }
            LockSource.entries.filter { it != LockSource.NONE }.forEach { src ->
                assertTrue(strings.lockSourceLabel(src).isNotBlank())
            }
        }
    }

    @Test
    fun `boot receiver handles boot completed intent safely`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val receiver = BootReceiver()
        receiver.onReceive(context, Intent(Intent.ACTION_BOOT_COMPLETED))
        receiver.onReceive(context, Intent("com.example.UNRELATED"))
    }
}
