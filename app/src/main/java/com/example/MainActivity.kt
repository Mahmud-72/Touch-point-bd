package com.example

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.HelpOutline
import androidx.compose.material.icons.rounded.Shield
import androidx.compose.material.icons.rounded.Translate
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.MainUiState
import com.example.ui.MainViewModel
import com.example.ui.components.InAppLockSandboxDialog
import com.example.ui.components.OnboardingOverlay
import com.example.ui.getAppStrings
import com.example.ui.screens.DashboardScreen
import com.example.ui.screens.GuideScreen
import com.example.ui.screens.LanguagePickerDialog
import com.example.ui.screens.SettingsScreen
import com.example.ui.theme.TouchShieldTheme
import kotlinx.coroutines.flow.collectLatest

private enum class AppTab(val testTag: String, val icon: ImageVector) {
    DASHBOARD("tab_dashboard", Icons.Rounded.Shield),
    SETTINGS("tab_settings", Icons.Rounded.Tune),
    GUIDE("tab_guide", Icons.Rounded.HelpOutline)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainViewModel = viewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            val lifecycleOwner = LocalLifecycleOwner.current
            DisposableEffect(lifecycleOwner) {
                val observer = LifecycleEventObserver { _, event ->
                    if (event == Lifecycle.Event.ON_RESUME) {
                        viewModel.refreshPermissions()
                    }
                }
                lifecycleOwner.lifecycle.addObserver(observer)
                onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
            }

            val notificationLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission()
            ) {
                viewModel.refreshPermissions()
            }

            val phoneStateLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission()
            ) { granted ->
                viewModel.refreshPermissions()
                if (granted) {
                    viewModel.setAutoUnlockOnCall(true)
                }
            }

            val exportLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.CreateDocument("application/json")
            ) { uri ->
                if (uri != null) {
                    viewModel.exportBackupToUri(uri)
                }
            }

            val importLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.OpenDocument()
            ) { uri ->
                if (uri != null) {
                    viewModel.importBackupFromUri(uri)
                }
            }

            val layoutDirection = if (uiState.settings.appLanguage.isRtl()) {
                LayoutDirection.Rtl
            } else {
                LayoutDirection.Ltr
            }

            CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
                TouchShieldTheme(
                    themeMode = uiState.settings.themeMode,
                    dynamicColor = uiState.settings.dynamicColorEnabled
                ) {
                    TouchShieldRoot(
                        uiState = uiState,
                        onRequestOverlayPermission = { viewModel.openOverlaySettings() },
                        onRequestNotificationPermission = {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                notificationLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                            }
                        },
                        onRequestPhoneStatePermission = {
                            phoneStateLauncher.launch(Manifest.permission.READ_PHONE_STATE)
                        },
                        onExportBackup = {
                            exportLauncher.launch("touchshield-backup.json")
                        },
                        onImportBackup = {
                            importLauncher.launch(arrayOf("application/json", "text/plain", "*/*"))
                        },
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TouchShieldRoot(
    uiState: MainUiState,
    onRequestOverlayPermission: () -> Unit,
    onRequestNotificationPermission: () -> Unit,
    onRequestPhoneStatePermission: () -> Unit,
    onExportBackup: () -> Unit,
    onImportBackup: () -> Unit,
    viewModel: MainViewModel
) {
    val strings = getAppStrings(uiState.settings.appLanguage)
    var currentTab by rememberSaveable { mutableStateOf(AppTab.DASHBOARD) }
    var showLanguagePicker by rememberSaveable { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel) {
        viewModel.snackbarEvents.collectLatest { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    if (currentTab != AppTab.DASHBOARD) {
        BackHandler {
            currentTab = AppTab.DASHBOARD
        }
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isWideScreen = maxWidth >= 600.dp

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
            contentWindowInsets = WindowInsets.safeDrawing,
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Shield,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(22.dp)
                            )
                            Text(
                                text = strings.appTitle,
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    },
                    actions = {
                        Surface(
                            modifier = Modifier
                                .padding(end = 12.dp)
                                .clip(RoundedCornerShape(50))
                                .clickable { showLanguagePicker = true }
                                .testTag("top_language_toggle"),
                            shape = RoundedCornerShape(50),
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Translate,
                                    contentDescription = strings.languageLabel,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(
                                    text = uiState.settings.appLanguage.resolve().storageKey.uppercase(),
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                )
            },
            bottomBar = {
                if (!isWideScreen) {
                    NavigationBar(
                        containerColor = MaterialTheme.colorScheme.surface
                    ) {
                        AppTab.entries.forEach { tab ->
                            val label = when (tab) {
                                AppTab.DASHBOARD -> strings.tabDashboard
                                AppTab.SETTINGS -> strings.tabSettings
                                AppTab.GUIDE -> strings.tabGuide
                            }
                            NavigationBarItem(
                                selected = currentTab == tab,
                                onClick = { currentTab = tab },
                                icon = { Icon(tab.icon, contentDescription = label) },
                                label = { Text(label, maxLines = 1) },
                                modifier = Modifier.testTag(tab.testTag)
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                if (isWideScreen) {
                    NavigationRail(
                        modifier = Modifier
                            .fillMaxHeight()
                            .windowInsetsPadding(WindowInsets(0)),
                        containerColor = MaterialTheme.colorScheme.surface
                    ) {
                        Spacer(modifier = Modifier.weight(0.1f))
                        AppTab.entries.forEach { tab ->
                            val label = when (tab) {
                                AppTab.DASHBOARD -> strings.tabDashboard
                                AppTab.SETTINGS -> strings.tabSettings
                                AppTab.GUIDE -> strings.tabGuide
                            }
                            NavigationRailItem(
                                selected = currentTab == tab,
                                onClick = { currentTab = tab },
                                icon = { Icon(tab.icon, contentDescription = label) },
                                label = { Text(label) },
                                modifier = Modifier.testTag(tab.testTag)
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .widthIn(max = 680.dp)
                            .fillMaxWidth()
                    ) {
                        when (currentTab) {
                            AppTab.DASHBOARD -> DashboardScreen(
                                uiState = uiState,
                                strings = strings,
                                onToggleArmed = { viewModel.toggleServiceArmed() },
                                onLockNow = { viewModel.lockScreenNow() },
                                onOpenSandbox = { viewModel.openSandbox() },
                                onSelectPreset = { viewModel.applySmartPreset(it) },
                                onSaveCustomProfile = { viewModel.saveCurrentAsProfile(it) },
                                onApplyCustomProfile = { viewModel.applyCustomProfile(it.id) },
                                onRenameCustomProfile = { profile, newName ->
                                    viewModel.renameCustomProfile(profile.id, newName)
                                },
                                onDeleteCustomProfile = { viewModel.deleteCustomProfile(it.id) },
                                onRequestOverlayPermission = onRequestOverlayPermission,
                                onRequestNotificationPermission = onRequestNotificationPermission,
                                onTogglePocketAutoLock = { viewModel.setPocketAutoLock(it) },
                                onTogglePocketAutoUnlock = { viewModel.setPocketAutoUnlock(it) },
                                onToggleFloatingBubble = { viewModel.setFloatingBubbleEnabled(it) },
                                onToggleKeepScreenOn = { viewModel.setKeepScreenOn(it) }
                            )

                            AppTab.SETTINGS -> SettingsScreen(
                                settings = uiState.settings,
                                runtime = uiState.runtime,
                                permissions = uiState.permissions,
                                strings = strings,
                                onSelectPreset = { viewModel.applySmartPreset(it) },
                                onSaveCustomProfile = { viewModel.saveCurrentAsProfile(it) },
                                onApplyCustomProfile = { viewModel.applyCustomProfile(it.id) },
                                onRenameCustomProfile = { profile, newName ->
                                    viewModel.renameCustomProfile(profile.id, newName)
                                },
                                onDeleteCustomProfile = { viewModel.deleteCustomProfile(it.id) },
                                onUnlockMethodSelected = { viewModel.setUnlockMethod(it) },
                                onHoldDurationChanged = { viewModel.setHoldDurationMs(it) },
                                onSaveUnlockPin = { viewModel.setUnlockPin(it) },
                                onBiometricUnlockChanged = { viewModel.setBiometricUnlock(it) },
                                onEmergencyBypassChanged = { viewModel.setEmergencyHoldBypass(it) },
                                onCountdownSelected = { viewModel.setLockCountdownSeconds(it) },
                                onHudTimeoutSelected = { viewModel.setHudTimeoutMs(it) },
                                onOverlayStyleSelected = { viewModel.setOverlayStyle(it) },
                                onCustomDimChanged = { viewModel.setCustomDimPercent(it) },
                                onLockBrightnessSelected = { viewModel.setLockBrightnessPercent(it) },
                                onBorderEnabledChanged = { viewModel.setBorderEnabled(it) },
                                onBorderColorSelected = { viewModel.setBorderColor(it) },
                                onBorderThicknessSelected = { viewModel.setBorderThickness(it) },
                                onBorderStyleSelected = { viewModel.setBorderStyle(it) },
                                onBorderPulseChanged = { viewModel.setBorderPulse(it) },
                                onBadgeModeSelected = { viewModel.setBadgeVisibilityMode(it) },
                                onOledClockChanged = { viewModel.setOledClockEnabled(it) },
                                onFloatingBubbleEnabledChanged = { viewModel.setFloatingBubbleEnabled(it) },
                                onFloatingBubbleOpacityChanged = { viewModel.setFloatingBubbleOpacity(it) },
                                onBubbleSizeSelected = { viewModel.setBubbleSize(it) },
                                onBubbleStyleSelected = { viewModel.setBubbleStyle(it) },
                                onBubbleAutoTuckChanged = { viewModel.setBubbleAutoTuck(it) },
                                onBubbleDoubleTapHideChanged = { viewModel.setBubbleDoubleTapHide(it) },
                                onPocketAutoLockChanged = { viewModel.setPocketAutoLock(it) },
                                onPocketAutoUnlockChanged = { viewModel.setPocketAutoUnlock(it) },
                                onPocketDebounceChanged = { viewModel.setPocketDebounceMs(it) },
                                onOrientationFilterChanged = { viewModel.setPocketOrientationFilter(it) },
                                onPauseLandscapeChanged = { viewModel.setPausePocketInLandscape(it) },
                                onPauseChargingChanged = { viewModel.setPausePocketWhileCharging(it) },
                                onFlipFaceDownChanged = { viewModel.setFlipFaceDownLock(it) },
                                onAutoUnlockOnCallChanged = { viewModel.setAutoUnlockOnCall(it) },
                                onReLockAfterCallChanged = { viewModel.setReLockAfterCall(it) },
                                onSessionTimerSelected = { viewModel.setSessionTimerMinutes(it) },
                                onHapticsChanged = { viewModel.setHapticFeedback(it) },
                                onAutoBootChanged = { viewModel.setAutoStartOnBoot(it) },
                                onThemeModeSelected = { viewModel.setThemeMode(it) },
                                onDynamicColorChanged = { viewModel.setDynamicColorEnabled(it) },
                                onLanguageSelected = { viewModel.setAppLanguage(it) },
                                onExportBackup = onExportBackup,
                                onImportBackup = onImportBackup,
                                onRequestOverlayPermission = onRequestOverlayPermission,
                                onRequestNotificationPermission = onRequestNotificationPermission,
                                onRequestPhoneStatePermission = onRequestPhoneStatePermission,
                                onOpenBatterySettings = { viewModel.openBatterySettings() },
                                onResetStats = { viewModel.resetStats() },
                                onRestoreDefaults = { viewModel.restoreDefaults() }
                            )

                            AppTab.GUIDE -> GuideScreen(
                                strings = strings,
                                onReplayOnboarding = { viewModel.replayOnboarding() }
                            )
                        }
                    }
                }
            }
        }
    }

    if (showLanguagePicker) {
        LanguagePickerDialog(
            currentLanguage = uiState.settings.appLanguage,
            strings = strings,
            onSelectLanguage = { viewModel.setAppLanguage(it) },
            onDismiss = { showLanguagePicker = false }
        )
    }

    if (!uiState.settings.hasCompletedOnboarding) {
        OnboardingOverlay(
            strings = strings,
            onComplete = { viewModel.completeOnboarding() }
        )
    }

    if (uiState.isSandboxOpen) {
        InAppLockSandboxDialog(
            settings = uiState.settings,
            strings = strings,
            onBlockedTouch = { viewModel.recordSandboxBlockedTouch() },
            onDismiss = { viewModel.closeSandbox() }
        )
    }
}
