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
import com.example.data.BorderStyle
import com.example.data.BorderThickness
import com.example.data.BubbleSize
import com.example.data.BubbleStyle
import com.example.data.LockSource
import com.example.data.OverlayStyle
import com.example.data.SettingsSection
import com.example.data.ShieldPreferences
import com.example.data.ShieldStateHolder
import com.example.data.SmartPreset
import com.example.data.ThemeMode
import com.example.data.UnlockMethod
import com.example.service.BiometricUnlockActivity
import com.example.service.BootReceiver
import com.example.service.ShortcutActionActivity
import com.example.service.TouchLockWidgetProvider
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
    fun `manifest declares required permissions components and stays offline without INTERNET`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val pm = context.packageManager
        val pkgInfo = pm.getPackageInfo(context.packageName, PackageManager.GET_PERMISSIONS)
        val permissions = pkgInfo.requestedPermissions?.toSet().orEmpty()

        assertTrue(permissions.contains(Manifest.permission.SYSTEM_ALERT_WINDOW))
        assertTrue(permissions.contains(Manifest.permission.FOREGROUND_SERVICE))
        assertTrue(permissions.contains(Manifest.permission.READ_PHONE_STATE))
        assertTrue(permissions.contains(Manifest.permission.RECEIVE_BOOT_COMPLETED))
        assertTrue(permissions.contains(Manifest.permission.USE_BIOMETRIC))
        // Privacy guarantee: no INTERNET permission
        assertFalse(permissions.contains(Manifest.permission.INTERNET))

        assertNotNull(pm.getServiceInfo(ComponentName(context, TouchShieldService::class.java), 0))
        assertNotNull(pm.getServiceInfo(ComponentName(context, TouchShieldTileService::class.java), 0))
        assertNotNull(pm.getReceiverInfo(ComponentName(context, BootReceiver::class.java), 0))
        assertNotNull(pm.getReceiverInfo(ComponentName(context, TouchLockWidgetProvider::class.java), 0))
        assertNotNull(pm.getActivityInfo(ComponentName(context, BiometricUnlockActivity::class.java), 0))
        assertNotNull(pm.getActivityInfo(ComponentName(context, ShortcutActionActivity::class.java), 0))
    }

    @Test
    fun `shield state holder tracks lock countdown bubble orientation and device context`() {
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
        ShieldStateHolder.updateOrientationReading(
            isFlatFaceUp = true,
            isFaceDown = false,
            isPocketOrientationValid = false
        )
        assertTrue(ShieldStateHolder.runtimeState.value.isFlatFaceUp)
        assertFalse(ShieldStateHolder.runtimeState.value.isFaceDown)
        assertFalse(ShieldStateHolder.runtimeState.value.isPocketOrientationValid)

        ShieldStateHolder.updateOrientationReading(
            isFlatFaceUp = false,
            isFaceDown = true,
            isPocketOrientationValid = true
        )
        assertTrue(ShieldStateHolder.runtimeState.value.isFaceDown)

        ShieldStateHolder.updateDeviceContext(isLandscape = true, isCharging = true, batteryPercent = 77)
        assertTrue(ShieldStateHolder.runtimeState.value.isLandscape)
        assertTrue(ShieldStateHolder.runtimeState.value.isCharging)
        assertEquals(77, ShieldStateHolder.runtimeState.value.batteryPercent)

        ShieldStateHolder.setScreenLocked(true, LockSource.FLIP_GESTURE)
        assertTrue(ShieldStateHolder.runtimeState.value.isScreenLocked)
        assertEquals(0, ShieldStateHolder.runtimeState.value.countdownRemainingSec)
        assertEquals(LockSource.FLIP_GESTURE, ShieldStateHolder.runtimeState.value.lockSource)

        ShieldStateHolder.incrementSessionBlockedTouches()
        assertEquals(1, ShieldStateHolder.runtimeState.value.sessionBlockedTouches)

        ShieldStateHolder.setScreenLocked(false, LockSource.NONE)
        assertFalse(ShieldStateHolder.runtimeState.value.isScreenLocked)
        ShieldStateHolder.setServiceRunning(false)
        assertFalse(ShieldStateHolder.runtimeState.value.isBubbleTemporarilyHidden)
    }

    @Test
    fun `preferences persist all international settings custom profiles backup json and restore defaults`() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val prefs = ShieldPreferences(context)

        prefs.setUnlockMethod(UnlockMethod.PIN)
        prefs.setHoldDurationMs(5000L) // clamp to 3500L
        prefs.setUnlockPin("9876")
        prefs.setUnlockPin("ab12") // invalid -> ignored
        prefs.setBiometricUnlock(false)
        prefs.setEmergencyHoldBypass(false)
        prefs.setLockCountdownSeconds(5)
        prefs.setHudTimeoutMs(5000L)
        prefs.setCustomDimPercent(42)
        prefs.setLockBrightnessPercent(100)
        prefs.setBorderEnabled(true)
        prefs.setBorderColor(BorderColorOption.CYAN)
        prefs.setBorderThickness(BorderThickness.BOLD)
        prefs.setBorderStyle(BorderStyle.CORNER_BRACKETS)
        prefs.setBorderPulse(false)
        prefs.setBadgeVisibilityMode(BadgeVisibilityMode.ON_TOUCH)
        prefs.setOledClockEnabled(true)
        prefs.setBubbleSize(BubbleSize.LARGE)
        prefs.setBubbleStyle(BubbleStyle.COMPACT_PILL)
        prefs.setBubbleAutoTuck(false)
        prefs.setBubbleDoubleTapHide(false)
        prefs.setPocketDebounceMs(1500L)
        prefs.setPocketOrientationFilter(false)
        prefs.setPausePocketInLandscape(false)
        prefs.setPausePocketWhileCharging(true)
        prefs.setFlipFaceDownLock(true)
        prefs.setAutoUnlockOnCall(true)
        prefs.setReLockAfterCall(true)
        prefs.setSessionTimerMinutes(30)
        prefs.setAutoStartOnBoot(true)
        prefs.setThemeMode(ThemeMode.LIGHT)
        prefs.setDynamicColorEnabled(false)
        prefs.setAppLanguage(AppLanguage.ARABIC)
        prefs.setOnboardingCompleted(true)
        prefs.incrementBlockedTouches(5L)
        prefs.incrementLockSessions(LockSource.WIDGET)

        val updated = prefs.settingsFlow.first()
        assertEquals(UnlockMethod.PIN, updated.unlockMethod)
        assertEquals(3500L, updated.holdDurationMs)
        assertEquals("9876", updated.unlockPin)
        assertFalse(updated.biometricUnlockEnabled)
        assertFalse(updated.emergencyHoldBypassEnabled)
        assertEquals(5, updated.lockCountdownSeconds)
        assertEquals(5000L, updated.hudTimeoutMs)
        assertEquals(42, updated.customDimPercent)
        assertEquals(100, updated.lockBrightnessPercent)
        assertEquals(OverlayStyle.SUBTLE_TINT, updated.overlayStyle)
        assertEquals(BorderColorOption.CYAN, updated.borderColor)
        assertEquals(BorderThickness.BOLD, updated.borderThickness)
        assertEquals(BorderStyle.CORNER_BRACKETS, updated.borderStyle)
        assertFalse(updated.borderPulseEnabled)
        assertEquals(BadgeVisibilityMode.ON_TOUCH, updated.badgeVisibilityMode)
        assertTrue(updated.oledClockEnabled)
        assertEquals(BubbleSize.LARGE, updated.bubbleSize)
        assertEquals(BubbleStyle.COMPACT_PILL, updated.bubbleStyle)
        assertFalse(updated.bubbleAutoTuckEnabled)
        assertFalse(updated.bubbleDoubleTapHideEnabled)
        assertEquals(1500L, updated.pocketDebounceMs)
        assertFalse(updated.pocketOrientationFilterEnabled)
        assertFalse(updated.pausePocketInLandscape)
        assertTrue(updated.pausePocketWhileCharging)
        assertTrue(updated.flipFaceDownLockEnabled)
        assertTrue(updated.autoUnlockOnCallEnabled)
        assertTrue(updated.reLockAfterCallEnabled)
        assertEquals(30, updated.sessionTimerMinutes)
        assertTrue(updated.autoStartOnBootEnabled)
        assertEquals(ThemeMode.LIGHT, updated.themeMode)
        assertFalse(updated.dynamicColorEnabled)
        assertEquals(AppLanguage.ARABIC, updated.appLanguage)
        assertTrue(updated.hasCompletedOnboarding)
        assertEquals(5L, updated.totalBlockedTouches)
        assertEquals(1L, updated.totalLockSessions)
        assertEquals(1L, updated.lockSourceCounts[LockSource.WIDGET.name])

        val weekEntries = ShieldPreferences.buildLast7DayEntries(updated.dailyBlockedHistory)
        assertEquals(7, weekEntries.size)
        assertEquals(5L, weekEntries.last().blockedTouches)

        // Custom Profiles CRUD
        prefs.saveCurrentAsProfile("Reading Night")
        val afterSaveProfile = prefs.settingsFlow.first()
        assertEquals(1, afterSaveProfile.customProfiles.size)
        val createdProfile = afterSaveProfile.customProfiles.first()
        assertEquals("Reading Night", createdProfile.name)
        assertEquals(42, createdProfile.customDimPercent)

        prefs.renameCustomProfile(createdProfile.id, "Reading Pro")
        assertEquals("Reading Pro", prefs.settingsFlow.first().customProfiles.first().name)

        // Export JSON backup before changing presets
        val exportedJson = prefs.exportSettingsJson()
        assertTrue(exportedJson.contains("touchshield_backup_v1"))

        // Presets (including NAVIGATION_MAP)
        prefs.applySmartPreset(SmartPreset.NAVIGATION_MAP)
        val nav = prefs.settingsFlow.first()
        assertEquals(0, nav.customDimPercent)
        assertEquals(100, nav.lockBrightnessPercent)
        assertFalse(nav.pocketAutoLockEnabled)
        assertEquals(BubbleStyle.COMPACT_PILL, nav.bubbleStyle)

        prefs.applySmartPreset(SmartPreset.KIDS_VIDEO)
        val kids = prefs.settingsFlow.first()
        assertEquals(3000L, kids.holdDurationMs)
        assertEquals(BorderStyle.CORNER_BRACKETS, kids.borderStyle)
        assertFalse(kids.floatingBubbleEnabled)

        prefs.applySmartPreset(SmartPreset.BATTERY_SAVER)
        val battery = prefs.settingsFlow.first()
        assertEquals(88, battery.customDimPercent)
        assertEquals(5, battery.lockBrightnessPercent)
        assertTrue(battery.oledClockEnabled)

        prefs.applySmartPreset(SmartPreset.POCKET_PHOTO)
        val photo = prefs.settingsFlow.first()
        assertEquals(0, photo.customDimPercent)
        assertEquals(-1, photo.lockBrightnessPercent)

        // Apply custom profile back
        prefs.applyCustomProfile(createdProfile.id)
        val reapplied = prefs.settingsFlow.first()
        assertEquals(42, reapplied.customDimPercent)
        assertEquals(UnlockMethod.PIN, reapplied.unlockMethod)

        // Delete custom profile
        prefs.deleteCustomProfile(createdProfile.id)
        assertTrue(prefs.settingsFlow.first().customProfiles.isEmpty())

        // Restore Defaults then Import JSON Backup
        prefs.restoreDefaults()
        assertFalse(prefs.importSettingsJson("{bad json"))
        assertTrue(prefs.importSettingsJson(exportedJson))
        val imported = prefs.settingsFlow.first()
        assertEquals(UnlockMethod.PIN, imported.unlockMethod)
        assertEquals("9876", imported.unlockPin)
        assertEquals(BorderStyle.CORNER_BRACKETS, imported.borderStyle)
        assertEquals(1, imported.customProfiles.size)

        // Reset Stats & Restore Defaults
        prefs.resetStats()
        val afterResetStats = prefs.settingsFlow.first()
        assertEquals(0L, afterResetStats.totalBlockedTouches)
        assertEquals(0L, afterResetStats.totalLockSessions)
        assertTrue(afterResetStats.lockSourceCounts.isEmpty())

        prefs.setAppLanguage(AppLanguage.ENGLISH)
        prefs.restoreDefaults()
        val restored = prefs.settingsFlow.first()
        assertEquals(AppLanguage.ENGLISH, restored.appLanguage)
        assertEquals(UnlockMethod.LONG_PRESS, restored.unlockMethod)
        assertEquals(1800L, restored.holdDurationMs)
        assertEquals("1234", restored.unlockPin)
        assertEquals(0, restored.customDimPercent)
        assertEquals(ThemeMode.SYSTEM, restored.themeMode)
    }

    @Test
    fun `all 7 languages resolve completely including RTL arabic and auto detection`() {
        assertTrue(AppLanguage.ARABIC.isRtl())
        assertTrue(AppLanguage.SYSTEM_AUTO.isRtl("ar"))
        assertFalse(AppLanguage.ENGLISH.isRtl())
        assertEquals(AppLanguage.INDONESIAN, AppLanguage.SYSTEM_AUTO.resolve("id"))
        assertEquals(AppLanguage.INDONESIAN, AppLanguage.SYSTEM_AUTO.resolve("in"))
        assertEquals(AppLanguage.BENGALI, AppLanguage.SYSTEM_AUTO.resolve("bn"))
        assertEquals(AppLanguage.ENGLISH, AppLanguage.SYSTEM_AUTO.resolve("fr"))

        val explicitLanguages = listOf(
            AppLanguage.ENGLISH,
            AppLanguage.BENGALI,
            AppLanguage.HINDI,
            AppLanguage.ARABIC,
            AppLanguage.SPANISH,
            AppLanguage.INDONESIAN,
            AppLanguage.PORTUGUESE
        )

        explicitLanguages.forEach { lang ->
            val strings = getAppStrings(lang)
            assertEquals("TouchShield", strings.appTitle)
            assertTrue(strings.statusLocked.isNotBlank())
            assertTrue(strings.onboardingPage1Title.isNotBlank())
            assertTrue(strings.analyticsTitle.isNotBlank())
            assertTrue(strings.privacyCardTitle.isNotBlank())
            assertTrue(strings.btnExportBackup.isNotBlank())
            assertTrue(strings.btnImportBackup.isNotBlank())
            assertTrue(strings.btnCustomize.isNotBlank())
            assertTrue(strings.statScreenClarity.isNotBlank())
            assertTrue(strings.dashSectionOverview.isNotBlank())
            assertTrue(strings.dashSectionQuickControls.isNotBlank())
            assertTrue(strings.dashSectionProfiles.isNotBlank())
            assertTrue(strings.dashSectionInsights.isNotBlank())
            assertTrue(strings.systemReadyBadge.isNotBlank())
            assertTrue(strings.dashQuickStyleLabel.isNotBlank())
            assertTrue(strings.dashFlipRuleActiveBadge.isNotBlank())
            assertEquals(5, SettingsSection.entries.size)

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
            BorderStyle.entries.forEach { bs ->
                assertTrue(strings.borderStyleLabel(bs).isNotBlank())
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
            ThemeMode.entries.forEach { tm ->
                assertTrue(strings.themeModeLabel(tm).isNotBlank())
            }
            LockSource.entries.filter { it != LockSource.NONE }.forEach { src ->
                assertTrue(strings.lockSourceLabel(src).isNotBlank())
            }
        }
    }

    @Test
    fun `boot receiver and widget updater execute safely`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val receiver = BootReceiver()
        receiver.onReceive(context, Intent(Intent.ACTION_BOOT_COMPLETED))
        receiver.onReceive(context, Intent("com.example.UNRELATED"))

        TouchLockWidgetProvider.updateAllWidgets(context)
    }
}
