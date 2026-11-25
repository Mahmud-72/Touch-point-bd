package com.example.ui

import com.example.data.SettingHelpTopic

data class FeatureHelpContent(
    val title: String,
    val whatItDoes: String,
    val exampleUse: String,
    val adviceTip: String
)

val EnglishHelpMap: Map<SettingHelpTopic, FeatureHelpContent> = mapOf(
    SettingHelpTopic.MODE_EASY_PRO to FeatureHelpContent(
        title = "Easy Mode vs. Pro Mode",
        whatItDoes = "Easy Mode shows only the most useful everyday controls. Pro Mode reveals all fine-tuning sliders, sensors, and power-user shortcuts.",
        exampleUse = "Keep Easy Mode on when you just want pocket lock, floating lock button, kids lock, or black privacy screen without clutter.",
        adviceTip = "Best for everyone: start in Easy Mode, switch to Pro Mode anytime—and Searching finds Pro settings in either mode."
    ),
    SettingHelpTopic.UNLOCK_METHOD to FeatureHelpContent(
        title = "How to Unlock Screen Touch",
        whatItDoes = "Picks the action required to unlock touch again after locking your screen.",
        exampleUse = "Use 'Hold' or 'Slide' for everyday pocket safety, or pick '4-Digit PIN' before handing your phone to kids.",
        adviceTip = "Hold-to-Unlock is the safest one-handed default against accidental pocket touches."
    ),
    SettingHelpTopic.BIOMETRIC_UNLOCK to FeatureHelpContent(
        title = "Fingerprint / Face Unlock",
        whatItDoes = "Adds a quick fingerprint or face unlock button right beside the 4-digit PIN pad.",
        exampleUse = "When kids hand your phone back, unlock immediately with your finger instead of typing your PIN.",
        adviceTip = "Great if you use PIN mode often and want fast parent access."
    ),
    SettingHelpTopic.EMERGENCY_HOLD_BYPASS to FeatureHelpContent(
        title = "5s Emergency Unlock",
        whatItDoes = "Shows a 5-second press-and-hold recovery button on the PIN screen in case you forget your PIN.",
        exampleUse = "If someone changed the PIN or you forgot it, holding the recovery button for 5 full seconds still lets you out.",
        adviceTip = "Turn this off (or enable Kids Safe-Lock) if kids shouldn't be able to hold their way out."
    ),
    SettingHelpTopic.LOCK_COUNTDOWN to FeatureHelpContent(
        title = "Delay Before Locking",
        whatItDoes = "Waits 3 or 5 seconds before blocking touches after you tap Lock, giving you time to adjust your screen.",
        exampleUse = "Tap Lock, switch to your map or photo app within 3 seconds, and the lock kicks in automatically.",
        adviceTip = "Keep 'Instant (0s)' for one-tap locking; pick 3s if you lock before switching apps."
    ),
    SettingHelpTopic.HUD_TIMEOUT to FeatureHelpContent(
        title = "Auto-Hide Unlock Bar",
        whatItDoes = "Hides the unlock prompt after a couple of seconds so your video, photo, or map looks clean and unobstructed.",
        exampleUse = "Tap anywhere on the locked screen whenever you want the unlock bar to reappear.",
        adviceTip = "3 seconds keeps the screen tidy without rushing you."
    ),
    SettingHelpTopic.KIDS_SAFETY to FeatureHelpContent(
        title = "Kids Safe-Lock Mode",
        whatItDoes = "Hides the 5-second emergency unlock button and disables Shake-to-Unlock so kids cannot bypass your PIN.",
        exampleUse = "Hand your phone to a child watching cartoons—they can watch, but can't exit the app or open anything else.",
        adviceTip = "Recommended for parents and toddlers. Pair with '4-Digit PIN' unlock."
    ),
    SettingHelpTopic.WRONG_ATTEMPT_COOLDOWN to FeatureHelpContent(
        title = "Wait Timer on Wrong PIN",
        whatItDoes = "Temporarily pauses the PIN keypad (15s after 5 mistakes, 30s after 8+) if someone keeps guessing random numbers.",
        exampleUse = "Stops curious kids or friends from endlessly tapping guesses on your PIN pad.",
        adviceTip = "Keep this ON whenever you use PIN lock."
    ),
    SettingHelpTopic.KIDS_TIMER_EXPIRY to FeatureHelpContent(
        title = "Lock Screen When Timer Ends",
        whatItDoes = "When your viewing timer reaches zero, it locks the screen behind your parent PIN instead of unlocking.",
        exampleUse = "Set a 15-minute cartoon timer: once 15 minutes pass, play pauses under the lock until you enter your PIN.",
        adviceTip = "Great for managing children's screen time cleanly."
    ),
    SettingHelpTopic.OVERLAY_STYLE to FeatureHelpContent(
        title = "Locked Screen Appearance",
        whatItDoes = "Controls how dark or transparent the screen looks while touch is locked.",
        exampleUse = "Clear (0%) keeps photos and maps bright; AMOLED Dim (88%) darkens the display to save battery in your pocket.",
        adviceTip = "Use Clear for gallery/maps, or Dark Dim for long music/podcasts in your pocket."
    ),
    SettingHelpTopic.LOCK_BRIGHTNESS to FeatureHelpContent(
        title = "Screen Brightness While Locked",
        whatItDoes = "Automatically lowers or boosts your screen brightness only while TouchShield is locked, then restores it when unlocked.",
        exampleUse = "Pick 'Outdoor 100%' for bike navigation in sunlight, or 'Night 5%' to save battery while listening to videos.",
        adviceTip = "'System' leaves your phone's normal brightness untouched."
    ),
    SettingHelpTopic.EDGE_BORDER to FeatureHelpContent(
        title = "Glowing Edge Frame",
        whatItDoes = "Draws a colored border or corner marks around the screen edge so you can tell at a glance that touch is locked.",
        exampleUse = "When Clear (0%) is selected, the green corner frame reminds you why taps aren't registering.",
        adviceTip = "Corner Frame looks minimal on videos; turn it off only if you want zero visual marks."
    ),
    SettingHelpTopic.OLED_CLOCK to FeatureHelpContent(
        title = "Dim-Screen Clock & Battery",
        whatItDoes = "Shows a subtle clock and battery percentage in the center when the screen is dimmed.",
        exampleUse = "Glance at the time and battery level on a dark locked screen without waking or unlocking the phone.",
        adviceTip = "Useful with 88% Dark Dim or Privacy Black Screen."
    ),
    SettingHelpTopic.PARTIAL_TOUCH_ZONE to FeatureHelpContent(
        title = "Keep One Area Touchable",
        whatItDoes = "Leaves one chosen box (like the top video or bottom controls) clickable while locking accidental touches everywhere else.",
        exampleUse = "Keep only the YouTube player or map zoom window touchable while blocking side ads, back buttons, and edge taps.",
        adviceTip = "Best for watching videos one-handed or letting kids pause/play without leaving the video."
    ),
    SettingHelpTopic.PRIVACY_VEIL to FeatureHelpContent(
        title = "Hide Screen in Public (99% Black)",
        whatItDoes = "Turns the locked screen almost completely black so people sitting next to you can't see what's open, with a 3s Peek button.",
        exampleUse = "On a bus or train, hide your open chat, banking app, or video audio without closing it—tap 'Peek 3s' anytime to check.",
        adviceTip = "Ideal for commuters and long audio/YouTube listening in public."
    ),
    SettingHelpTopic.STEALTH_BLACKOUT to FeatureHelpContent(
        title = "Totally Hidden Lock (No Lights)",
        whatItDoes = "Hides the glowing edge border, top pill, and clock so the darkened screen looks completely turned off.",
        exampleUse = "Saves maximum screen glare in dark rooms or pockets; tap anywhere to reveal the unlock bar.",
        adviceTip = "Pair with 'Hide Screen in Public' for an ultra-discreet black shield."
    ),
    SettingHelpTopic.FLOATING_BUBBLE to FeatureHelpContent(
        title = "Floating Lock Button",
        whatItDoes = "Puts a small movable lock button over any app so you can freeze the screen with one quick tap.",
        exampleUse = "Open Maps, Gallery, or YouTube and tap the floating button whenever you want to lock touch immediately.",
        adviceTip = "Keep this ON for the easiest day-to-day locking without leaving your app."
    ),
    SettingHelpTopic.BUBBLE_AUTO_TUCK to FeatureHelpContent(
        title = "Slide Button to Edge After 3s",
        whatItDoes = "Moves the floating lock button halfway to the screen edge and makes it faint after 3 seconds of idle time.",
        exampleUse = "Prevents the floating button from blocking game controls, captions, or photo corners while you read.",
        adviceTip = "Recommended ON unless you always want the button fully visible."
    ),
    SettingHelpTopic.BUBBLE_DOUBLE_TAP_HIDE to FeatureHelpContent(
        title = "Double-Tap Button to Hide",
        whatItDoes = "Double-tap the floating button to temporarily hide it whenever it gets in your way; bring it back from the notification bar.",
        exampleUse = "Taking a screenshot or tapping a corner button under the bubble? Double-tap to tuck it away temporarily.",
        adviceTip = "Handy safety valve so the button never blocks what's underneath."
    ),
    SettingHelpTopic.POCKET_AUTO_LOCK to FeatureHelpContent(
        title = "Auto-Lock Inside Pocket",
        whatItDoes = "Uses the top earpiece sensor to automatically lock touch as soon as you slide your phone into your pocket.",
        exampleUse = "Leave a reference photo or ticket open, slip the phone in your pocket, and no cloth taps will swipe or dial.",
        adviceTip = "Turn ON for hands-free pocket protection."
    ),
    SettingHelpTopic.POCKET_AUTO_UNLOCK to FeatureHelpContent(
        title = "Auto-Unlock Out of Pocket",
        whatItDoes = "Automatically unlocks touch when you pull the phone back out into clear light (only when locked by the pocket sensor).",
        exampleUse = "Pull your phone out to view your open map or ticket immediately without holding to unlock.",
        adviceTip = "Leave OFF if you ever hand the phone to kids or want manual unlock only."
    ),
    SettingHelpTopic.POCKET_SENSITIVITY to FeatureHelpContent(
        title = "Pocket Detection Delay",
        whatItDoes = "Controls how long the top sensor must stay covered before auto-locking, preventing false triggers from quick hand waves.",
        exampleUse = "Set 1.2s+ if wiping the top bezel triggers locks too quickly; keep 0.7s for snappy pocket locking.",
        adviceTip = "Default 0.7s works smoothly for most pockets."
    ),
    SettingHelpTopic.ORIENTATION_FILTER to FeatureHelpContent(
        title = "Ignore When Flat on Table",
        whatItDoes = "Uses motion sensors to stop pocket auto-lock when the phone is lying flat face-up on a desk.",
        exampleUse = "Reach across your phone on the table to grab coffee—your hand shadow won't accidentally lock the screen.",
        adviceTip = "Strongly recommended ON to avoid desk annoyance."
    ),
    SettingHelpTopic.PAUSE_LANDSCAPE to FeatureHelpContent(
        title = "Pause During Sideways Video / Games",
        whatItDoes = "Pauses pocket auto-lock whenever you hold your phone sideways in landscape mode.",
        exampleUse = "Your thumbs won't trigger pocket lock if they brush the top speaker edge while gaming or watching a movie.",
        adviceTip = "Keep ON if you play games or watch fullscreen videos."
    ),
    SettingHelpTopic.PAUSE_CHARGING to FeatureHelpContent(
        title = "Pause Auto-Lock While Charging",
        whatItDoes = "Turns off automatic pocket locking while a charging cable is plugged in.",
        exampleUse = "Useful if your phone sits on a bedside charger stand or car dock.",
        adviceTip = "Keep OFF if you lock your screen deliberately while charging in a bag with a power bank."
    ),
    SettingHelpTopic.FLIP_FACE_DOWN to FeatureHelpContent(
        title = "Place Face-Down to Lock",
        whatItDoes = "Automatically locks touch whenever you lay the phone screen-down on a flat surface.",
        exampleUse = "Flip your phone face-down on a café table to hide and lock an active app right away.",
        adviceTip = "Great quick gesture during meetings or desk breaks."
    ),
    SettingHelpTopic.CALL_AUTO_UNLOCK to FeatureHelpContent(
        title = "Auto-Unlock for Phone Calls",
        whatItDoes = "Unlocks the screen automatically when a phone call rings (and optionally re-locks when the call finishes).",
        exampleUse = "Never miss answering an urgent call because TouchShield was blocking your swipe-to-answer button.",
        adviceTip = "Highly recommended if you carry unlocked photos or podcasts in your pocket."
    ),
    SettingHelpTopic.SESSION_TIMER to FeatureHelpContent(
        title = "Auto-Unlock Timer",
        whatItDoes = "Automatically stops locking after 15, 30, or 60 minutes (or triggers Parent PIN if Kids Timer Lock is turned on).",
        exampleUse = "Fall asleep listening to a podcast and let the lock clear automatically after 30 minutes.",
        adviceTip = "Keep 'Off' unless you want timed viewing or sleep sessions."
    ),
    SettingHelpTopic.APP_WATCH to FeatureHelpContent(
        title = "Auto-Protect Chosen Apps",
        whatItDoes = "Watches locally on your phone for apps you choose (like YouTube, Maps, Camera) and automatically shows the lock button or locks.",
        exampleUse = "Open Google Maps on your bike mount and the floating lock button pops up by itself—and vanishes when you go home.",
        adviceTip = "Uses local Usage Access only—no Accessibility service or internet."
    ),
    SettingHelpTopic.SHAKE_SHORTCUT to FeatureHelpContent(
        title = "Shake Twice to Lock / Unlock",
        whatItDoes = "Lets you lock (or unlock) touch by giving your phone two quick shakes.",
        exampleUse = "Wearing gloves outdoors or riding? Give two firm shakes to freeze screen touches without hunting for a button.",
        adviceTip = "Shake-to-Unlock automatically shuts off when Kids Safe-Lock is enabled."
    ),
    SettingHelpTopic.VOLUME_SHORTCUT to FeatureHelpContent(
        title = "Double-Press Volume Key",
        whatItDoes = "Pressing Volume Up or Down twice quickly toggles or unlocks the screen lock—works reliably inside TouchShield and supported overlays.",
        exampleUse = "Prefer hardware buttons over tapping the screen? Double-click a volume key to pop open the screen.",
        adviceTip = "Great hardware backup for wet hands or gloves."
    ),
    SettingHelpTopic.LOW_BATTERY_BLACKOUT to FeatureHelpContent(
        title = "Auto Dark-Save on Low Battery",
        whatItDoes = "Automatically darkens the locked screen to 92% black (with lower brightness) whenever battery drops below 10%, 15%, or 20%.",
        exampleUse = "On a long trip with GPS or music, TouchShield darkens itself when your battery runs low so your phone lasts longer.",
        adviceTip = "Set to 15% for effortless emergency battery stretch."
    ),
    SettingHelpTopic.CHARGER_AUTO_RULE to FeatureHelpContent(
        title = "Charger Plug / Unplug Rules",
        whatItDoes = "Automatically locks touch when a charger cable is plugged in, or unlocks when the cable is unplugged.",
        exampleUse = "Plug your phone into a car charger mount to keep the map locked safely, and auto-unlock when you unplug on arrival.",
        adviceTip = "Handy for car mounts and power banks."
    ),
    SettingHelpTopic.KEEP_SCREEN_ON to FeatureHelpContent(
        title = "Prevent Screen From Sleeping",
        whatItDoes = "Keeps your display awake while locked so your photo, QR ticket, recipe, or map doesn't time out and turn off.",
        exampleUse = "Show a boarding pass or grocery list at checkout without having to keep tapping the screen to keep it lit.",
        adviceTip = "Turn OFF only when using dark audio/pocket mode to save maximum battery."
    ),
    SettingHelpTopic.HAPTICS_AND_BOOT to FeatureHelpContent(
        title = "Vibration & Auto-Start on Reboot",
        whatItDoes = "Gives a gentle buzz when locking/unlocking, and can prepare protection automatically whenever your phone restarts.",
        exampleUse = "Feel a clear buzz in your pocket confirming your touch lock actually turned on.",
        adviceTip = "Keep Vibration ON for instant confidence without looking."
    ),
    SettingHelpTopic.BACKUP_RESTORE to FeatureHelpContent(
        title = "Save or Restore Settings File",
        whatItDoes = "Saves all your preferences and custom profiles into a small offline file (.json) that you can reload anytime.",
        exampleUse = "Switching phones or reinstalling? Import your saved file to get every profile and setting back in 2 seconds.",
        adviceTip = "100% offline—your settings stay only in the file you save."
    )
)

val BengaliHelpMap: Map<SettingHelpTopic, FeatureHelpContent> = mapOf(
    SettingHelpTopic.MODE_EASY_PRO to FeatureHelpContent(
        title = "সহজ মোড বনাম প্রো মোড",
        whatItDoes = "সহজ মোডে শুধু প্রতিদিনের দরকারি ও সহজ বাটনগুলো দেখানো হয়। প্রো মোড চালু করলে সূক্ষ্ম সেন্সর, বর্ডার সাজানো এবং অ্যাডভান্সড অটোমেশন সব দেখা যায়।",
        exampleUse = "সাধারণভাবে পকেটে লক, ভাসমান বোতাম, বাচ্চাদের সুরক্ষা বা কালো পর্দা চালাতে চাইলে সহজ মোডই যথেষ্ট।",
        adviceTip = "শুরুতে সহজ মোড ব্যবহার করুন; কোনো প্রো অপশন লাগলে উপরে সার্চ করলেই সাথে সাথে পেয়ে যাবেন।"
    ),
    SettingHelpTopic.UNLOCK_METHOD to FeatureHelpContent(
        title = "লক খোলার ধরন",
        whatItDoes = "স্ক্রিনের টাচ লক করার পর কীভাবে তা আনলক বা খুলবেন—এখান থেকে তা বেছে নেওয়া যায়।",
        exampleUse = "পকেটের জন্য 'চেপে ধরে আনলক' বা 'স্লাইড' ভালো; আর বাচ্চাকে ফোন দেওয়ার আগে '৪-সংখ্যার পিন' বেছে নিন।",
        adviceTip = "পকেটে ভুল করে না খুলতে চাইলে 'চেপে ধরে আনলক' সবচেয়ে নিরাপদ।"
    ),
    SettingHelpTopic.BIOMETRIC_UNLOCK to FeatureHelpContent(
        title = "আঙুলের ছাপ বা ফেস আনলক",
        whatItDoes = "পিন লক চালু থাকলে পিন লেখার জায়গার পাশেই আপনার আঙুলের ছাপ বা মুখ দেখে দ্রুত খোলার বাটন দেখায়।",
        exampleUse = "বাচ্চার হাত থেকে ফোন নেওয়ার পর বারবার ৪ সংখ্যার পিন না লিখে ১ সেকেন্ডে আঙুলের ছাপ দিয়ে খুলে ফেলুন।",
        adviceTip = "পিন ব্যবহার করলে এটি চালু রাখাই সবচেয়ে আরামদায়ক।"
    ),
    SettingHelpTopic.EMERGENCY_HOLD_BYPASS to FeatureHelpContent(
        title = "পিন ভুলে গেলে জরুরি আনলক (৫ সেকেন্ড)",
        whatItDoes = "কোনো কারণে ৪-সংখ্যার পিন ভুলে গেলে জরুরি বাটন টানা ৫ সেকেন্ড চেপে ধরে লক খোলার সুবিধা রাখে।",
        exampleUse = "আপনি পিন ভুলে গেলে বা অন্য কেউ পিন বদলে ফেললেও ফোন আটকে যাবে না—৫ সেকেন্ড ধরে রাখলেই খুলবে।",
        adviceTip = "ছোট বাচ্চারা যাতে ৫ সেকেন্ড চেপে লক খুলতে না পারে, সেজন্য বাচ্চাদের ফোন দিলে এটি বন্ধ (বা শিশু সুরক্ষা চালু) রাখুন।"
    ),
    SettingHelpTopic.LOCK_COUNTDOWN to FeatureHelpContent(
        title = "লক হওয়ার আগে সময় নেওয়া (০–৫ সেকেন্ড)",
        whatItDoes = "লক বাটনে চাপ দেওয়ার সাথে সাথে লক না হয়ে ৩ বা ৫ সেকেন্ড পরে লক চালু হয়।",
        exampleUse = "লক বাটনে ট্যাপ করে ৩ সেকেন্ডের মধ্যে YouTube বা ছবি খুলে নিন, তারপর নিজে থেকেই লক হয়ে যাবে।",
        adviceTip = "সাথে সাথে লক চাইলে 'তাৎক্ষণিক (0s)' রাখুন।"
    ),
    SettingHelpTopic.HUD_TIMEOUT to FeatureHelpContent(
        title = "খোলার বাটন নিজে থেকেই লুকানো",
        whatItDoes = "স্ক্রিন লক অবস্থায় আনলক করার লেখা/বারটি কয়েক সেকেন্ড পর আপনা-আপনি অদৃশ্য হয়ে যায়, যাতে ছবি বা ভিডিও দেখতে অসুবিধা না হয়।",
        exampleUse = "যখন লক খুলতে চাইবেন, শুধু পর্দার যেকোনো জায়গায় ট্যাপ করলেই খোলার বাটন ফিরে আসবে।",
        adviceTip = "৩ সেকেন্ড সাধারণ ব্যবহারের জন্য সবচেয়ে ভালো।"
    ),
    SettingHelpTopic.KIDS_SAFETY to FeatureHelpContent(
        title = "বাচ্চাদের জন্য কড়া লক (শিশু সুরক্ষা)",
        whatItDoes = "৫ সেকেন্ডের জরুরি আনলক বাটন লুকিয়ে রাখে এবং ফোন ঝাঁকিয়ে আনলক করা বন্ধ করে দেয়, যাতে বাচ্চারা নিজে থেকে লক খুলতে না পারে।",
        exampleUse = "বাচ্চাকে কার্টুন চালিয়ে দিয়ে এটি চালু করুন—সে শুধু ভিডিও দেখতে পারবে, কিন্তু অ্যাপ থেকে বের হয়ে কল বা কিছু ডিলিট করতে পারবে না।",
        adviceTip = "বাচ্চাকে ফোন দেওয়ার আগে অবশ্যই এটি এবং পিন আনলক চালু রাখুন।"
    ),
    SettingHelpTopic.WRONG_ATTEMPT_COOLDOWN to FeatureHelpContent(
        title = "ভুল পিনে কিছুক্ষণ বিরতি",
        whatItDoes = "কেউ বারবার ভুল পিন দিলে পিন বাটনগুলো সাময়িকভাবে বন্ধ হয়ে যায় (৫ বার ভুলে ১৫ সেকেন্ড, ৮+ বারে ৩০ সেকেন্ড)।",
        exampleUse = "বাচ্চারা বা বন্ধুরা ইচ্ছামতো নম্বর টিপে টিপে আপনার পিন বের করার চেষ্টা করলে তা আটকে যাবে।",
        adviceTip = "পিন ব্যবহার করলে এটি সবসময় চালু রাখাই নিরাপদ।"
    ),
    SettingHelpTopic.KIDS_TIMER_EXPIRY to FeatureHelpContent(
        title = "সময় শেষ হলে পিন ছাড়া খুলবে না",
        whatItDoes = "আপনার দেওয়া অটো-টাইমার (যেমন ১৫ বা ৩০ মিনিট) শেষ হলে লক খোলার বদলে প্যারেন্ট পিন দিয়ে পর্দা আটকে দেয়।",
        exampleUse = "বাচ্চাকে ১৫ মিনিট ভিডিও দেখার সময় দিন—১৫ মিনিট শেষ হলেই পর্দা লক হয়ে আপনার পিন চাইবে।",
        adviceTip = "শিশুদের স্ক্রিন-টাইম নিয়ন্ত্রণের জন্য দারুণ কার্যকর।"
    ),
    SettingHelpTopic.OVERLAY_STYLE to FeatureHelpContent(
        title = "লক থাকা অবস্থায় পর্দা কেমন দেখাবে",
        whatItDoes = "স্ক্রিন লক করার পর পর্দা পুরোপুরি পরিষ্কার থাকবে, নাকি হালকা ছায়া বা গাঢ় কালো হয়ে থাকবে—তা ঠিক করে।",
        exampleUse = "ছবি, টিকিট বা ম্যাপ দেখতে 'একদম পরিষ্কার (0%)' বেছে নিন; আর পকেটে গান শুনতে চাইলে 'কালো পর্দা (88%)' বেছে ব্যাটারি বাঁচান।",
        adviceTip = "পকেটে অডিও চালানোর সময় গাঢ় কালো মোড ব্যাটারি অনেক বাঁচায়।"
    ),
    SettingHelpTopic.LOCK_BRIGHTNESS to FeatureHelpContent(
        title = "লক অবস্থায় পর্দার আলো কমানো/বাড়ানো",
        whatItDoes = "শুধু লক থাকা অবস্থায় স্ক্রিনের ব্রাইটনেস কম বা বেশি রাখে; লক খোলার সাথে সাথে আবার আপনার আগের আলো ফিরিয়ে আনে।",
        exampleUse = "বাইক চালানোর সময় রোদে ম্যাপ দেখতে 'রোদ ১০০%' দিন, আর রাতে গান শুনতে 'রাত ৫%' দিন।",
        adviceTip = "'সিস্টেম' রাখলে ফোনের সাধারণ আলো যেমন আছে তেমনই থাকবে।"
    ),
    SettingHelpTopic.EDGE_BORDER to FeatureHelpContent(
        title = "চারপাশে রঙিন দাগ (লক চিহ্ন)",
        whatItDoes = "পর্দার চারপাশে বা কোণায় হালকা রঙিন বর্ডার দেখায়, যাতে এক নজর দেখেই বোঝা যায় এখন টাচ লক করা আছে।",
        exampleUse = "০% পরিষ্কার পর্দায় হঠাৎ ট্যাপ কাজ না করলে চারপাশের সবুজ দাগ দেখে সহজেই মনে পড়বে যে লক চালু আছে।",
        adviceTip = "ভিডিও দেখতে চাইলে 'চার কোণায় দাগ' সবচেয়ে পরিষ্কার দেখায়।"
    ),
    SettingHelpTopic.OLED_CLOCK to FeatureHelpContent(
        title = "অন্ধকার পর্দায় সময় ও ব্যাটারি দেখা",
        whatItDoes = "পর্দা কালো বা অন্ধকার থাকলে মাঝখানে হালকা অক্ষরে ঘড়ি ও কত শতাংশ চার্জ আছে তা দেখায়।",
        exampleUse = "রাতে বা পকেট থেকে বের করে পিন না খুলেই এক নজরে সময় ও চার্জ দেখে নেওয়া যায়।",
        adviceTip = "৮৮% গাঢ় কালো মোড বা গোপন কালো পর্দার সাথে এটি খুব কাজে লাগে।"
    ),
    SettingHelpTopic.PARTIAL_TOUCH_ZONE to FeatureHelpContent(
        title = "নির্দিষ্ট অংশ খোলা রেখে বাকিটা লক",
        whatItDoes = "পুরো স্ক্রিন লক না করে শুধু আপনার ঠিক করা একটি অংশে (যেমন উপরের ভিডিও বা নিচের অংশ) টাচ চালু রাখে, বাকি সব লক করে।",
        exampleUse = "ভিডিও প্লেয়ার খোলা রেখে বাকি অংশ লক করে রাখলে ভুল করে ব্যাক বাটন, বিজ্ঞাপন বা অন্য ভিডিওতে চাপ পড়বে না।",
        adviceTip = "গুগল ম্যাপ বা ইউটিউব চালানোর সময় দারুণ উপকারী।"
    ),
    SettingHelpTopic.PRIVACY_VEIL to FeatureHelpContent(
        title = "পাশ থেকে যেন কেউ না দেখে (৯৯% কালো পর্দা)",
        whatItDoes = "অ্যাপ বন্ধ না করেই পর্দাকে প্রায় পুরো কালো করে দেয় যাতে বাসে-ট্রেনে পাশের কেউ দেখতে না পারে। প্রয়োজনে '৩ সেকেন্ড দেখুন' বোতামে ট্যাপ করে দেখে নেওয়া যায়।",
        exampleUse = "পাবলিক বাসে ব্যক্তিগত চ্যাট খোলা রেখে বা পকেটে ইউটিউব গান বাজাতে বাজাতে স্ক্রিন কালো করে নিশ্চিন্ত থাকুন।",
        adviceTip = "ভিড়ের মধ্যে প্রাইভেসি রক্ষা ও অডিও চালিয়ে রাখতে সেরা।"
    ),
    SettingHelpTopic.STEALTH_BLACKOUT to FeatureHelpContent(
        title = "একদম বন্ধের মতো কালো পর্দা (কোনো আলো নয়)",
        whatItDoes = "চারপাশের রঙিন বর্ডার, ওপরের লেখা এবং ঘড়ি সব লুকিয়ে পর্দাকে একদম বন্ধ স্ক্রিনের মতো করে রাখে।",
        exampleUse = "অন্ধকার ঘরে বা পকেটে সামান্য আলোও না চাইলে এটি চালু করুন—পর্দায় ট্যাপ করলেই লক খোলার বাটন আসবে।",
        adviceTip = "৯৯% কালো পর্দার সাথে ব্যবহার করলে ফোনের স্ক্রিন একদম নিভে আছে বলে মনে হবে।"
    ),
    SettingHelpTopic.FLOATING_BUBBLE to FeatureHelpContent(
        title = "পর্দার ওপর ভাসমান লক বোতাম",
        whatItDoes = "যেকোনো অ্যাপের ওপরে ছোট একটি ভাসমান লক বাটন রাখে, যাতে যেখানেই থাকুন ১ ট্যাপেই স্ক্রিন লক করতে পারেন।",
        exampleUse = "গ্যালারির ছবি, ম্যাপ বা ইউটিউব খুলে রেখেই পাশের ভাসমান বোতামে চাপ দিয়ে সাথে সাথে টাচ আটকে দিন।",
        adviceTip = "সহজে প্রতিদিন ব্যবহারের জন্য এটি চালু রাখা সবচেয়ে ভালো।"
    ),
    SettingHelpTopic.BUBBLE_AUTO_TUCK to FeatureHelpContent(
        title = "ব্যবহার না করলে বোতাম কিনারায় লুকাবে",
        whatItDoes = "৩ সেকেন্ড হাত না দিলে ভাসমান বোতামটি অর্ধেক স্ক্রিনের ধারে সরে গিয়ে হালকা হয়ে যায়, যাতে ছবি বা লেখার ওপর বাধা না হয়।",
        exampleUse = "ভিডিও বা লেখা পড়ার সময় বোতামটি সামনে এসে ডিস্টার্ব করবে না; ধরলেই আবার পুরো দেখা যাবে।",
        adviceTip = "সাধারণত এটি চালু রাখাই সবচেয়ে আরামদায়ক।"
    ),
    SettingHelpTopic.BUBBLE_DOUBLE_TAP_HIDE to FeatureHelpContent(
        title = "২ বার চাপলে বোতাম সাময়িক লুকাবে",
        whatItDoes = "ভাসমান বোতামটি কোনো বাটনের ওপর পড়ে গেলে তাতে দ্রুত ২ বার ট্যাপ করে সাময়িকভাবে সরিয়ে দিতে পারবেন; পরে নোটিফিকেশন থেকে ফিরিয়ে আনুন।",
        exampleUse = "কোনো অ্যাপের কোণার বাটনে চাপ দিতে সমস্যা হলে ডাবল-ট্যাপ করলেই বোতাম সরে যাবে।",
        adviceTip = "দরকারি শর্টকাট হিসেবে চালু রাখুন।"
    ),
    SettingHelpTopic.POCKET_AUTO_LOCK to FeatureHelpContent(
        title = "পকেটে রাখলেই অটো-লক",
        whatItDoes = "ফোনের ওপরের সেন্সর দিয়ে বুঝতে পারে ফোন পকেটে বা ব্যাগে ঢুকেছে কি না, এবং নিজে থেকেই স্ক্রিন লক করে দেয়।",
        exampleUse = "ছবি বা অ্যাপ খোলা অবস্থায় হাতে নিয়ে পকেটে ঢোকান—বাটন না চাপলেও কাপড়ের ঘষায় ভুল কল বা ডিলিট হবে না।",
        adviceTip = "হাত ব্যস্ত থাকলে বা ছবি খোলা রেখে পকেটে ভরতে চাইলে এটি চালু রাখুন।"
    ),
    SettingHelpTopic.POCKET_AUTO_UNLOCK to FeatureHelpContent(
        title = "পকেট থেকে বের করলেই অটো-আনলক",
        whatItDoes = "পকেট সেন্সর দিয়ে লক হওয়া ফোন আবার পকেট থেকে বের করলে নিজে থেকেই টাচ আনলক হয়ে যায়।",
        exampleUse = "পকেট থেকে বের করা মাত্রই ম্যাপ বা টিকিট আবার আগের মতো ব্যবহার করা শুরু করুন।",
        adviceTip = "বাচ্চাদের হাতে ফোন দিলে বা শুধু হাতে লক খুলতে চাইলে এটি বন্ধ রাখুন।"
    ),
    SettingHelpTopic.POCKET_SENSITIVITY to FeatureHelpContent(
        title = "পকেটে বোঝার গতি (সেন্সর সময়)",
        whatItDoes = "ওপরের সেন্সর কতক্ষণ ঢাকা থাকলে ফোন পকেটে গেছে বলে ধরবে—তার সময় ঠিক করে (যেমন ০.৭ সেকেন্ড বা ১.৫ সেকেন্ড)।",
        exampleUse = "হাত নাড়ালে ভুল করে লক হয়ে গেলে সময় ১.২ সেকেন্ড বা তার বেশি দিন; দ্রুত লকের জন্য ০.৭ সেকেন্ড রাখুন।",
        adviceTip = "ডিফল্ট ০.৭ সেকেন্ড বেশিরভাগ মানুষের জন্য একদম ঠিক।"
    ),
    SettingHelpTopic.ORIENTATION_FILTER to FeatureHelpContent(
        title = "টেবিলে রাখা থাকলে ভুল লক আটকাবে",
        whatItDoes = "ফোন টেবিল বা বিছানায় সোজা চিৎ হয়ে থাকলে ওপর দিয়ে হাত গেলেও অযথা পকেট লক চালু হতে দেয় না।",
        exampleUse = "টেবিলে ফোন রেখে কাজ করার সময় ওপর দিয়ে হাত বা কাগজ সরলে স্ক্রিন বিরক্তিকরভাবে লক হবে না।",
        adviceTip = "অপ্রয়োজনীয় লক এড়াতে এটি সবসময় চালু রাখুন।"
    ),
    SettingHelpTopic.PAUSE_LANDSCAPE to FeatureHelpContent(
        title = "আড়াআড়ি ভিডিও বা গেমে অটো-লক থামাও",
        whatItDoes = "ফোন আড়াআড়ি (Landscape) ধরে সিনেমা দেখলে বা গেম খেললে পকেটে লক হওয়ার সেন্সর সাময়িক বন্ধ রাখে।",
        exampleUse = "গেম খেলার সময় বা ইউটিউব দেখার সময় আপনার হাত স্পিকারের পাশে লাগলেও স্ক্রিন হঠাৎ লক হবে না।",
        adviceTip = "গেমার ও ভিডিও দর্শকদের জন্য অবশ্যই চালু রাখা উচিত।"
    ),
    SettingHelpTopic.PAUSE_CHARGING to FeatureHelpContent(
        title = "চার্জে লাগানো থাকলে পকেট লক বন্ধ",
        whatItDoes = "ফোনে চার্জার কেবল লাগানো থাকলে পকেটের সেন্সর দিয়ে নিজে থেকে লক হওয়া বন্ধ রাখে।",
        exampleUse = "গাড়ির ড্যাশবোর্ডে বা টেবিলে চার্জে লাগিয়ে কাজ করার সময় ভুল করে অটো-লক হবে না।",
        adviceTip = "পাওয়ার ব্যাংকের সাথে পকেটে ফোন রাখলে এটি বন্ধ রাখুন।"
    ),
    SettingHelpTopic.FLIP_FACE_DOWN to FeatureHelpContent(
        title = "ফোন উল্টো করে রাখলে অটো-লক",
        whatItDoes = "ফোনটির স্ক্রিন নিচের দিকে দিয়ে টেবিলে উপুড় করে রাখলেই সাথে সাথে টাচ লক চালু করে দেয়।",
        exampleUse = "মিটিং বা আড্ডায় কিছু খোলা রেখেই টেবিলে ফোন উল্টো করে রাখলে কেউ ছুঁয়ে অ্যাপ বদলাতে পারবে না।",
        adviceTip = "১ সেকেন্ডে লক করার দারুণ স্মার্ট উপায়।"
    ),
    SettingHelpTopic.CALL_AUTO_UNLOCK to FeatureHelpContent(
        title = "ফোন কল আসলে নিজেই খুলে যাবে",
        whatItDoes = "রিং বাজলে বা কল আসলে সাথে সাথে লক খুলে দেয় যাতে ফোন ধরতে অসুবিধা না হয় (চাইলে কথা শেষে আবার লক হবে)।",
        exampleUse = "পকেটে টাচ লক থাকা অবস্থায় জরুরি কল আসলে নির্বিঘ্নে রিসিভ করতে পারবেন।",
        adviceTip = "নিয়মিত পকেটে লক ব্যবহার করলে এটি চালু রাখা খুব দরকারি।"
    ),
    SettingHelpTopic.SESSION_TIMER to FeatureHelpContent(
        title = "নির্দিষ্ট সময় পর অটো-আনলক টাইমার",
        whatItDoes = "১৫, ৩০ বা ৬০ মিনিট পর নিজে থেকে লক বন্ধ করে দেয় (আর শিশু সুরক্ষা টাইমার চালু থাকলে প্যারেন্ট পিন লক বসায়)।",
        exampleUse = "ঘুমানোর আগে ৩০ মিনিটের টাইমার দিয়ে গান চালিয়ে নিশ্চিন্তে ঘুমিয়ে পড়ুন।",
        adviceTip = "সময় ধরে ব্যবহার না করলে 'বন্ধ' রাখাই ভালো।"
    ),
    SettingHelpTopic.APP_WATCH to FeatureHelpContent(
        title = "নির্দিষ্ট অ্যাপে অটো সুরক্ষা",
        whatItDoes = "আপনার বেছে নেওয়া অ্যাপ (যেমন YouTube, Maps, Camera) খুললেই নিজে থেকে ভাসমান লক বোতাম দেখাবে বা স্ক্রিন লক করবে, আর বের হলে বন্ধ হবে।",
        exampleUse = "বাইকে উঠে Google Maps খুললেই ভাসমান বোতাম চলে আসবে—অ্যাপ থেকে বের হলেই বোতাম অদৃশ্য হবে।",
        adviceTip = "কোনো জটিল Accessibility ছাড়াই শুধু আপনার ফোনের লোকাল অ্যাপ ব্যবহার অনুমতি দিয়ে নিরাপদে কাজ করে।"
    ),
    SettingHelpTopic.SHAKE_SHORTCUT to FeatureHelpContent(
        title = "ফোন ২ বার ঝাঁকিয়ে লক / আনলক",
        whatItDoes = "স্ক্রিনে কোনো বাটন না চেপেই ফোনটি হালকা ২ বার ঝাঁকিয়ে টাচ লক বা আনলক করতে দেয়।",
        exampleUse = "হাতে গ্লাভস পরা বা ভেজা হাতে ফোন লক করতে চাইলে শুধু দুইবার ঝাঁকি দিন।",
        adviceTip = "বাচ্চাদের সুরক্ষা মোড চালু করলে ঝাঁকিয়ে আনলক নিজে থেকেই বন্ধ হয়ে যায়।"
    ),
    SettingHelpTopic.VOLUME_SHORTCUT to FeatureHelpContent(
        title = "ভলিউম বাটনে ডাবল-ক্লিক শর্টকাট",
        whatItDoes = "ভলিউম বাড়ানো বা কমানোর বোতামে দ্রুত ২ বার চাপ দিলে টাচ লক চালু বা বন্ধ হয় (অ্যাপ ও লক পর্দায় কাজ করে)।",
        exampleUse = "বৃষ্টিতে বা ভেজা আঙুলে স্ক্রিনে কাজ না করলে সাইডের ভলিউম বাটনে ২ বার চেপে লক খুলে ফেলুন।",
        adviceTip = "হার্ডওয়্যার বাটনে সহজ নিয়ন্ত্রণের জন্য বেশ উপকারী।"
    ),
    SettingHelpTopic.LOW_BATTERY_BLACKOUT to FeatureHelpContent(
        title = "চার্জ কমলে অটো কালো পর্দা (ব্যাটারি বাঁচাও)",
        whatItDoes = "ব্যাটারি ১০%, ১৫% বা ২০%-এর নিচে নামলেই লক থাকা অবস্থায় পর্দা ৯২% কালো করে এবং আলো কমিয়ে ব্যাটারি বাঁচায়।",
        exampleUse = "ভ্রমণের সময় ফোনে চার্জ কমে ১৫% হলে নিজে থেকেই পর্দা অন্ধকার করে বাকি পথ চলার চার্জ বাঁচিয়ে রাখবে।",
        adviceTip = "১৫% সেট করে রাখলে জরুরি সময়ে দারুণ ব্যাটারি সেভ হয়।"
    ),
    SettingHelpTopic.CHARGER_AUTO_RULE to FeatureHelpContent(
        title = "চার্জার লাগানো/খোলার নিয়ম",
        whatItDoes = "চার্জার কেবল লাগালে অটো লক হবে অথবা চার্জার খুলে ফেললেই সাথে সাথে আনলক হয়ে যাবে।",
        exampleUse = "গাড়িতে চার্জে বসালেই ম্যাপ বা মিউজিক স্ক্রিন লক হবে, আর গাড়ি থেকে নামার সময় কেবল খুললেই আনলক হবে।",
        adviceTip = "গাড়ি চালক ও পাওয়ার ব্যাংক ব্যবহারকারীদের জন্য সেরা।"
    ),
    SettingHelpTopic.KEEP_SCREEN_ON to FeatureHelpContent(
        title = "লক অবস্থায় পর্দা যেন নিভে না যায়",
        whatItDoes = "লক চালু থাকলে ফোনের স্ক্রিন সময় শেষ হয়ে নিজে থেকে বন্ধ হয়ে যাবে না—ফটো, ম্যাপ বা টিকিট দেখা যেতেই থাকবে।",
        exampleUse = "লাইন ধরে টিকিট দেখানোর সময় বা রান্নার রেসিপি দেখে কাজ করার সময় বারবার স্ক্রিন জ্বালাতে হবে না।",
        adviceTip = "শুধু পকেটে গান শুনে বেশি ব্যাটারি বাঁচাতে চাইলে এটি বন্ধ রাখতে পারেন।"
    ),
    SettingHelpTopic.HAPTICS_AND_BOOT to FeatureHelpContent(
        title = "হালকা ভাইব্রেশন ও রিস্টার্টে অটো-চালু",
        whatItDoes = "লক বা আনলক হলে হাতের ছোঁয়ায় হালকা কাঁপুনি (Vibration) দেয়, এবং ফোন রিস্টার্টের পর নিজে থেকেই সুরক্ষা তৈরি রাখে।",
        exampleUse = "পকেটে ফোন ঢোকানোর পর না তাকিয়েও হালকা ভাইব্রেশন অনুভব করে বুঝবেন যে লক চালু হয়েছে।",
        adviceTip = "ভাইব্রেশন চালু রাখলে প্রতিটি লক সহজে টের পাওয়া যায়।"
    ),
    SettingHelpTopic.BACKUP_RESTORE to FeatureHelpContent(
        title = "সেটিংস ফাইল হিসেবে জমা বা ফেরত আনা",
        whatItDoes = "আপনার সব পছন্দের সেটিংস ও প্রোফাইল একটি ছোট অফলাইন ফাইল (.json) হিসেবে সেভ রাখে, যা পরে যেকোনো সময় ফেরত আনা যায়।",
        exampleUse = "নতুন ফোন নিলে বা অ্যাপ আবার ইনস্টল করলে আগের সব সেটিংস ১ ক্লিকেই ফিরে পাবেন।",
        adviceTip = "সম্পূর্ণ অফলাইন—আপনার তথ্য আপনার ফোনেই থাকে।"
    )
)
