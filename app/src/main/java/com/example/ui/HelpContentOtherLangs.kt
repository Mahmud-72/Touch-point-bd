package com.example.ui

import com.example.data.SettingHelpTopic

val HindiHelpMap: Map<SettingHelpTopic, FeatureHelpContent> = mapOf(
    SettingHelpTopic.MODE_EASY_PRO to FeatureHelpContent(
        title = "आसान मोड बनाम प्रो मोड",
        whatItDoes = "आसान मोड में केवल रोज़मर्रा के सबसे जरूरी बटन दिखते हैं। प्रो मोड में सभी बारीक स्लाइडर, सेंसर और अतिरिक्त ऑटोमेशन खुल जाते हैं।",
        exampleUse = "अगर आप केवल पॉकेट लॉक, फ्लोटिंग बटन, बच्चों की सुरक्षा या काली स्क्रीन चाहते हैं तो आसान मोड रखें।",
        adviceTip = "सामान्य उपयोग के लिए आसान मोड से शुरू करें; खोज बार में किसी भी समय प्रो सेटिंग मिल जाती है।"
    ),
    SettingHelpTopic.UNLOCK_METHOD to FeatureHelpContent(
        title = "स्क्रीन टच खोलने का तरीका",
        whatItDoes = "चुनता है कि स्क्रीन लॉक होने के बाद टच दोबारा कैसे खुलेगा।",
        exampleUse = "जेब के लिए 'दबाकर रखें' या 'स्लाइड' चुनें; बच्चों को फोन देने से पहले '4-अंकीय PIN' चुनें।",
        adviceTip = "जेब में गलती से न खुलने के लिए 'दबाकर रखें' सबसे सुरक्षित है।"
    ),
    SettingHelpTopic.BIOMETRIC_UNLOCK to FeatureHelpContent(
        title = "फिंगरप्रिंट / फेस अनलॉक",
        whatItDoes = "4-अंकीय PIN पैड के बगल में फिंगरप्रिंट या चेहरे से तुरंत खोलने का बटन जोड़ता है।",
        exampleUse = "बच्चों से फोन वापस लेने के बाद PIN टाइप किए बिना उंगली लगाकर 1 सेकंड में अनलॉक करें।",
        adviceTip = "PIN मोड उपयोग करने पर इसे चालू रखना बेहद सुविधाजनक है।"
    ),
    SettingHelpTopic.EMERGENCY_HOLD_BYPASS to FeatureHelpContent(
        title = "PIN भूलने पर 5 सेकंड इमरजेंसी अनलॉक",
        whatItDoes = "PIN भूल जाने की स्थिति में 5 सेकंड तक बटन दबाकर लॉक खोलने का सुरक्षित रास्ता देता है।",
        exampleUse = "यदि कोई PIN बदल दे या आप भूल जाएँ, तो भी आपका फोन अनलॉक हो जाएगा।",
        adviceTip = "छोटे बच्चों को फोन देते समय इसे बंद रखें (या किड्स सेफ-लॉक चालू करें)।"
    ),
    SettingHelpTopic.LOCK_COUNTDOWN to FeatureHelpContent(
        title = "लॉक होने से पहले देरी (0–5 सेकंड)",
        whatItDoes = "लॉक दबाने के तुरंत बाद नहीं, बल्कि 3 या 5 सेकंड बाद टच ब्लॉक करता है।",
        exampleUse = "लॉक दबाएँ और 3 सेकंड के भीतर अपना वीडियो या मैप खोल लें।",
        adviceTip = "एक टैप में तुरंत लॉक के लिए 'तुरंत (0s)' रखें।"
    ),
    SettingHelpTopic.HUD_TIMEOUT to FeatureHelpContent(
        title = "अनलॉक बटन अपने आप छिपना",
        whatItDoes = "कुछ सेकंड बाद अनलॉक पैनल को छिपा देता है ताकि आपकी फोटो या वीडियो पूरी साफ दिखे।",
        exampleUse = "अनलॉक करना हो तो स्क्रीन पर कहीं भी टैप करें और बटन वापस आ जाएगा।",
        adviceTip = "3 सेकंड रोज़मर्रा के लिए संतुलित विकल्प है।"
    ),
    SettingHelpTopic.KIDS_SAFETY to FeatureHelpContent(
        title = "बच्चों के लिए पक्का लॉक",
        whatItDoes = "5 सेकंड का इमरजेंसी अनलॉक छिपाता है और फोन हिलाकर खोलना बंद करता है ताकि बच्चे PIN बाईपास न कर पाएँ।",
        exampleUse = "बच्चों को कार्टून चलाकर दें—वे ऐप से बाहर नहीं निकल पाएंगे और कोई कॉल या डिलीट नहीं होगा।",
        adviceTip = "बच्चों को फोन देने से पहले PIN अनलॉक के साथ इसे जरूर चालू करें।"
    ),
    SettingHelpTopic.WRONG_ATTEMPT_COOLDOWN to FeatureHelpContent(
        title = "गलत PIN पर प्रतीक्षा समय",
        whatItDoes = "बार-बार गलत PIN डालने पर कीपैड को कुछ सेकंड के लिए रोक देता है (5 गलत पर 15s, 8+ पर 30s)।",
        exampleUse = "बच्चों या दोस्तों को अंदाज़े से PIN आज़माने से रोकता है।",
        adviceTip = "PIN लॉक के साथ हमेशा चालू रखें।"
    ),
    SettingHelpTopic.KIDS_TIMER_EXPIRY to FeatureHelpContent(
        title = "समय पूरा होने पर PIN लॉक",
        whatItDoes = "सेशन टाइमर खत्म होने पर अनलॉक होने के बजाय पैरेन्ट PIN लॉक लगा देता है।",
        exampleUse = "15 मिनट का वीडियो समय तय करें—15 मिनट बाद स्क्रीन अपने आप आपके PIN के पीछे रुक जाएगी।",
        adviceTip = "बच्चों का स्क्रीन टाइम नियंत्रित करने के लिए उत्तम।"
    ),
    SettingHelpTopic.OVERLAY_STYLE to FeatureHelpContent(
        title = "लॉक के दौरान स्क्रीन कैसी दिखे",
        whatItDoes = "तय करता है कि टच लॉक होने पर स्क्रीन पूरी साफ दिखे, हल्की छाया हो या बैटरी बचाने को गहरी काली हो।",
        exampleUse = "फोटो व मैप के लिए 'पूरी साफ (0%)'; जेब में गाना सुनने के लिए 'गहरी काली (88%)' चुनें।",
        adviceTip = "AMOLED स्क्रीन पर काला मोड बैटरी काफी बचाता है।"
    ),
    SettingHelpTopic.LOCK_BRIGHTNESS to FeatureHelpContent(
        title = "लॉक के समय स्क्रीन की चमक",
        whatItDoes = "लॉक रहने के दौरान स्क्रीन की रोशनी कम या तेज रखता है और अनलॉक होते ही पहले जैसी कर देता है।",
        exampleUse = "धूप में बाइक मैप के लिए 100% चुनें, और रात में पॉडकास्ट के लिए 5% चुनें।",
        adviceTip = "'सिस्टम' रखने पर फोन की सामान्य चमक ही बनी रहती है।"
    ),
    SettingHelpTopic.EDGE_BORDER to FeatureHelpContent(
        title = "किनारों पर रंगीन चमकती बॉर्डर",
        whatItDoes = "स्क्रीन के चारों तरफ या कोनों में हल्की बॉर्डर दिखाता है ताकि तुरंत समझ आए कि टच लॉक है।",
        exampleUse = "0% पारदर्शी स्क्रीन पर टैप न होने पर बॉर्डर याद दिलाता है कि लॉक चालू है।",
        adviceTip = "वीडियो पर 'कॉर्नर फ्रेम' सबसे साफ लगता है।"
    ),
    SettingHelpTopic.OLED_CLOCK to FeatureHelpContent(
        title = "काली स्क्रीन पर घड़ी और बैटरी",
        whatItDoes = "स्क्रीन धुंधली या काली होने पर बीच में हल्की घड़ी और बैटरी प्रतिशत दिखाता है।",
        exampleUse = "फोन अनलॉक किए बिना एक नज़र में समय और बैटरी देख लें।",
        adviceTip = "डार्क मोड या ब्लैकआउट शील्ड के साथ बहुत उपयोगी।"
    ),
    SettingHelpTopic.PARTIAL_TOUCH_ZONE to FeatureHelpContent(
        title = "केवल एक हिस्सा चालू, बाकी लॉक",
        whatItDoes = "चुने हुए बॉक्स (जैसे ऊपर वीडियो या बीच का मैप) में टच चलने देता है और बाकी पूरी स्क्रीन लॉक रखता है।",
        exampleUse = "YouTube वीडियो चलते समय किनारे के बटन, विज्ञापन या बैक बटन से गलती से बाहर जाने को रोकें।",
        adviceTip = "एक हाथ से वीडियो या मैप उपयोग के लिए बेहतरीन।"
    ),
    SettingHelpTopic.PRIVACY_VEIL to FeatureHelpContent(
        title = "भीड़ में स्क्रीन छिपाएँ (99% काली)",
        whatItDoes = "ऐप बंद किए बिना स्क्रीन लगभग पूरी काली कर देता है ताकि बगल वाला न देख पाए, साथ में 3s झाँकने का बटन रहता है।",
        exampleUse = "बस या ट्रेन में अपनी चैट, बैंक ऐप या पॉडकास्ट सुरक्षित रखें; 3s झाँकें बटन से कभी भी देख लें।",
        adviceTip = "सार्वजनिक यात्रा और लंबे ऑडियो सुनने के लिए बेस्ट।"
    ),
    SettingHelpTopic.STEALTH_BLACKOUT to FeatureHelpContent(
        title = "बिल्कुल बंद जैसी काली स्क्रीन",
        whatItDoes = "बॉर्डर, ऊपरी बैज और घड़ी सब छिपा देता है जिससे फोन बंद जैसा दिखता है—टैप करने पर अनलॉक दिखता है।",
        exampleUse = "अंधेरे कमरे या जेब में बिल्कुल रोशनी न चाहें तो चालू करें।",
        adviceTip = "प्राइवेसी काली स्क्रीन के साथ सबसे बढ़िया काम करता है।"
    ),
    SettingHelpTopic.FLOATING_BUBBLE to FeatureHelpContent(
        title = "तैरता हुआ लॉक बटन",
        whatItDoes = "हर ऐप के ऊपर एक छोटा मूवेबल लॉक बटन दिखाता है ताकि 1 टैप में तुरंत लॉक कर सकें।",
        exampleUse = "गैलरी, मैप या वीडियो खोलकर तुरंत वहीं से बटन दबाकर टच फ्रीज़ करें।",
        adviceTip = "रोज़ाना आसान उपयोग के लिए चालू रखें।"
    ),
    SettingHelpTopic.BUBBLE_AUTO_TUCK to FeatureHelpContent(
        title = "3 सेकंड बाद किनारे छिप जाए",
        whatItDoes = "इस्तेमाल न होने पर फ्लोटिंग बटन आधे किनारे में खिसक कर हल्का हो जाता है।",
        exampleUse = "पढ़ते या गेम खेलते समय बटन स्क्रीन के ऊपर बाधा नहीं डालेगा।",
        adviceTip = "चालू रखना सबसे आरामदायक रहता है।"
    ),
    SettingHelpTopic.BUBBLE_DOUBLE_TAP_HIDE to FeatureHelpContent(
        title = "डबल-टैप से बटन अस्थायी छिपाएँ",
        whatItDoes = "बटन पर दो बार टैप करने से वह अस्थायी रूप से छिप जाता है; नोटिफिकेशन से वापस ला सकते हैं।",
        exampleUse = "यदि बटन किसी ज़रूरी कोने के आइकन को ढक ले तो 2 टैप में हटा दें।",
        adviceTip = "बहुत काम का शॉर्टकट है।"
    ),
    SettingHelpTopic.POCKET_AUTO_LOCK to FeatureHelpContent(
        title = "जेब में रखते ही ऑटो लॉक",
        whatItDoes = "ऊपरी सेंसर से पहचानता है कि फोन जेब में है और अपने आप टच लॉक कर देता है।",
        exampleUse = "टिकट या फोटो खुली रखकर जेब में डालें—कपड़े से गलती से कॉल या डिलीट नहीं होगा।",
        adviceTip = "बिना बटन दबाए पॉकेट सुरक्षा के लिए चालू रखें।"
    ),
    SettingHelpTopic.POCKET_AUTO_UNLOCK to FeatureHelpContent(
        title = "जेब से निकालते ही ऑटो अनलॉक",
        whatItDoes = "जेब सेंसर से लॉक हुआ फोन बाहर निकालते ही रोशनी में अपने आप खुल जाता है।",
        exampleUse = "जेब से निकालते ही मैप या फोटो तुरंत चलने लगती है।",
        adviceTip = "यदि बच्चों को फोन देते हैं तो इसे बंद रखें।"
    ),
    SettingHelpTopic.POCKET_SENSITIVITY to FeatureHelpContent(
        title = "जेब पहचानने में लगने वाला समय",
        whatItDoes = "तय करता है कि ऊपरी सेंसर कितनी देर ढका रहने पर ऑटो-लॉक चालू हो।",
        exampleUse = "हाथ निकलने पर गलती से लॉक हो रहा हो तो समय 1.2s+ बढ़ा दें।",
        adviceTip = "डिफ़ॉल्ट 0.7s अधिकांश लोगों के लिए सही है।"
    ),
    SettingHelpTopic.ORIENTATION_FILTER to FeatureHelpContent(
        title = "मेज़ पर सपाट होने पर गलत लॉक रोकें",
        whatItDoes = "फोन मेज़ पर सीधा रखा होने पर ऊपर से हाथ गुजरने से पॉकेट-लॉक नहीं होने देता।",
        exampleUse = "डेस्क पर काम करते समय छाया पड़ने पर भी स्क्रीन लॉक नहीं होगी।",
        adviceTip = "हमेशा चालू रखना उचित है।"
    ),
    SettingHelpTopic.PAUSE_LANDSCAPE to FeatureHelpContent(
        title = "आड़ी स्क्रीन (वीडियो/गेम) में रोकें",
        whatItDoes = "फोन को आड़ा (Landscape) पकड़कर वीडियो देखते या गेम खेलते समय पॉकेट लॉक रोक देता है।",
        exampleUse = "गेम खेलते समय स्पीकर सेंसर पर उंगली लगने से स्क्रीन लॉक नहीं होगी।",
        adviceTip = "वीडियो और गेमिंग के लिए चालू रखें।"
    ),
    SettingHelpTopic.PAUSE_CHARGING to FeatureHelpContent(
        title = "चार्जिंग के समय ऑटो-लॉक रोकें",
        whatItDoes = "चार्जर केबल जुड़े रहने पर पॉकेट ऑटो-लॉक को बंद रखता है।",
        exampleUse = "डेस्क स्टैंड या कार डॉक पर चार्जिंग में काम आता है।",
        adviceTip = "पावर बैंक के साथ जेब में रखते समय बंद रखें।"
    ),
    SettingHelpTopic.FLIP_FACE_DOWN to FeatureHelpContent(
        title = "फोन उल्टा रखने पर लॉक",
        whatItDoes = "स्क्रीन को नीचे की ओर मेज़ पर रखते ही तुरंत टच लॉक कर देता है।",
        exampleUse = "मीटिंग में फोन उल्टा रखें और स्क्रीन तुरंत लॉक हो जाएगी।",
        adviceTip = "तेज़ और स्मार्ट तरीका है।"
    ),
    SettingHelpTopic.CALL_AUTO_UNLOCK to FeatureHelpContent(
        title = "कॉल आने पर अपने आप अनलॉक",
        whatItDoes = "रिंग बजते ही लॉक खोल देता है ताकि आप कॉल उठा सकें, और बात खत्म होते ही फिर से लॉक कर सकता है।",
        exampleUse = "जेब में लॉक रहते समय जरूरी कॉल आने पर तुरंत उत्तर दे पाएँ।",
        adviceTip = "अत्यंत उपयोगी सुरक्षा सुविधा।"
    ),
    SettingHelpTopic.SESSION_TIMER to FeatureHelpContent(
        title = "अपने आप खुलने का टाइमर",
        whatItDoes = "15, 30 या 60 मिनट बाद लॉक अपने आप हटा देता है (या बच्चों के टाइमर लॉक में PIN माँगता है)।",
        exampleUse = "सोते समय 30 मिनट का टाइमर लगाएँ ताकि बाद में लॉक साफ हो जाए।",
        adviceTip = "ज़रूरत न हो तो 'बंद' रखें।"
    ),
    SettingHelpTopic.APP_WATCH to FeatureHelpContent(
        title = "चुने हुए ऐप्स में ऑटो सुरक्षा",
        whatItDoes = "YouTube, Maps या Camera खुलते ही अपने आप लॉक बटन दिखाता है या लॉक करता है—और बाहर आने पर हट जाता है।",
        exampleUse = "बाइक पर Google Maps खोलते ही लॉक बटन दिखेगा; घर जाने पर अपने आप गायब होगा।",
        adviceTip = "बिना Accessibility के, सिर्फ डिवाइस पर Usage Access से सुरक्षित काम करता है।"
    ),
    SettingHelpTopic.SHAKE_SHORTCUT to FeatureHelpContent(
        title = "2 बार हिलाकर लॉक / अनलॉक",
        whatItDoes = "फोन को दो बार झटके से हिलाने पर टच लॉक या अनलॉक होता है।",
        exampleUse = "दस्ताने पहनकर या गीले हाथों से बिना बटन ढूँढे लॉक लगाएँ।",
        adviceTip = "किड्स सेफ-लॉक चालू होने पर हिलाकर अनलॉक बंद रहता है।"
    ),
    SettingHelpTopic.VOLUME_SHORTCUT to FeatureHelpContent(
        title = "वॉल्यूम बटन डबल-प्रेस शॉर्टकट",
        whatItDoes = "वॉल्यूम बटन को तेजी से दो बार दबाने पर लॉक चालू या बंद होता है।",
        exampleUse = "स्क्रीन गीली होने पर साइड बटन से आसानी से लॉक खोलें।",
        adviceTip = "आपातकालीन बटन विकल्प के रूप में अच्छा है।"
    ),
    SettingHelpTopic.LOW_BATTERY_BLACKOUT to FeatureHelpContent(
        title = "बैटरी कम होने पर ऑटो काली स्क्रीन",
        whatItDoes = "बैटरी 10%, 15% या 20% से नीचे जाने पर लॉक स्क्रीन अपने आप 92% काली होकर पावर बचाती है।",
        exampleUse = "सफर में बैटरी 15% बचे तो अपने आप गहरा होकर बैटरी लंबा चलाता है।",
        adviceTip = "15% चुनना बढ़िया संतुलन है।"
    ),
    SettingHelpTopic.CHARGER_AUTO_RULE to FeatureHelpContent(
        title = "चार्जर लगाने / निकालने के नियम",
        whatItDoes = "चार्जर लगाते ही ऑटो लॉक या चार्जर हटाते ही ऑटो अनलॉक कर सकता है।",
        exampleUse = "कार चार्जर लगाते ही मैप सुरक्षित लॉक हो जाएगा, उतरते ही अनलॉक।",
        adviceTip = "कार यात्रा के लिए उपयोगी।"
    ),
    SettingHelpTopic.KEEP_SCREEN_ON to FeatureHelpContent(
        title = "लॉक के समय स्क्रीन बुझने न दें",
        whatItDoes = "स्क्रीन टाइम-आउट रोकता है ताकि फोटो, QR कोड या मैप लगातार दिखता रहे।",
        exampleUse = "बोर्डिंग पास या रेसिपी देखते समय बार-बार स्क्रीन टैप करने की ज़रूरत नहीं।",
        adviceTip = "केवल जेब में ऑडियो सुनते समय अधिकतम बैटरी के लिए बंद कर सकते हैं।"
    ),
    SettingHelpTopic.HAPTICS_AND_BOOT to FeatureHelpContent(
        title = "वाइब्रेशन और रीस्टार्ट पर ऑटो-शुरू",
        whatItDoes = "लॉक/अनलॉक पर हल्का कंपन देता है और फोन रीस्टार्ट होने के बाद सुरक्षा तैयार रखता है।",
        exampleUse = "जेब में फोन रखते ही बिना देखे हल्के कंपन से समझ जाएँ कि लॉक लग गया।",
        adviceTip = "वाइब्रेशन चालू रखना अच्छा रहता है।"
    ),
    SettingHelpTopic.BACKUP_RESTORE to FeatureHelpContent(
        title = "सेटिंग्स फ़ाइल में सहेजें या वापस लाएँ",
        whatItDoes = "आपकी सभी सेटिंग्स और प्रोफाइल को एक छोटी ऑफ़लाइन फ़ाइल (.json) में सेव करता है।",
        exampleUse = "नए फोन पर जाने पर 1 क्लिक में सारी पुरानी सेटिंग्स वापस पाएँ।",
        adviceTip = "100% ऑफ़लाइन—सारा डेटा सिर्फ आपके पास रहता है।"
    )
)

val ArabicHelpMap: Map<SettingHelpTopic, FeatureHelpContent> = mapOf(
    SettingHelpTopic.MODE_EASY_PRO to FeatureHelpContent(
        title = "الوضع السهل مقابل وضع المحترفين",
        whatItDoes = "يعرض الوضع السهل أهم الأزرار اليومية الواضحة فقط، بينما يفتح وضع المحترفين جميع المؤشرات والقواعد المتقدمة.",
        exampleUse = "استخدم الوضع السهل لقفل الجيب، والفقاعة العائمة، وحماية الأطفال، والشاشة السوداء دون ازدحام.",
        adviceTip = "ابدأ بالوضع السهل، ويمكنك التبديل أو البحث عن أي ميزة متقدمة في أي وقت."
    ),
    SettingHelpTopic.UNLOCK_METHOD to FeatureHelpContent(
        title = "طريقة فتح قفل اللمس",
        whatItDoes = "يحدد الحركة المطلوبة لإلغاء قفل الشاشة بعد تفعيل القفل.",
        exampleUse = "اختر 'الضغط المطول' للجيب، أو 'رمز PIN' قبل إعطاء الهاتف للأطفال.",
        adviceTip = "الضغط المطول هو الأكثر أماناً ضد الفتح العرضي في الجيب."
    ),
    SettingHelpTopic.BIOMETRIC_UNLOCK to FeatureHelpContent(
        title = "الفتح بالبصمة أو الوجه",
        whatItDoes = "يضيف زر البصمة أو الوجه بجانب لوحة أرقام PIN.",
        exampleUse = "عند استعادة الهاتف من الأطفال، افتح القفل بإصبعك خلال ثانية واحدة.",
        adviceTip = "مفيد جداً عند تفعيل قفل PIN."
    ),
    SettingHelpTopic.EMERGENCY_HOLD_BYPASS to FeatureHelpContent(
        title = "فتح طوارئ بالضغط 5 ثوانٍ",
        whatItDoes = "يضع زر إنقاذ احتياطي على شاشة PIN يفتح القفل عند الضغط المستمر 5 ثوانٍ إذا نسيت الرمز.",
        exampleUse = "لن يعلق هاتفك أبداً حتى لو نسيت رمز PIN الخاص بك.",
        adviceTip = "أوقفه أو فعّل 'قفل أمان الأطفال' إذا كان الأطفال يستخدمون الهاتف."
    ),
    SettingHelpTopic.LOCK_COUNTDOWN to FeatureHelpContent(
        title = "مهلة قبل بدء القفل",
        whatItDoes = "ينتظر 3 أو 5 ثوانٍ قبل تفعيل الحظر لتتمكن من فتح التطبيق المطلوب.",
        exampleUse = "اضغط قفل ثم انتقل فوراً إلى الخرائط أو الصورة خلال 3 ثوانٍ.",
        adviceTip = "اتركه 'فوري (0ث)' للقفل بلمسة واحدة."
    ),
    SettingHelpTopic.HUD_TIMEOUT to FeatureHelpContent(
        title = "إخفاء شريط الفتح تلقائياً",
        whatItDoes = "يخفي رسالة الفتح بعد ثوانٍ قليلة لتظل الصورة أو الفيديو واضحاً تماماً.",
        exampleUse = "المس الشاشة في أي مكان عند رغبتك في إظهار زر الفتح مجدداً.",
        adviceTip = "3 ثوانٍ مناسبة لمعظم الاستخدامات."
    ),
    SettingHelpTopic.KIDS_SAFETY to FeatureHelpContent(
        title = "قفل أمان الأطفال الصارم",
        whatItDoes = "يخفي زر الطوارئ (5ث) ويعطل الفتح بالهزّ حتى لا يتمكن الأطفال من تجاوز رمز PIN.",
        exampleUse = "أعطِ طفلك فيديو كرتون بأمان—لن يخرج من التطبيق أو يجري اتصالاً بالخطأ.",
        adviceTip = "موصى به جداً للآباء مع اختيار قفل PIN."
    ),
    SettingHelpTopic.WRONG_ATTEMPT_COOLDOWN to FeatureHelpContent(
        title = "مؤقت انتظار عند إدخال PIN خطأ",
        whatItDoes = "يوقف لوحة الأرقام مؤقتاً (15ث بعد 5 أخطاء و30ث بعد 8+) عند تكرار المحاولات الخاطئة.",
        exampleUse = "يمنع الأطفال من التخمين العشوائي المستمر لرمز PIN.",
        adviceTip = "أبقِ هذه الميزة مفعّلة مع قفل PIN."
    ),
    SettingHelpTopic.KIDS_TIMER_EXPIRY to FeatureHelpContent(
        title = "القفل برمز الوالدين عند انتهاء الوقت",
        whatItDoes = "عند انتهاء مؤقت المشاهدة، يقفل الشاشة برمز الوالدين بدلاً من فتحها.",
        exampleUse = "اضبط مؤقتاً لـ 15 دقيقة من الكرتون—بعدها يتوقف التفاعل حتى تدخل رمز PIN.",
        adviceTip = "ممتاز لتنظيم وقت شاشة الأطفال."
    ),
    SettingHelpTopic.OVERLAY_STYLE to FeatureHelpContent(
        title = "مظهر الشاشة أثناء القفل",
        whatItDoes = "يختار بين شاشة صافية 100% أو تظليل خفيف أو شاشة داكنة لتوفير البطارية.",
        exampleUse = "اختر الصافية للصور والخرائط، والداكنة للاستماع بالصوت في الجيب.",
        adviceTip = "الوضع الداكن يوفر بطارية كبيرة على شاشات AMOLED."
    ),
    SettingHelpTopic.LOCK_BRIGHTNESS to FeatureHelpContent(
        title = "سطوع الشاشة أثناء القفل",
        whatItDoes = "يعدل سطوع الشاشة مؤقتاً خلال القفل فقط ثم يعيده لطبيعته عند الفتح.",
        exampleUse = "اختر 100% للملاحة تحت الشمس، أو 5% ليلاً عند تشغيل مقاطع الصوت.",
        adviceTip = "'النظام' يبقي السطوع كما هو."
    ),
    SettingHelpTopic.EDGE_BORDER to FeatureHelpContent(
        title = "إطار الحواف المضيء",
        whatItDoes = "يرسم خطاً ملوناً أو زوايا خفيفة حول الشاشة لتعرف بنظرة واحدة أن القفل يعمل.",
        exampleUse = "في الشاشة الصافية يذكّرك الإطار الأخضر بأن اللمس محظور.",
        adviceTip = "إطار الزوايا أنيق ولا يشتت المشاهدة."
    ),
    SettingHelpTopic.OLED_CLOCK to FeatureHelpContent(
        title = "ساعة ونسبة البطارية على الشاشة الداكنة",
        whatItDoes = "يعرض ساعة خفيفة ومستوى البطارية في المنتصف أثناء تعتيم الشاشة.",
        exampleUse = "تحقق من الوقت دون إلغاء القفل.",
        adviceTip = "رائع مع الشاشة الداكنة أو ستار الخصوصية."
    ),
    SettingHelpTopic.PARTIAL_TOUCH_ZONE to FeatureHelpContent(
        title = "فتح جزء محدد وقفل الباقي",
        whatItDoes = "يترك مربعاً تختاره قابلاً للمس (مثل الفيديو أو الخريطة) ويحظر باقي الشاشة.",
        exampleUse = "أبقِ نافذة الفيديو فعالة مع حظر زر الرجوع والإعلانات الجانبية.",
        adviceTip = "مثالي للخرائط والمشاهدة بيد واحدة."
    ),
    SettingHelpTopic.PRIVACY_VEIL to FeatureHelpContent(
        title = "إخفاء الشاشة عن المتطفلين (سوداء 99%)",
        whatItDoes = "يسوّد الشاشة بالكامل تقريباً لحماية خصوصيتك في المواصلات مع زر 'نظرة 3ث'.",
        exampleUse = "استمع ليوتيوب أو احمِ محادثاتك في القطار واضغط 'نظرة 3ث' متى احتجت.",
        adviceTip = "مثالي للتنقل اليومي والخصوصية."
    ),
    SettingHelpTopic.STEALTH_BLACKOUT to FeatureHelpContent(
        title = "قفل مخفي تماماً (بدون إضاءة)",
        whatItDoes = "يخفي الإطار والساعة لتبدو الشاشة وكأنها مطفأة تماماً—المس لإظهار خيار الفتح.",
        exampleUse = "مفيد في الغرف المظلمة أو الجيب لمنع أي إضاءة جانبية.",
        adviceTip = "اجمعه مع شاشة الخصوصية لنتيجة فائقة."
    ),
    SettingHelpTopic.FLOATING_BUBBLE to FeatureHelpContent(
        title = "زر القفل العائم",
        whatItDoes = "يضع زراً صغيراً متحركاً فوق كل التطبيقات لقفل اللمس بضغطة واحدة فوراً.",
        exampleUse = "افتح المعرض أو الخرائط واضغط الزر العائم مباشرة دون الرجوع للتطبيق.",
        adviceTip = "أسهل طريقة للقفل اليومي السريع."
    ),
    SettingHelpTopic.BUBBLE_AUTO_TUCK to FeatureHelpContent(
        title = "سحب الزر للحافة بعد 3 ثوانٍ",
        whatItDoes = "يبعد الزر العائم نصف خطوة نحو الحافة ويجعله باهتاً حتى لا يغطي المحتوى.",
        exampleUse = "يترك الصور والترجمة واضحة دون عائق.",
        adviceTip = "يُنصح بإبقائه مفعلاً."
    ),
    SettingHelpTopic.BUBBLE_DOUBLE_TAP_HIDE to FeatureHelpContent(
        title = "نقر مزدوج لإخفاء الزر مؤقتاً",
        whatItDoes = "انقر مرتين على الزر العائم لإخفائه مؤقتاً، وأعده من الإشعار عند الحاجة.",
        exampleUse = "إذا غطى الزر زراً مهماً أسفله، أخفه بنقرتين سريعاً.",
        adviceTip = "اختصار عملي ومريح."
    ),
    SettingHelpTopic.POCKET_AUTO_LOCK to FeatureHelpContent(
        title = "قفل تلقائي داخل الجيب",
        whatItDoes = "يستخدم مستشعر السماعة العلوية لقفل الشاشة تلقائياً بمجرد وضع الهاتف في جيبك.",
        exampleUse = "أدخل هاتفك في جيبك أثناء فتح صورة دون قلق من اللمسات الخاطئة.",
        adviceTip = "فعّله لحماية تلقائية دون لمس أي زر."
    ),
    SettingHelpTopic.POCKET_AUTO_UNLOCK to FeatureHelpContent(
        title = "فتح تلقائي عند الإخراج من الجيب",
        whatItDoes = "يلغي قفل الجيب تلقائياً فور إخراج الهاتف إلى الضوء.",
        exampleUse = "اسحب الهاتف وتصفح الخريطة فوراً دون انتظار.",
        adviceTip = "اتركه مغلقاً إذا كنت تشارك الهاتف مع الأطفال."
    ),
    SettingHelpTopic.POCKET_SENSITIVITY to FeatureHelpContent(
        title = "سرعة استشعار الجيب",
        whatItDoes = "يحدد المدة اللازمة لتغطية المستشعر قبل بدء القفل التلقائي.",
        exampleUse = "ارفعه إلى 1.2ث+ إذا كان مرور اليد السريع يفعل القفل بالخطأ.",
        adviceTip = "القيمة الافتراضية 0.7ث متوازنة لمعظم المستخدمين."
    ),
    SettingHelpTopic.ORIENTATION_FILTER to FeatureHelpContent(
        title = "تجاهل القفل عندما يكون الهاتف مسطحاً",
        whatItDoes = "يمنع قفل الجيب التلقائي حين يكون الهاتف موضوعاً بشكل أفقي على الطاولة.",
        exampleUse = "لا يقفل الهاتف عند مرور يدك فوقه على المكتب.",
        adviceTip = "موصى بتفعيله دائماً."
    ),
    SettingHelpTopic.PAUSE_LANDSCAPE to FeatureHelpContent(
        title = "إيقاف قفل الجيب في الفيديو والألعاب",
        whatItDoes = "يوقف قفل الجيب مؤقتاً عند مسك الهاتف بالعرض (الوضع الأفقي).",
        exampleUse = "يمنع القفل بالخطأ عند تغطية المستشعر بيدك أثناء اللعب.",
        adviceTip = "ممتاز لمحبي الألعاب والفيديو."
    ),
    SettingHelpTopic.PAUSE_CHARGING to FeatureHelpContent(
        title = "إيقاف القفل التلقائي أثناء الشحن",
        whatItDoes = "يعطل قفل الجيب التلقائي طالما الهاتف موصل بالشاحن.",
        exampleUse = "مريح على حامل السيارة أو المكتب أثناء الشحن.",
        adviceTip = "أوقفه إذا كنت تضعه مع باور بانك في الجيب."
    ),
    SettingHelpTopic.FLIP_FACE_DOWN to FeatureHelpContent(
        title = "اقلب الهاتف على وجهه للقفل",
        whatItDoes = "يقفل اللمس فوراً عند قلب الشاشة لأسفل على أي سطح مستوٍ.",
        exampleUse = "أثناء الاجتماع، اقلب الهاتف لقفل وحماية التطبيق المفتوح.",
        adviceTip = "حركة ذكية وسريعة."
    ),
    SettingHelpTopic.CALL_AUTO_UNLOCK to FeatureHelpContent(
        title = "فتح القفل تلقائياً عند الاتصال",
        whatItDoes = "يفتح القفل عند رنين مكالمة واردة لتتمكن من الرد فوراً (ويعيد القفل بعد انتهائها إذا رغبت).",
        exampleUse = "لن تفوتك مكالمة مهمة بسبب شاشة القفل.",
        adviceTip = "مهم جداً لمن يستخدم قفل الجيب باستمرار."
    ),
    SettingHelpTopic.SESSION_TIMER to FeatureHelpContent(
        title = "مؤقت فتح الجلسة تلقائياً",
        whatItDoes = "ينهي القفل بعد 15 أو 30 أو 60 دقيقة (أو يقفل برمز الوالدين مع وضع الأطفال).",
        exampleUse = "استمع لمقطع صوتي قبل النوم واضبطه على 30 دقيقة.",
        adviceTip = "اتركه مغلقاً في الاستخدام العادي."
    ),
    SettingHelpTopic.APP_WATCH to FeatureHelpContent(
        title = "حماية تلقائية لتطبيقات تختارها",
        whatItDoes = "يراقب محلياً فتح تطبيقاتك المختارة مثل يوتيوب والخرائط ليُظهر زر القفل أو يقفل تلقائياً.",
        exampleUse = "عند فتح خرائط جوجل على الدراجة، يظهر زر القفل وحده ويختفي عند الخروج.",
        adviceTip = "يعتمد على إحصاءات الاستخدام المحلية فقط دون خدمة إمكانية الوصول."
    ),
    SettingHelpTopic.SHAKE_SHORTCUT to FeatureHelpContent(
        title = "هزّ الهاتف مرتين للقفل / الفتح",
        whatItDoes = "يتيح القفل أو الفتح بهزّ الهاتف مرتين برفق.",
        exampleUse = "أثناء ارتداء القفازات، هزّ الهاتف مرتين لقفل اللمس فوراً.",
        adviceTip = "يتعطل الفتح بالهزّ تلقائياً مع قفل أمان الأطفال."
    ),
    SettingHelpTopic.VOLUME_SHORTCUT to FeatureHelpContent(
        title = "اختصار الضغط المزدوج لزر الصوت",
        whatItDoes = "الضغط مرتين سريعاً على زر الصوت يقفل أو يفتح الشاشة.",
        exampleUse = "مفيد جداً إذا كانت يدك مبللة ولا تستجيب للشاشة.",
        adviceTip = "خيار احتياطي ممتاز بالأزرار الجانبية."
    ),
    SettingHelpTopic.LOW_BATTERY_BLACKOUT to FeatureHelpContent(
        title = "تعتيم تلقائي عند ضعف البطارية",
        whatItDoes = "عند انخفاض البطارية عن 10% أو 15% أو 20% يعتم القفل إلى 92% لحفظ الشحن.",
        exampleUse = "يطيل عمر البطارية تلقائياً في السفر الطويل.",
        adviceTip = "نسبة 15% اختيار متوازن."
    ),
    SettingHelpTopic.CHARGER_AUTO_RULE to FeatureHelpContent(
        title = "قواعد توصيل وفصل الشاحن",
        whatItDoes = "يقفل الشاشة عند شبك الشاحن أو يفتحها عند فصل السلك تلقائياً.",
        exampleUse = "مثالي لحامل شاحن السيارة للخرائط.",
        adviceTip = "جرّبه إذا كنت تستخدم الهاتف في السيارة."
    ),
    SettingHelpTopic.KEEP_SCREEN_ON to FeatureHelpContent(
        title = "منع انطفاء الشاشة أثناء القفل",
        whatItDoes = "يبقي الشاشة مضاءة طوال فترة القفل لعرض الصور أو التذاكر أو الخرائط باستمرار.",
        exampleUse = "عرض تذكرة السفر أو الوصفة دون انطفاء الشاشة كل 30 ثانية.",
        adviceTip = "أوقفه فقط لتوفير أقصى بطارية عند الاستماع من الجيب."
    ),
    SettingHelpTopic.HAPTICS_AND_BOOT to FeatureHelpContent(
        title = "الاهتزاز والتشغيل بعد إعادة التشغيل",
        whatItDoes = "يعطي اهتزازاً خفيفاً لتأكيد القفل ويعيد تجهيز الخدمة بعد تشغيل الهاتف.",
        exampleUse = "تأكد من تفعيل القفل في جيبك بالاهتزاز دون النظر للهاتف.",
        adviceTip = "إبقاء الاهتزاز مفعلاً يمنحك ثقة فورية."
    ),
    SettingHelpTopic.BACKUP_RESTORE to FeatureHelpContent(
        title = "حفظ الإعدادات أو استعادتها",
        whatItDoes = "يحفظ جميع ملفاتك وإعداداتك في ملف محلي (.json) لاستعادته في أي وقت.",
        exampleUse = "عند تغيير هاتفك، استرجع كل تفضيلاتك بضغطة زر.",
        adviceTip = "دون إنترنت 100% وتبقى ملفاتك على جهازك."
    )
)

val SpanishHelpMap: Map<SettingHelpTopic, FeatureHelpContent> = mapOf(
    SettingHelpTopic.MODE_EASY_PRO to FeatureHelpContent(
        title = "Modo Fácil vs. Modo Pro",
        whatItDoes = "El Modo Fácil muestra solo los botones esenciales de uso diario. El Modo Pro despliega todos los ajustes finos, sensores y atajos.",
        exampleUse = "Quédate en Modo Fácil si solo quieres bloqueo en bolsillo, burbuja flotante, modo niños o pantalla negra.",
        adviceTip = "Empieza en Modo Fácil; si buscas algo específico, el buscador también encuentra funciones Pro."
    ),
    SettingHelpTopic.UNLOCK_METHOD to FeatureHelpContent(
        title = "Cómo desbloquear el táctil",
        whatItDoes = "Elige la acción necesaria para volver a activar los toques en pantalla.",
        exampleUse = "Usa 'Mantener' o 'Deslizar' para el bolsillo, o 'PIN de 4 dígitos' antes de prestar el celular a niños.",
        adviceTip = "Mantener presionado es lo más seguro contra aperturas accidentales en el bolsillo."
    ),
    SettingHelpTopic.BIOMETRIC_UNLOCK to FeatureHelpContent(
        title = "Desbloqueo con Huella / Rostro",
        whatItDoes = "Muestra un botón de huella o rostro junto al teclado PIN.",
        exampleUse = "Desbloquea en 1 segundo con tu dedo sin teclear el PIN cuando los niños te devuelven el teléfono.",
        adviceTip = "Muy cómodo si usas bloqueo con PIN a menudo."
    ),
    SettingHelpTopic.EMERGENCY_HOLD_BYPASS to FeatureHelpContent(
        title = "Salida de emergencia (5s)",
        whatItDoes = "Agrega un botón de mantener 5 segundos en la pantalla PIN por si olvidas tu clave.",
        exampleUse = "Nunca quedarás atrapado aunque olvides el PIN.",
        adviceTip = "Desactívalo o enciende 'Modo Seguro Niños' si prestas el teléfono a menores."
    ),
    SettingHelpTopic.LOCK_COUNTDOWN to FeatureHelpContent(
        title = "Espera antes de bloquear",
        whatItDoes = "Cuenta 3 o 5 segundos antes de bloquear toques tras pulsar Bloquear.",
        exampleUse = "Pulsa Bloquear y abre tu mapa o galería dentro de esos 3 segundos.",
        adviceTip = "Deja 'Instantáneo (0s)' para bloqueo inmediato."
    ),
    SettingHelpTopic.HUD_TIMEOUT to FeatureHelpContent(
        title = "Ocultar barra de desbloqueo",
        whatItDoes = "Oculta el aviso de desbloqueo tras unos segundos para ver fotos o videos sin estorbos.",
        exampleUse = "Toca cualquier parte de la pantalla cuando quieras ver el botón de desbloqueo otra vez.",
        adviceTip = "3 segundos es ideal para uso diario."
    ),
    SettingHelpTopic.KIDS_SAFETY to FeatureHelpContent(
        title = "Modo Seguro para Niños",
        whatItDoes = "Oculta la salida de emergencia de 5s y desactiva desbloquear agitando para que no salten tu PIN.",
        exampleUse = "Pon caricaturas y activa el PIN: los niños verán el video sin poder salir ni borrar nada.",
        adviceTip = "Recomendado para padres junto con desbloqueo por PIN."
    ),
    SettingHelpTopic.WRONG_ATTEMPT_COOLDOWN to FeatureHelpContent(
        title = "Pausa tras PIN incorrecto",
        whatItDoes = "Bloquea temporalmente el teclado PIN tras varios intentos fallidos (15s tras 5, 30s tras 8+).",
        exampleUse = "Evita que alguien intente adivinar tu PIN probando números sin parar.",
        adviceTip = "Mantenlo encendido si usas PIN."
    ),
    SettingHelpTopic.KIDS_TIMER_EXPIRY to FeatureHelpContent(
        title = "Pedir PIN al acabar el tiempo",
        whatItDoes = "Cuando vence el temporizador, bloquea la pantalla con PIN en lugar de desbloquearse sola.",
        exampleUse = "Pon 15 minutos de video infantil: al terminar el tiempo, pedirá tu PIN.",
        adviceTip = "Excelente para controlar el tiempo de pantalla."
    ),
    SettingHelpTopic.OVERLAY_STYLE to FeatureHelpContent(
        title = "Cómo se ve la pantalla bloqueada",
        whatItDoes = "Elige entre vidrio transparente (0%), tinte suave o negro AMOLED para ahorrar batería.",
        exampleUse = "Usa Cristal (0%) para ver fotos o mapas, y Negro (88%) para escuchar videos en el bolsillo.",
        adviceTip = "El modo oscuro ahorra mucha batería en pantallas OLED."
    ),
    SettingHelpTopic.LOCK_BRIGHTNESS to FeatureHelpContent(
        title = "Brillo solo mientras está bloqueado",
        whatItDoes = "Cambia el brillo únicamente durante el bloqueo y restaura el tuyo al desbloquear.",
        exampleUse = "Usa 100% bajo el sol en bici o 5% de noche para escuchar música.",
        adviceTip = "'Sistema' mantiene tu brillo actual."
    ),
    SettingHelpTopic.EDGE_BORDER to FeatureHelpContent(
        title = "Borde luminoso de aviso",
        whatItDoes = "Dibuja un marco o esquinas de color para saber al instante que el táctil está bloqueado.",
        exampleUse = "Con pantalla transparente 0%, el marco te recuerda por qué no responden los toques.",
        adviceTip = "Las esquinas son discretas en videos."
    ),
    SettingHelpTopic.OLED_CLOCK to FeatureHelpContent(
        title = "Reloj y batería en pantalla oscura",
        whatItDoes = "Muestra hora y porcentaje de batería sutiles cuando la pantalla está oscurecida.",
        exampleUse = "Mira la hora sin tener que desbloquear.",
        adviceTip = "Útil con Negro 88% o Escudo Privacidad."
    ),
    SettingHelpTopic.PARTIAL_TOUCH_ZONE to FeatureHelpContent(
        title = "Dejar solo una zona táctil libre",
        whatItDoes = "Permite tocar un área elegida (video o mapa) mientras bloquea todo el resto de la pantalla.",
        exampleUse = "Deja libre el reproductor de YouTube pero bloquea anuncios, botón atrás y bordes.",
        adviceTip = "Genial para usar mapas o videos con una mano."
    ),
    SettingHelpTopic.PRIVACY_VEIL to FeatureHelpContent(
        title = "Ocultar pantalla en público (99%)",
        whatItDoes = "Oscurece casi por completo la pantalla frente a curiosos en transporte público, con botón 'Ver 3s'.",
        exampleUse = "Escucha audio en el bus o protege un chat privado; toca 'Ver 3s' para echar un vistazo.",
        adviceTip = "Ideal para traslados diarios."
    ),
    SettingHelpTopic.STEALTH_BLACKOUT to FeatureHelpContent(
        title = "Pantalla totalmente oculta (sin luces)",
        whatItDoes = "Oculta bordes, insignia y reloj para que la pantalla parezca apagada—toca para desbloquear.",
        exampleUse = "Evita cualquier brillo molesto en habitaciones oscuras o en el bolsillo.",
        adviceTip = "Combínalo con Privacidad 99% para discreción total."
    ),
    SettingHelpTopic.FLOATING_BUBBLE to FeatureHelpContent(
        title = "Botón flotante de bloqueo",
        whatItDoes = "Muestra un botón móvil sobre cualquier app para bloquear la pantalla con un toque.",
        exampleUse = "Bloquea directo sobre Fotos, YouTube o Maps sin salir de ellos.",
        adviceTip = "La forma más cómoda de bloquear al instante."
    ),
    SettingHelpTopic.BUBBLE_AUTO_TUCK to FeatureHelpContent(
        title = "Apartar botón al borde tras 3s",
        whatItDoes = "Mueve la burbuja medio paso hacia el borde y la atenúa cuando no la tocas.",
        exampleUse = "Evita tapar botones o subtítulos de tus apps.",
        adviceTip = "Déjalo encendido para mayor comodidad."
    ),
    SettingHelpTopic.BUBBLE_DOUBLE_TAP_HIDE to FeatureHelpContent(
        title = "Doble toque para ocultar botón",
        whatItDoes = "Toca dos veces la burbuja para ocultarla temporalmente; recupérala desde la notificación.",
        exampleUse = "Si estorba justo encima de un botón, ocúltala en medio segundo.",
        adviceTip = "Atajo muy práctico."
    ),
    SettingHelpTopic.POCKET_AUTO_LOCK to FeatureHelpContent(
        title = "Auto-bloqueo en el bolsillo",
        whatItDoes = "Detecta con el sensor superior cuando guardas el celular y bloquea los toques automáticamente.",
        exampleUse = "Guarda tu entrada o foto abierta en el bolsillo sin toques accidentales.",
        adviceTip = "Actívalo para protección automática sin pulsar botones."
    ),
    SettingHelpTopic.POCKET_AUTO_UNLOCK to FeatureHelpContent(
        title = "Auto-desbloqueo al sacar",
        whatItDoes = "Desbloquea automáticamente al sacar el celular del bolsillo (solo si se bloqueó por bolsillo).",
        exampleUse = "Saca el teléfono y mira tu mapa al instante sin esperar.",
        adviceTip = "Déjalo apagado si prestas el móvil a niños."
    ),
    SettingHelpTopic.POCKET_SENSITIVITY to FeatureHelpContent(
        title = "Demora de detección de bolsillo",
        whatItDoes = "Ajusta cuánto tiempo debe cubrirse el sensor antes de bloquearse automáticamente.",
        exampleUse = "Súbelo a 1.2s+ si se activa por error al limpiar la parte superior.",
        adviceTip = "0.7s funciona bien para la mayoría."
    ),
    SettingHelpTopic.ORIENTATION_FILTER to FeatureHelpContent(
        title = "Pausar si está plano en la mesa",
        whatItDoes = "Evita bloqueos por sensor cuando el teléfono descansa plano boca arriba en un escritorio.",
        exampleUse = "Pasa la mano por encima sin que se bloquee por accidente.",
        adviceTip = "Muy recomendable mantenerlo activado."
    ),
    SettingHelpTopic.PAUSE_LANDSCAPE to FeatureHelpContent(
        title = "Pausar en videos o juegos horizontales",
        whatItDoes = "Desactiva el auto-bloqueo de bolsillo cuando sostienes el móvil en horizontal.",
        exampleUse = "Tus dedos jugando no activarán el bloqueo por cubrir el sensor superior.",
        adviceTip = "Imprescindible para juegos y películas."
    ),
    SettingHelpTopic.PAUSE_CHARGING to FeatureHelpContent(
        title = "Pausar mientras carga",
        whatItDoes = "Desactiva el auto-bloqueo de bolsillo mientras el cable cargador está conectado.",
        exampleUse = "Cómodo si lo usas en soporte de coche o mesa cargando.",
        adviceTip = "Apágalo si cargas con batería portátil dentro de un bolso."
    ),
    SettingHelpTopic.FLIP_FACE_DOWN to FeatureHelpContent(
        title = "Voltear boca abajo para bloquear",
        whatItDoes = "Bloquea el táctil automáticamente al apoyar el móvil con la pantalla hacia abajo.",
        exampleUse = "Pon el celular boca abajo en la mesa durante una reunión y quedará protegido.",
        adviceTip = "Gesto rápido e intuitivo."
    ),
    SettingHelpTopic.CALL_AUTO_UNLOCK to FeatureHelpContent(
        title = "Desbloquear en llamadas entrantes",
        whatItDoes = "Abre el bloqueo al sonar una llamada para contestar rápido (y puede volver a bloquear al colgar).",
        exampleUse = "Nunca pierdas una llamada por tener el táctil bloqueado en el bolsillo.",
        adviceTip = "Muy útil en el uso diario."
    ),
    SettingHelpTopic.SESSION_TIMER to FeatureHelpContent(
        title = "Temporizador de desbloqueo",
        whatItDoes = "Termina el bloqueo tras 15, 30 o 60 minutos (o pide PIN infantil si activaste esa regla).",
        exampleUse = "Escucha un podcast al dormir y deja que se apague solo a los 30 minutos.",
        adviceTip = "Déjalo apagado salvo uso cronometrado."
    ),
    SettingHelpTopic.APP_WATCH to FeatureHelpContent(
        title = "Protección automática por app",
        whatItDoes = "Detecta localmente cuando abres apps elegidas (YouTube, Maps, Cámara) para mostrar la burbuja o bloquear.",
        exampleUse = "Abre Google Maps y el botón de bloqueo aparece solo—y desaparece al salir.",
        adviceTip = "Usa acceso de uso local sin Accesibilidad ni Internet."
    ),
    SettingHelpTopic.SHAKE_SHORTCUT to FeatureHelpContent(
        title = "Agitar 2 veces para bloquear / abrir",
        whatItDoes = "Bloquea o desbloquea los toques agitando suavemente el teléfono dos veces.",
        exampleUse = "Con guantes puestos, agita dos veces sin buscar botones en pantalla.",
        adviceTip = "Agitar para desbloquear se desactiva solo en Modo Seguro Niños."
    ),
    SettingHelpTopic.VOLUME_SHORTCUT to FeatureHelpContent(
        title = "Doble toque en botón de Volumen",
        whatItDoes = "Pulsar dos veces rápido subir o bajar volumen alterna o abre el bloqueo.",
        exampleUse = "Abre el bloqueo con teclas físicas si tienes las manos mojadas.",
        adviceTip = "Buen respaldo físico."
    ),
    SettingHelpTopic.LOW_BATTERY_BLACKOUT to FeatureHelpContent(
        title = "Ahorro oscuro con batería baja",
        whatItDoes = "Oscurece la pantalla bloqueada al 92% cuando la batería cae bajo 10%, 15% o 20%.",
        exampleUse = "Alarga tu batería automáticamente en viajes largos.",
        adviceTip = "15% es una excelente red de seguridad."
    ),
    SettingHelpTopic.CHARGER_AUTO_RULE to FeatureHelpContent(
        title = "Reglas al conectar / quitar cargador",
        whatItDoes = "Bloquea automáticamente al enchufar o desbloquea al desconectar el cargador.",
        exampleUse = "Conecta en el coche para fijar el mapa y desbloquea al bajar.",
        adviceTip = "Práctico para soportes de coche."
    ),
    SettingHelpTopic.KEEP_SCREEN_ON to FeatureHelpContent(
        title = "Evitar que la pantalla se apague",
        whatItDoes = "Mantiene la pantalla encendida mientras está bloqueada para que fotos, boletos o mapas sigan visibles.",
        exampleUse = "Muestra tu boleto QR o receta sin estar tocando la pantalla cada 30 segundos.",
        adviceTip = "Apágalo solo si escuchas audio en el bolsillo y quieres máximo ahorro."
    ),
    SettingHelpTopic.HAPTICS_AND_BOOT to FeatureHelpContent(
        title = "Vibración y auto-inicio tras reinicio",
        whatItDoes = "Vibra suavemente al bloquear/desbloquear y deja lista la protección al encender el teléfono.",
        exampleUse = "Siente una pequeña vibración en el bolsillo para saber que ya está bloqueado.",
        adviceTip = "Deja la vibración activada."
    ),
    SettingHelpTopic.BACKUP_RESTORE to FeatureHelpContent(
        title = "Guardar o restaurar copia",
        whatItDoes = "Guarda todos tus ajustes y perfiles en un archivo local (.json) para restaurarlos cuando quieras.",
        exampleUse = "Cambia de móvil y recupera todos tus perfiles en 2 segundos.",
        adviceTip = "100% sin conexión—tus datos nunca salen del dispositivo."
    )
)

val IndonesianHelpMap: Map<SettingHelpTopic, FeatureHelpContent> = mapOf(
    SettingHelpTopic.MODE_EASY_PRO to FeatureHelpContent(
        title = "Mode Mudah vs. Mode Pro",
        whatItDoes = "Mode Mudah hanya menampilkan tombol harian yang paling penting. Mode Pro membuka semua pengatur sensor, batas tepi, dan pintasan lanjutan.",
        exampleUse = "Gunakan Mode Mudah bila hanya butuh kunci saku, tombol mengambang, kunci anak, atau layar hitam.",
        adviceTip = "Mulai dari Mode Mudah; fitur Pro tetap bisa dicari lewat kolom pencarian kapan saja."
    ),
    SettingHelpTopic.UNLOCK_METHOD to FeatureHelpContent(
        title = "Cara Membuka Kunci Sentuh",
        whatItDoes = "Memilih gerakan atau PIN yang diperlukan untuk membuka kembali layar.",
        exampleUse = "Pakai 'Tahan' untuk di saku, atau pilih 'PIN 4-Digit' sebelum meminjamkan HP ke anak.",
        adviceTip = "Tahan tombol paling aman dari sentuhan tidak sengaja di saku."
    ),
    SettingHelpTopic.BIOMETRIC_UNLOCK to FeatureHelpContent(
        title = "Buka dengan Sidik Jari / Wajah",
        whatItDoes = "Menambahkan tombol sidik jari atau wajah di samping papan PIN.",
        exampleUse = "Buka kunci dalam 1 detik dengan jari Anda saat mengambil HP dari anak.",
        adviceTip = "Sangat praktis bila sering memakai kunci PIN."
    ),
    SettingHelpTopic.EMERGENCY_HOLD_BYPASS to FeatureHelpContent(
        title = "Buka Darurat Tahan 5 Detik",
        whatItDoes = "Menampilkan tombol tahan 5 detik di layar PIN seandainya Anda lupa PIN.",
        exampleUse = "HP tetap bisa dibuka meskipun lupa PIN.",
        adviceTip = "Matikan atau aktifkan Mode Aman Anak agar anak tidak bisa menahan tombol 5 detik."
    ),
    SettingHelpTopic.LOCK_COUNTDOWN to FeatureHelpContent(
        title = "Jeda Sebelum Mengunci",
        whatItDoes = "Menunggu 3 atau 5 detik sebelum mengunci sentuhan setelah tombol Kunci ditekan.",
        exampleUse = "Tekan Kunci lalu buka peta atau foto dalam 3 detik.",
        adviceTip = "Pilih 'Instan (0d)' untuk langsung mengunci."
    ),
    SettingHelpTopic.HUD_TIMEOUT to FeatureHelpContent(
        title = "Sembunyikan Panel Buka Otomatis",
        whatItDoes = "Menyembunyikan tombol buka setelah beberapa detik agar foto atau video tampil bersih.",
        exampleUse = "Ketuk layar di mana saja kapan pun ingin memunculkan tombol buka lagi.",
        adviceTip = "3 detik pas untuk pemakaian harian."
    ),
    SettingHelpTopic.KIDS_SAFETY to FeatureHelpContent(
        title = "Kunci Aman Anak Ketat",
        whatItDoes = "Menyembunyikan tombol darurat 5 detik dan mematikan buka lewat goyangan agar anak tidak bisa keluar dari PIN.",
        exampleUse = "Putar kartun untuk anak dengan PIN—mereka tidak bisa keluar aplikasi atau menghapus foto.",
        adviceTip = "Wajib bagi orang tua bersama kunci PIN."
    ),
    SettingHelpTopic.WRONG_ATTEMPT_COOLDOWN to FeatureHelpContent(
        title = "Jeda Saat Salah Masukkan PIN",
        whatItDoes = "Menghentikan papan PIN sementara setelah beberapa kali salah (15d setelah 5x, 30d setelah 8+).",
        exampleUse = "Mencegah anak atau teman menebak-nebak PIN terus menerus.",
        adviceTip = "Biarkan aktif bila memakai PIN."
    ),
    SettingHelpTopic.KIDS_TIMER_EXPIRY to FeatureHelpContent(
        title = "Kunci PIN Saat Waktu Habis",
        whatItDoes = "Saat timer berakhir, layar langsung terkunci dengan PIN orang tua alih-alih terbuka.",
        exampleUse = "Pasang batas tonton 15 menit—setelah habis, layar terkunci menunggu PIN Anda.",
        adviceTip = "Bagus untuk membatasi durasi layar anak."
    ),
    SettingHelpTopic.OVERLAY_STYLE to FeatureHelpContent(
        title = "Tampilan Layar Saat Terkunci",
        whatItDoes = "Mengatur layar tetap jernih 100%, agak gelap, atau hitam pekat untuk hemat baterai.",
        exampleUse = "Pakai Jernih (0%) untuk foto dan peta; Gelap (88%) saat mendengar video di saku.",
        adviceTip = "Mode gelap sangat menghemat baterai OLED."
    ),
    SettingHelpTopic.LOCK_BRIGHTNESS to FeatureHelpContent(
        title = "Kecerahan Khusus Saat Terkunci",
        whatItDoes = "Mengubah kecerahan hanya selama terkunci, lalu kembali normal saat dibuka.",
        exampleUse = "Pakai 100% di bawah sinar matahari untuk peta motor, atau 5% di malam hari.",
        adviceTip = "'Sistem' mempertahankan kecerahan normal Anda."
    ),
    SettingHelpTopic.EDGE_BORDER to FeatureHelpContent(
        title = "Garis Tepi Penanda Kunci",
        whatItDoes = "Menampilkan bingkai atau sudut warna agar Anda langsung tahu sentuhan sedang terkunci.",
        exampleUse = "Saat layar bening 0%, garis sudut mengingatkan bahwa kunci sedang aktif.",
        adviceTip = "Bingkai sudut tampak rapi saat menonton."
    ),
    SettingHelpTopic.OLED_CLOCK to FeatureHelpContent(
        title = "Jam & Baterai di Layar Gelap",
        whatItDoes = "Menampilkan jam dan sisa baterai tipis di tengah saat layar diredupkan.",
        exampleUse = "Lihat jam sekilas tanpa membuka kunci.",
        adviceTip = "Cocok dengan layar gelap 88% atau Layar Privasi."
    ),
    SettingHelpTopic.PARTIAL_TOUCH_ZONE to FeatureHelpContent(
        title = "Satu Area Bebas Sentuh, Sisanya Terkunci",
        whatItDoes = "Membiarkan kotak pilihan Anda tetap bisa disentuh sementara area lain terkunci.",
        exampleUse = "Biarkan layar video bisa dijeda sambil memblokir tombol kembali dan iklan samping.",
        adviceTip = "Pas untuk nonton atau buka peta satu tangan."
    ),
    SettingHelpTopic.PRIVACY_VEIL to FeatureHelpContent(
        title = "Layar Hitam Privasi (99%)",
        whatItDoes = "Menggelapkan layar hampir total dari pandangan orang sebelah di kendaraan umum, dengan tombol 'Intip 3d'.",
        exampleUse = "Dengarkan audio atau lindungi chat di bus; ketuk 'Intip 3d' kapan saja untuk mengecek.",
        adviceTip = "Terbaik untuk komuter dan privasi."
    ),
    SettingHelpTopic.STEALTH_BLACKOUT to FeatureHelpContent(
        title = "Kunci Tersembunyi Tanpa Cahaya",
        whatItDoes = "Menyembunyikan bingkai dan jam agar layar terlihat seperti mati total—ketuk layar untuk membuka.",
        exampleUse = "Mencegah silau sedikit pun di ruang gelap atau di dalam saku.",
        adviceTip = "Padukan dengan Layar Hitam Privasi 99%."
    ),
    SettingHelpTopic.FLOATING_BUBBLE to FeatureHelpContent(
        title = "Tombol Kunci Mengambang",
        whatItDoes = "Menaruh tombol kecil yang bisa digeser di atas semua aplikasi untuk kunci cepat 1 ketuk.",
        exampleUse = "Kunci langsung di Galeri, YouTube, atau Maps tanpa berpindah aplikasi.",
        adviceTip = "Cara tercepat untuk penggunaan sehari-hari."
    ),
    SettingHelpTopic.BUBBLE_AUTO_TUCK to FeatureHelpContent(
        title = "Geser ke Tepi Setelah 3 Detik",
        whatItDoes = "Menyisipkan setengah tombol ke pinggir layar saat tidak dipakai agar tidak menutupi konten.",
        exampleUse = "Tidak menghalangi teks video atau sudut foto.",
        adviceTip = "Disarankan tetap aktif."
    ),
    SettingHelpTopic.BUBBLE_DOUBLE_TAP_HIDE to FeatureHelpContent(
        title = "Ketuk 2x untuk Sembunyikan Sementara",
        whatItDoes = "Ketuk dua kali tombol mengambang agar hilang sementara; kembalikan dari notifikasi.",
        exampleUse = "Singkirkan tombol cepat jika menutupi ikon di bawahnya.",
        adviceTip = "Pintasan kecil yang sangat membantu."
    ),
    SettingHelpTopic.POCKET_AUTO_LOCK to FeatureHelpContent(
        title = "Otomatis Kunci di Dalam Saku",
        whatItDoes = "Mendeteksi lewat sensor atas saat ponsel masuk saku dan langsung mengunci sentuhan.",
        exampleUse = "Masukkan HP ke saku dengan tiket atau foto terbuka tanpa salah pencet.",
        adviceTip = "Aktifkan untuk perlindungan saku otomatis."
    ),
    SettingHelpTopic.POCKET_AUTO_UNLOCK to FeatureHelpContent(
        title = "Otomatis Terbuka Saat Dikeluarkan",
        whatItDoes = "Membuka kunci otomatis begitu ponsel keluar dari saku (hanya bila dikunci sensor saku).",
        exampleUse = "Langsung lihat peta begitu mengeluarkan ponsel dari saku.",
        adviceTip = "Matikan jika ponsel sering dipakai anak."
    ),
    SettingHelpTopic.POCKET_SENSITIVITY to FeatureHelpContent(
        title = "Waktu Deteksi Saku",
        whatItDoes = "Mengatur berapa lama sensor harus tertutup sebelum mengunci otomatis.",
        exampleUse = "Naikkan ke 1.2d+ bila sering terkunci tidak sengaja saat mengelap layar.",
        adviceTip = "Standar 0.7d cocok untuk mayoritas pengguna."
    ),
    SettingHelpTopic.ORIENTATION_FILTER to FeatureHelpContent(
        title = "Jeda Saat Ponsel Datar di Meja",
        whatItDoes = "Mencegah kunci otomatis saat HP tergeletak menghadap ke atas di meja.",
        exampleUse = "Tangan yang lewat di atas HP di meja kerja tidak akan memicu kunci.",
        adviceTip = "Sangat disarankan tetap aktif."
    ),
    SettingHelpTopic.PAUSE_LANDSCAPE to FeatureHelpContent(
        title = "Jeda Saat Video / Game Miring",
        whatItDoes = "Menonaktifkan kunci saku otomatis ketika ponsel dalam posisi horizontal.",
        exampleUse = "Jari tidak akan memicu kunci saat bermain game atau menonton film.",
        adviceTip = "Penting untuk gamer dan penonton video."
    ),
    SettingHelpTopic.PAUSE_CHARGING to FeatureHelpContent(
        title = "Jeda Kunci Saku Saat Mengisi Daya",
        whatItDoes = "Menunda kunci otomatis saku selama kabel pengisi daya terpasang.",
        exampleUse = "Nyaman saat ponsel diletakkan di holder mobil atau meja kerja.",
        adviceTip = "Matikan bila mengisi daya dengan power bank di dalam tas."
    ),
    SettingHelpTopic.FLIP_FACE_DOWN to FeatureHelpContent(
        title = "Balik Tengkurap untuk Mengunci",
        whatItDoes = "Langsung mengunci sentuhan saat ponsel diletakkan dengan layar menghadap ke bawah.",
        exampleUse = "Balikkan ponsel di meja saat rapat agar layar tetap terkunci aman.",
        adviceTip = "Gestur cepat yang praktis."
    ),
    SettingHelpTopic.CALL_AUTO_UNLOCK to FeatureHelpContent(
        title = "Terbuka Otomatis Saat Ada Telepon",
        whatItDoes = "Membuka kunci saat panggilan masuk berdering agar mudah menjawab (dan bisa mengunci lagi setelah selesai).",
        exampleUse = "Tidak pernah gagal mengangkat telepon karena tombol terjebak kunci.",
        adviceTip = "Sangat berguna untuk pemakaian harian."
    ),
    SettingHelpTopic.SESSION_TIMER to FeatureHelpContent(
        title = "Timer Buka Kunci Otomatis",
        whatItDoes = "Menghentikan kunci setelah 15, 30, atau 60 menit (atau meminta PIN anak jika fitur itu aktif).",
        exampleUse = "Putar audio sebelum tidur dengan batas waktu 30 menit.",
        adviceTip = "Biarkan mati bila tidak ingin batas waktu."
    ),
    SettingHelpTopic.APP_WATCH to FeatureHelpContent(
        title = "Penjaga Otomatis Aplikasi Pilihan",
        whatItDoes = "Mengecek secara lokal saat Anda membuka aplikasi pilihan (YouTube, Maps, Kamera) lalu memunculkan tombol atau mengunci.",
        exampleUse = "Buka Google Maps di motor, tombol kunci langsung siap—dan hilang saat keluar.",
        adviceTip = "Hanya memakai akses penggunaan lokal tanpa Accessibility atau internet."
    ),
    SettingHelpTopic.SHAKE_SHORTCUT to FeatureHelpContent(
        title = "Goyang 2x untuk Kunci / Buka",
        whatItDoes = "Mengunci atau membuka sentuhan hanya dengan menggoyang ponsel dua kali.",
        exampleUse = "Saat memakai sarung tangan, goyang dua kali tanpa mencari tombol layar.",
        adviceTip = "Buka lewat goyangan otomatis mati saat Kunci Aman Anak aktif."
    ),
    SettingHelpTopic.VOLUME_SHORTCUT to FeatureHelpContent(
        title = "Pintasan Tekan Ganda Tombol Volume",
        whatItDoes = "Menekan tombol volume 2x cepat mengunci atau membuka layar.",
        exampleUse = "Gunakan tombol fisik saat jari basah terkena air hujan.",
        adviceTip = "Cadangan tombol fisik yang sangat berguna."
    ),
    SettingHelpTopic.LOW_BATTERY_BLACKOUT to FeatureHelpContent(
        title = "Gelap Otomatis Saat Baterai Lemah",
        whatItDoes = "Saat baterai turun di bawah 10%, 15%, atau 20%, kunci layar otomatis menggelap 92% untuk menghemat daya.",
        exampleUse = "Menghemat sisa baterai secara otomatis di perjalanan jauh.",
        adviceTip = "Pilih 15% sebagai pengaman praktis."
    ),
    SettingHelpTopic.CHARGER_AUTO_RULE to FeatureHelpContent(
        title = "Aturan Pasang / Cabut Charger",
        whatItDoes = "Mengunci otomatis saat charger terhubung atau membuka saat dilepas.",
        exampleUse = "Pasang di charger mobil untuk mengunci peta dan buka otomatis saat tiba.",
        adviceTip = "Cocok untuk pengguna mobil."
    ),
    SettingHelpTopic.KEEP_SCREEN_ON to FeatureHelpContent(
        title = "Jaga Layar Tetap Menyala Saat Terkunci",
        whatItDoes = "Mencegah layar mati sendiri ketika terkunci agar foto, kode QR, atau peta selalu tampak.",
        exampleUse = "Tunjukkan tiket QR atau catatan belanja tanpa mengetuk layar terus-menerus.",
        adviceTip = "Matikan hanya jika ingin hemat baterai maksimal di saku."
    ),
    SettingHelpTopic.HAPTICS_AND_BOOT to FeatureHelpContent(
        title = "Getar & Siap Otomatis Setelah Reboot",
        whatItDoes = "Memberi getaran ringan saat mengunci dan menyiapkan perlindungan setelah ponsel dinyalakan ulang.",
        exampleUse = "Rasakan getaran halus di saku untuk memastikan layar sudah terkunci.",
        adviceTip = "Aktifkan getar agar terasa meyakinkan."
    ),
    SettingHelpTopic.BACKUP_RESTORE to FeatureHelpContent(
        title = "Simpan atau Pulihkan File Pengaturan",
        whatItDoes = "Menyimpan semua pengaturan dan profil ke file lokal (.json) yang bisa dimuat ulang kapan saja.",
        exampleUse = "Ganti HP dan kembalikan seluruh profil favorit dalam sekejap.",
        adviceTip = "100% offline—file hanya tersimpan di perangkat Anda."
    )
)

val PortugueseHelpMap: Map<SettingHelpTopic, FeatureHelpContent> = mapOf(
    SettingHelpTopic.MODE_EASY_PRO to FeatureHelpContent(
        title = "Modo Fácil vs. Modo Pro",
        whatItDoes = "O Modo Fácil mostra apenas os botões essenciais do dia a dia. O Modo Pro libera ajustes finos, sensores e atalhos avançados.",
        exampleUse = "Fique no Modo Fácil se só precisa de bloqueio no bolso, botão flutuante, trava infantil ou tela preta.",
        adviceTip = "Comece pelo Modo Fácil; a busca também encontra opções Pro quando precisar."
    ),
    SettingHelpTopic.UNLOCK_METHOD to FeatureHelpContent(
        title = "Como Desbloquear o Toque",
        whatItDoes = "Define o gesto ou código necessário para liberar o toque novamente.",
        exampleUse = "Use 'Segurar' para o bolso ou 'PIN de 4 dígitos' antes de entregar o celular às crianças.",
        adviceTip = "Segurar é o método mais seguro contra aberturas acidentais no bolso."
    ),
    SettingHelpTopic.BIOMETRIC_UNLOCK to FeatureHelpContent(
        title = "Desbloqueio por Digital / Rosto",
        whatItDoes = "Adiciona o botão de impressão digital ou rosto ao lado do teclado PIN.",
        exampleUse = "Destrave em 1 segundo com seu dedo ao pegar o aparelho das crianças.",
        adviceTip = "Muito prático para quem usa bloqueio com PIN."
    ),
    SettingHelpTopic.EMERGENCY_HOLD_BYPASS to FeatureHelpContent(
        title = "Saída de Emergência (5s)",
        whatItDoes = "Mostra um botão para segurar por 5 segundos na tela de PIN caso esqueça sua senha.",
        exampleUse = "Garante que você nunca fique preso mesmo se esquecer o PIN.",
        adviceTip = "Desative ou ligue o 'Modo Seguro Infantil' quando emprestar para crianças."
    ),
    SettingHelpTopic.LOCK_COUNTDOWN to FeatureHelpContent(
        title = "Atraso Antes de Bloquear",
        whatItDoes = "Aguarda 3 ou 5 segundos antes de travar os toques após clicar em Bloquear.",
        exampleUse = "Toque em Bloquear e abra seu mapa ou foto dentro desses 3 segundos.",
        adviceTip = "Deixe em 'Instantâneo (0s)' para travar na hora."
    ),
    SettingHelpTopic.HUD_TIMEOUT to FeatureHelpContent(
        title = "Ocultar Barra de Desbloqueio",
        whatItDoes = "Esconde o aviso de desbloqueio após alguns segundos para não cobrir fotos ou vídeos.",
        exampleUse = "Toque em qualquer ponto da tela sempre que quiser trazer o botão de volta.",
        adviceTip = "3 segundos é ideal para o uso comum."
    ),
    SettingHelpTopic.KIDS_SAFETY to FeatureHelpContent(
        title = "Trava Infantil Rígida",
        whatItDoes = "Oculta o atalho de emergência de 5s e desativa destravar agitando para as crianças não burlarem o PIN.",
        exampleUse = "Coloque desenhos e trave com PIN: a criança assiste sem sair do app nem apagar nada.",
        adviceTip = "Altamente recomendado para pais com desbloqueio por PIN."
    ),
    SettingHelpTopic.WRONG_ATTEMPT_COOLDOWN to FeatureHelpContent(
        title = "Espera por PIN Incorreto",
        whatItDoes = "Pausa o teclado PIN temporariamente após vários erros (15s após 5 erros, 30s após 8+).",
        exampleUse = "Impede que crianças ou curiosos fiquem chutando números sem parar.",
        adviceTip = "Mantenha ligado sempre que usar PIN."
    ),
    SettingHelpTopic.KIDS_TIMER_EXPIRY to FeatureHelpContent(
        title = "Pedir PIN ao Fim do Tempo",
        whatItDoes = "Quando o temporizador termina, trava a tela exigindo o PIN dos pais em vez de liberar sozinho.",
        exampleUse = "Defina 15 minutos de vídeo: ao acabar o tempo, a tela trava até você digitar o PIN.",
        adviceTip = "Excelente para controlar o tempo de tela infantil."
    ),
    SettingHelpTopic.OVERLAY_STYLE to FeatureHelpContent(
        title = "Aparência da Tela Bloqueada",
        whatItDoes = "Define se a tela fica 100% transparente, levemente escurecida ou preta AMOLED para economizar bateria.",
        exampleUse = "Use Vidro Claro (0%) para fotos e mapas, ou Preto (88%) para ouvir áudio no bolso.",
        adviceTip = "O modo escuro economiza bastante bateria em telas OLED."
    ),
    SettingHelpTopic.LOCK_BRIGHTNESS to FeatureHelpContent(
        title = "Brilho Apenas Durante o Bloqueio",
        whatItDoes = "Ajusta o brilho só enquanto estiver travado e volta ao normal quando desbloquear.",
        exampleUse = "Use 100% no sol para bike/moto ou 5% à noite para vídeos.",
        adviceTip = "'Sistema' mantém o brilho atual do telefone."
    ),
    SettingHelpTopic.EDGE_BORDER to FeatureHelpContent(
        title = "Borda Luminosa nas Laterais",
        whatItDoes = "Exibe uma linha ou cantos coloridos ao redor da tela para indicar de imediato que está bloqueada.",
        exampleUse = "Na tela transparente 0%, os cantos lembram na hora que o toque está travado.",
        adviceTip = "Os cantos ficam discretos em vídeos."
    ),
    SettingHelpTopic.OLED_CLOCK to FeatureHelpContent(
        title = "Relógio e Bateria na Tela Escura",
        whatItDoes = "Mostra hora e bateria sutis no centro enquanto a tela estiver escurecida.",
        exampleUse = "Confira a hora sem precisar destravar o celular.",
        adviceTip = "Ótimo com modo Preto 88% ou Véu de Privacidade."
    ),
    SettingHelpTopic.PARTIAL_TOUCH_ZONE to FeatureHelpContent(
        title = "Deixar Só Uma Área Livre",
        whatItDoes = "Mantém apenas uma área escolhida clicável (como o vídeo ou mapa) e bloqueia o resto.",
        exampleUse = "Permita pausar o YouTube enquanto bloqueia anúncios, botão voltar e bordas.",
        adviceTip = "Ideal para usar mapas ou assistir com uma mão só."
    ),
    SettingHelpTopic.PRIVACY_VEIL to FeatureHelpContent(
        title = "Esconder Tela em Público (99%)",
        whatItDoes = "Escurece quase 100% a tela contra olhares curiosos no transporte, com botão 'Espiar 3s'.",
        exampleUse = "Proteja conversas ou ouça YouTube no ônibus; toque em 'Espiar 3s' quando precisar ver.",
        adviceTip = "Perfeito para deslocamentos diários."
    ),
    SettingHelpTopic.STEALTH_BLACKOUT to FeatureHelpContent(
        title = "Tela Totalmente Oculta (Sem Luz)",
        whatItDoes = "Esconde bordas e relógio para a tela parecer desligada—toque nela para revelar o desbloqueio.",
        exampleUse = "Evita qualquer brilho incômodo no quarto escuro ou no bolso.",
        adviceTip = "Combine com Privacidade 99% para discrição máxima."
    ),
    SettingHelpTopic.FLOATING_BUBBLE to FeatureHelpContent(
        title = "Botão Flutuante de Trava",
        whatItDoes = "Coloca um botão móvel sobre qualquer app para bloquear a tela com 1 toque.",
        exampleUse = "Trave direto sobre Fotos, Maps ou YouTube sem voltar ao aplicativo.",
        adviceTip = "O jeito mais fácil para uso diário."
    ),
    SettingHelpTopic.BUBBLE_AUTO_TUCK to FeatureHelpContent(
        title = "Recolher Botão à Borda Após 3s",
        whatItDoes = "Recolhe o botão pela metade na lateral quando ocioso para não cobrir fotos ou legendas.",
        exampleUse = "Mantém a área útil da tela limpa enquanto lê ou assiste.",
        adviceTip = "Recomendado manter ativo."
    ),
    SettingHelpTopic.BUBBLE_DOUBLE_TAP_HIDE to FeatureHelpContent(
        title = "Toque Duplo para Ocultar Botão",
        whatItDoes = "Toque duas vezes no botão flutuante para ocultá-lo temporariamente; restaure pela notificação.",
        exampleUse = "Se ele ficar em cima de um botão do app, esconda em meio segundo.",
        adviceTip = "Atalho muito útil."
    ),
    SettingHelpTopic.POCKET_AUTO_LOCK to FeatureHelpContent(
        title = "Bloquear Sozinho no Bolso",
        whatItDoes = "Usa o sensor superior para detectar quando o celular entra no bolso e travar os toques sozinho.",
        exampleUse = "Guarde ingresso ou foto aberta no bolso sem toques acidentais.",
        adviceTip = "Ative para proteção automática sem apertar botões."
    ),
    SettingHelpTopic.POCKET_AUTO_UNLOCK to FeatureHelpContent(
        title = "Destravar ao Tirar do Bolso",
        whatItDoes = "Desbloqueia automaticamente ao tirar o celular da escuridão do bolso.",
        exampleUse = "Veja seu mapa instantaneamente assim que puxar o aparelho.",
        adviceTip = "Deixe desligado se empresta o aparelho a crianças."
    ),
    SettingHelpTopic.POCKET_SENSITIVITY to FeatureHelpContent(
        title = "Atraso de Detecção no Bolso",
        whatItDoes = "Define quanto tempo o sensor deve ficar coberto antes de travar automaticamente.",
        exampleUse = "Aumente para 1.2s+ se ativar sem querer ao limpar o topo da tela.",
        adviceTip = "0.7s funciona bem para quase todos."
    ),
    SettingHelpTopic.ORIENTATION_FILTER to FeatureHelpContent(
        title = "Ignorar Quando Reto na Mesa",
        whatItDoes = "Evita bloqueios automáticos quando o aparelho está apoiado reto para cima na mesa.",
        exampleUse = "Passe a mão por cima na escrivaninha sem travar sem querer.",
        adviceTip = "Muito recomendado deixar ativo."
    ),
    SettingHelpTopic.PAUSE_LANDSCAPE to FeatureHelpContent(
        title = "Pausar em Vídeos ou Jogos Horizontais",
        whatItDoes = "Pausa o bloqueio de bolso enquanto você segura o celular deitado na horizontal.",
        exampleUse = "Seus dedos jogando não travarão a tela ao encostar no sensor superior.",
        adviceTip = "Essencial para jogos e filmes."
    ),
    SettingHelpTopic.PAUSE_CHARGING to FeatureHelpContent(
        title = "Pausar Enquanto Carrega",
        whatItDoes = "Desativa o bloqueio automático de bolso enquanto o cabo carregador está conectado.",
        exampleUse = "Confortável no suporte do carro ou carregando na mesa.",
        adviceTip = "Desligue se carrega com bateria portátil na mochila."
    ),
    SettingHelpTopic.FLIP_FACE_DOWN to FeatureHelpContent(
        title = "Virar Tela para Baixo para Travar",
        whatItDoes = "Trava os toques na hora ao apoiar o celular virado para baixo numa superfície.",
        exampleUse = "Vire o aparelho na mesa durante reuniões para proteger o app aberto.",
        adviceTip = "Gesto rápido e intuitivo."
    ),
    SettingHelpTopic.CALL_AUTO_UNLOCK to FeatureHelpContent(
        title = "Desbloquear em Ligações",
        whatItDoes = "Libera a tela quando o telefone toca para atender fácil (e pode retravar ao desligar).",
        exampleUse = "Nunca perca uma ligação urgente por causa do bloqueio de toque.",
        adviceTip = "Muito importante para uso diário."
    ),
    SettingHelpTopic.SESSION_TIMER to FeatureHelpContent(
        title = "Temporizador de Desbloqueio",
        whatItDoes = "Encerra o bloqueio após 15, 30 ou 60 minutos (ou exige PIN se a regra infantil estiver ativa).",
        exampleUse = "Durma ouvindo áudio e deixe a trava sair sozinha em 30 minutos.",
        adviceTip = "Deixe desligado fora de sessões cronometradas."
    ),
    SettingHelpTopic.APP_WATCH to FeatureHelpContent(
        title = "Proteção Automática por App",
        whatItDoes = "Monitora localmente quando você abre apps escolhidos (YouTube, Maps, Câmera) para exibir a bolha ou travar.",
        exampleUse = "Abra o Google Maps e o botão flutuante aparece sozinho—e some ao sair.",
        adviceTip = "Usa apenas Acesso de Uso local, sem Acessibilidade nem Internet."
    ),
    SettingHelpTopic.SHAKE_SHORTCUT to FeatureHelpContent(
        title = "Agitar 2x para Bloquear / Abrir",
        whatItDoes = "Trava ou destrava o toque ao agitar o celular duas vezes rapidamente.",
        exampleUse = "Com luvas na rua, agite duas vezes sem precisar tocar na tela.",
        adviceTip = "Agitar para abrir desliga sozinho no Modo Seguro Infantil."
    ),
    SettingHelpTopic.VOLUME_SHORTCUT to FeatureHelpContent(
        title = "Toque Duplo no Botão de Volume",
        whatItDoes = "Apertar duas vezes rápido o volume alterna ou destrava o bloqueio.",
        exampleUse = "Use botões físicos se estiver com as mãos molhadas.",
        adviceTip = "Ótima alternativa física de emergência."
    ),
    SettingHelpTopic.LOW_BATTERY_BLACKOUT to FeatureHelpContent(
        title = "Escuro Automático com Bateria Baixa",
        whatItDoes = "Quando a bateria cai abaixo de 10%, 15% ou 20%, a trava escurece 92% para poupar energia.",
        exampleUse = "Estende sua bateria automaticamente em viagens longas.",
        adviceTip = "15% é uma ótima margem de segurança."
    ),
    SettingHelpTopic.CHARGER_AUTO_RULE to FeatureHelpContent(
        title = "Regras ao Conectar / Desconectar Cabo",
        whatItDoes = "Trava ao ligar o carregador ou destrava automaticamente ao remover o cabo.",
        exampleUse = "Plugue no carro para travar o mapa e destrave ao desembarcar.",
        adviceTip = "Útil em suportes veiculares."
    ),
    SettingHelpTopic.KEEP_SCREEN_ON to FeatureHelpContent(
        title = "Manter Tela Acesa Enquanto Bloqueada",
        whatItDoes = "Impede a tela de apagar sozinha para fotos, ingressos QR ou mapas seguirem visíveis.",
        exampleUse = "Mostre o QR code sem precisar ficar tocando a tela a cada 30 segundos.",
        adviceTip = "Desligue só se ouvir áudio no bolso para máxima economia."
    ),
    SettingHelpTopic.HAPTICS_AND_BOOT to FeatureHelpContent(
        title = "Vibração e Início Após Reiniciar",
        whatItDoes = "Dá um leve toque vibratório ao travar e deixa a proteção pronta após reiniciar o aparelho.",
        exampleUse = "Sinta no bolso que a trava realmente foi ativada sem olhar a tela.",
        adviceTip = "Mantenha a vibração ativada."
    ),
    SettingHelpTopic.BACKUP_RESTORE to FeatureHelpContent(
        title = "Salvar ou Restaurar Cópia de Ajustes",
        whatItDoes = "Guarda todos os seus ajustes e perfis num arquivo offline (.json) para restaurar quando quiser.",
        exampleUse = "Ao trocar de aparelho, recupere tudo em 2 segundos.",
        adviceTip = "100% offline—seus arquivos não saem do celular."
    )
)
