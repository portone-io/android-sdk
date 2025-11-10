# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

**android-sdk** is PortOne's official Android SDK for V2 payment integration. It provides a native Kotlin/Java API for integrating PortOne payments, billing keys, and identity verification into Android applications.

### Key Characteristics

- **Thin Wrapper Architecture**: Uses WebView to load PortOne payment UI (follows [SDK Thin Wrapper Principle](../../CLAUDE.md#2-sdk-architecture-philosophy-thin-wrappers))
- **Native Android Integration**: Activity-based API with proper lifecycle handling
- **Multi-language Support**: Kotlin (primary) and Java compatible
- **Published via JitPack**: Not Maven Central (yet)

## Repository Structure

```
android-sdk/
├── sdk/                           # Main SDK module (published library)
│   ├── src/main/java/io/portone/sdk/android/
│   │   ├── PortOne.kt            # Main SDK API (object singleton)
│   │   ├── PortOneWebView.kt     # Custom WebView implementation
│   │   ├── payment/              # Payment integration
│   │   │   ├── PaymentActivity.kt
│   │   │   ├── PaymentRequest.kt
│   │   │   ├── PaymentResponse.kt
│   │   │   └── PaymentCallback.kt
│   │   ├── issuebillingkey/      # Billing key issuance
│   │   │   ├── IssueBillingKeyActivity.kt
│   │   │   ├── IssueBillingKeyRequest.kt
│   │   │   ├── IssueBillingKeyResponse.kt
│   │   │   └── IssueBillingKeyCallback.kt
│   │   ├── identityverification/ # Identity verification (본인인증)
│   │   │   ├── IdentityVerificationActivity.kt
│   │   │   ├── IdentityVerificationRequest.kt
│   │   │   ├── IdentityVerificationResponse.kt
│   │   │   └── IdentityVerificationCallback.kt
│   │   ├── issuebillingkeyandpay/ # Combined billing key + payment
│   │   ├── paymentui/            # Custom payment UI loading
│   │   └── issuebillingkeyui/    # Custom billing key UI loading
│   ├── build.gradle.kts          # SDK module build configuration
│   └── proguard-rules.pro        # ProGuard/R8 rules for minification
├── app/                           # Sample application (test/demo app)
│   ├── src/main/java/io/portone/portonesdk/
│   │   ├── MainActivity.kt       # Sample usage examples
│   │   ├── PaymentTestActivity.kt
│   │   ├── IssueBillingKeyTestActivity.kt
│   │   └── IdentityVerificationTestActivity.kt
│   └── build.gradle.kts          # App module build configuration
├── .devcontainer/                 # Dev container configuration
├── build.gradle.kts               # Root project build configuration
├── settings.gradle.kts            # Gradle project settings
├── gradle.properties              # Gradle properties (includes versionName)
├── gradlew                        # Gradle wrapper script (Unix)
├── gradlew.bat                    # Gradle wrapper script (Windows)
├── README.md                      # User-facing documentation (Korean)
└── LICENSE                        # Apache 2.0 / MIT dual license
```

## Technology Stack

### Build System
- **Gradle**: Kotlin DSL (`build.gradle.kts`)
- **Android Gradle Plugin**: 8.x
- **Kotlin**: 1.9+
- **Java**: Target/Source 17 (with desugaring for Java 8 APIs)

### Android Configuration
- **Min SDK**: 21 (Android 5.0 Lollipop)
- **Compile SDK**: 36 (Android 14+)
- **Target SDK**: (follows Android best practices)

### Key Dependencies
```kotlin
// Core Android
implementation("androidx.core:core-ktx")
implementation("androidx.appcompat:appcompat")
implementation("androidx.activity:activity")
implementation("androidx.constraintlayout:constraintlayout")
implementation("androidx.webkit:webkit")  // WebView compatibility

// Serialization
implementation("org.jetbrains.kotlinx:kotlinx-serialization-json")
implementation("org.jetbrains.kotlinx:kotlinx-datetime")

// Material Design
implementation("com.google.android.material:material")

// Desugaring (Java 8+ APIs on older Android)
coreLibraryDesugaring("com.android.tools:desugar_jdk_libs")
```

### Publishing
- **Distribution**: JitPack (`com.github.portone-io:android-sdk:x.y.z`)
- **Maven Publishing Plugin**: Configured in `sdk/build.gradle.kts`
- **Versioning**: Semantic Versioning 2.0.0 (currently `0.x.x-alpha.x`)

## SDK Architecture

### Design Philosophy: Thin WebView Wrapper

The Android SDK follows the **thin wrapper principle** because:

1. **PG providers require web-based UI**: Card input, 3DS authentication, carrier billing all happen in web views
2. **Browser SDK contains the logic**: Payment orchestration is in the browser SDK
3. **Native layer handles integration**: Activity lifecycle, deep links, WebView management

```
Android App (Activity/Fragment)
    ↓
PortOne.requestPayment() → PaymentActivity
    ↓
PortOneWebView (loads PortOne checkout page)
    ↓
Browser SDK (JavaScript payment logic)
    ↓
PG Provider Web UI (card input, 3DS, etc.)
    ↓
Deep Link / Redirect back to app
    ↓
PaymentCallback.onSuccess() or onFail()
```

### API Pattern: register → request → callback

All SDK operations follow this pattern:

```kotlin
class MyActivity : ComponentActivity() {
    // 1. Register for activity result (in onCreate or class-level)
    private val paymentLauncher = PortOne.registerForPaymentActivity(
        this,
        callback = object : PaymentCallback {
            override fun onSuccess(response: PaymentResponse.Success) {
                // Handle success
            }
            override fun onFail(response: PaymentResponse.Fail) {
                // Handle failure
            }
        }
    )
    
    // 2. Request payment (when user clicks pay button)
    fun onPayButtonClick() {
        PortOne.requestPayment(
            this,
            request = PaymentRequest(
                storeId = "store-xxx",
                channelKey = "channel-xxx",
                paymentId = "order-123",
                orderName = "Product Name",
                amount = Amount(total = 10000, currency = Currency.KRW),
                method = PaymentMethod.Card()
            ),
            resultLauncher = paymentLauncher
        )
    }
}
```

**Why this pattern?**
- Uses Android's modern `ActivityResultContract` API (not deprecated `startActivityForResult`)
- Type-safe result handling with callbacks
- Proper lifecycle management (Activity/Fragment aware)

### Core SDK Operations

The SDK provides these main operations:

1. **requestPayment** - One-time payment
2. **requestIssueBillingKey** - Issue billing key for subscriptions
3. **requestIdentityVerification** - Korean identity verification (본인인증)
4. **requestIssueBillingKeyAndPay** - Issue billing key + pay in one step
5. **loadPaymentUI** - Load custom payment UI
6. **loadIssueBillingKeyUI** - Load custom billing key UI

Each operation has:
- A `Request` data class (Parcelable)
- A `Response` sealed class with `Success` and `Fail` variants
- A `Callback` interface with `onSuccess` and `onFail` methods
- An `Activity` that manages the WebView lifecycle

## Development Guidelines

### Code Style

**Kotlin**:
- Follow [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use `kotlin.code.style=official` (configured in `gradle.properties`)
- Prefer data classes for models
- Use sealed classes for responses/states
- Leverage Kotlin Parcelize plugin for Parcelable

**File Naming**:
- PascalCase for classes: `PaymentActivity.kt`, `PaymentRequest.kt`
- Group related files in packages: `payment/`, `issuebillingkey/`

**Documentation**:
- Use KDoc comments for public APIs
- Include `@param` and `@return` tags
- Provide usage examples in class-level KDoc

### Android Best Practices

**Activities**:
- All SDK activities extend `ComponentActivity` (not `Activity`)
- Use `EdgeToEdge.enable()` for modern UI
- Handle configuration changes properly
- Support both portrait and landscape

**WebView Security**:
- JavaScript is enabled (required for payment flow)
- Use HTTPS only for production
- Implement `WebViewClient` for URL handling
- Handle deep links via custom URL scheme (`portone://payment`)

**Permissions**:
- Internet permission is implicit (WebView needs it)
- No location/camera/storage permissions required

### AndroidManifest.xml Requirements

Apps using this SDK must declare `<queries>` for payment provider apps:

```xml
<queries>
    <!-- Easy Pay (간편결제) -->
    <package android:name="com.kftc.bankpay.android" /> <!-- Bank Pay -->
    <package android:name="viva.republica.toss" /> <!-- Toss Pay -->
    <package android:name="com.kakao.talk" /> <!-- Kakao Pay -->
    <package android:name="com.nhn.android.search" /> <!-- Naver Pay -->
    <!-- ... many more (see README.md for complete list) -->
</queries>
```

**Why?** Android 11+ requires explicit package visibility declarations to launch other apps.

### Gradle Configuration

**Publishing Version**:
- Version is defined in `gradle.properties`: `versionName=0.1.0-alpha.1`
- Follow Semantic Versioning: `MAJOR.MINOR.PATCH-prerelease`
- Currently in `0.x` (unstable API, but production-ready)

**Java Desugaring**:
Required for Java 8+ APIs on Android API < 26:
```kotlin
android {
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
}
dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.x.x")
}
```

## Working with the SDK

### Running the Sample App

```bash
cd sdk/android-sdk

# Option 1: Open in Android Studio
# File → Open → select android-sdk directory

# Option 2: Build with Gradle
./gradlew :app:assembleDebug

# Option 3: Run on connected device/emulator
./gradlew :app:installDebug
adb shell am start -n io.portone.portonesdk/.MainActivity
```

**Sample App Credentials**: Edit `app/src/main/res/values/strings.xml` or hardcode in test activities.

### Testing SDK Changes

**Unit Tests**:
```bash
./gradlew :sdk:testDebugUnitTest
```

**Instrumented Tests** (requires emulator/device):
```bash
./gradlew :sdk:connectedAndroidTest
```

**Manual Testing**:
1. Modify SDK code in `sdk/src/main/`
2. Update sample app in `app/src/main/` to test changes
3. Run app on device: `./gradlew :app:installDebug`
4. Test payment flow end-to-end

### Publishing to JitPack

**JitPack automatically builds from Git tags**:

1. Update version in `gradle.properties`:
   ```properties
   versionName=0.2.0-alpha.1
   ```

2. Commit and create Git tag:
   ```bash
   git add gradle.properties
   git commit -m "chore: Bump version to 0.2.0-alpha.1"
   git tag v0.2.0-alpha.1
   git push origin main --tags
   ```

3. JitPack builds automatically when someone requests the version:
   ```gradle
   dependencies {
       implementation("com.github.portone-io:android-sdk:0.2.0-alpha.1")
   }
   ```

**Check build status**: https://jitpack.io/#portone-io/android-sdk

### Adding New Features

When adding a new SDK operation (e.g., `requestRefund`):

1. **Create domain models** in `sdk/src/main/java/io/portone/sdk/android/refund/`:
   ```kotlin
   @Parcelize
   data class RefundRequest(val paymentId: String, val amount: Long) : Parcelable
   
   sealed class RefundResponse : Parcelable {
       @Parcelize data class Success(val refundId: String) : RefundResponse()
       @Parcelize data class Fail(val error: String) : RefundResponse()
   }
   
   interface RefundCallback : Callback<RefundResponse.Success, RefundResponse.Fail>
   ```

2. **Create Activity** for WebView handling:
   ```kotlin
   class RefundActivity : ComponentActivity() {
       // WebView lifecycle, JavaScript interface, result handling
   }
   ```

3. **Add to PortOne object** in `PortOne.kt`:
   ```kotlin
   object PortOne : Sdk {
       fun registerForRefundActivity(
           activity: ComponentActivity,
           callback: RefundCallback
       ): ActivityResultLauncher<Intent> { /* ... */ }
       
       fun requestRefund(
           activity: ComponentActivity,
           request: RefundRequest,
           resultLauncher: ActivityResultLauncher<Intent>
       ) { /* ... */ }
   }
   ```

4. **Update sample app** with example usage in `app/src/main/`

5. **Document in README.md** with Kotlin and Java examples

## Common Issues & Troubleshooting

### Issue: "Unresolved reference: PortOne"
**Cause**: JitPack dependency not added or sync failed
**Solution**:
```kotlin
// settings.gradle.kts
dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

// app/build.gradle.kts
dependencies {
    implementation("com.github.portone-io:android-sdk:0.1.0-alpha.1")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")
}
```

### Issue: "Cannot launch payment provider app"
**Cause**: Missing `<queries>` in `AndroidManifest.xml`
**Solution**: Add all package declarations from README.md to `<manifest><queries>`

### Issue: "WebView not loading payment page"
**Cause**: Network permission, cleartext traffic, or SSL issues
**Solution**:
- Verify `<uses-permission android:name="android.permission.INTERNET" />`
- For local testing, enable cleartext traffic in `AndroidManifest.xml`:
  ```xml
  <application android:usesCleartextTraffic="true">
  ```
- For production, use HTTPS only

### Issue: "Payment callback not called"
**Cause**: Activity destroyed before result returns (e.g., back button pressed)
**Solution**: 
- This is expected behavior (user cancelled)
- Handle in `onFail` callback with cancellation error
- Consider using `onStop`/`onDestroy` to log analytics

### Issue: "Deep link not working"
**Cause**: Intent filter not configured or URL scheme mismatch
**Solution**: SDK handles deep links internally via `portone://payment`, no app-side configuration needed

### Issue: "Duplicate class errors during build"
**Cause**: Conflicting dependencies or transitive dependency issues
**Solution**:
```bash
./gradlew :app:dependencies  # Check dependency tree
```
- Exclude conflicting transitive dependencies
- Ensure single version of androidx libraries

## Testing Strategy

### Unit Tests (Fast, No Device)
- Test data classes serialization
- Test request/response parsing
- Mock WebView interactions
- Location: `sdk/src/test/`

### Instrumented Tests (Requires Device)
- Test Activity lifecycle
- Test WebView loading
- Test deep link handling
- Location: `sdk/src/androidTest/`

### Manual End-to-End Tests
Use sample app to test:
1. Payment success flow
2. Payment failure flow
3. User cancellation (back button)
4. Network error handling
5. Rotation/configuration changes
6. Billing key issuance
7. Identity verification

**Test Credentials**: Get from PortOne Console (test mode)

**Test PG Providers**: Use sandbox environments

## Security Considerations

### What NOT to Include in SDK

- ✗ **API Secrets**: Never expose V2 API Secret in client SDK
- ✗ **Webhook Verification**: Server-side only
- ✗ **Payment Amount Validation**: Trust server, not client
- ✗ **Business Logic**: Keep SDK thin, logic belongs in services

### What to Protect

- ✓ **User Input Sanitization**: Validate request parameters
- ✓ **HTTPS Enforcement**: No cleartext traffic in production
- ✓ **WebView Security**: Disable unnecessary JavaScript interfaces
- ✓ **ProGuard/R8**: Obfuscate release builds

### ProGuard Rules

The SDK includes `consumer-rules.pro` for library consumers:
```proguard
# Keep PortOne public API
-keep class io.portone.sdk.android.PortOne { *; }
-keep class io.portone.sdk.android.payment.** { *; }
# ... (automatically applied when app uses SDK)
```

## Versioning & Releases

### Current Status
- **Version**: `0.1.0-alpha.1` (as of gradle.properties)
- **Stability**: Alpha (API may change)
- **Production Ready?**: Yes, with understanding that API is not final

### Semantic Versioning

Following [SemVer 2.0.0](https://semver.org/):

- **MAJOR.0.0**: Breaking API changes (e.g., `PortOne.pay()` → `PortOne.requestPayment()`)
- **0.MINOR.0**: New features, backward-compatible (e.g., add `requestRefund()`)
- **0.0.PATCH**: Bug fixes, no API changes

**Pre-release tags**:
- `-alpha.x`: Early testing, API unstable
- `-beta.x`: API stable, testing for bugs
- `-rc.x`: Release candidate, production-ready

**When will it be 1.0.0?** When public API is considered stable and frozen.

## Related Resources

### PortOne Documentation
- **Developer Docs**: https://developers.portone.io/docs/sdk/android-sdk
- **API Reference**: https://developers.portone.io/api
- **Console**: https://console.portone.io

### SDK Repositories
- **Browser SDK**: `portone-io/browser-sdk` (the JavaScript payment logic)
- **iOS SDK**: `portone-io/ios-sdk` (Swift equivalent)
- **React Native SDK**: `portone-io/react-native-sdk` (uses this SDK internally on Android)
- **Server SDK (JVM)**: `portone-io/server-sdk` (for backend verification)

### Sample Projects
- **portone-sample**: `guide/portone-sample` in workspace (includes Android example)

### Support
- **Technical Support**: tech.support@portone.io
- **Issues**: GitHub Issues (this repository)
- **Questions**: PortOne Developer Community

## Contributing

When contributing to android-sdk:

1. **Follow Kotlin conventions** - Use official style guide
2. **Write tests** - Unit tests for logic, instrumented tests for UI
3. **Update README.md** - Add examples for new features
4. **Update sample app** - Show how to use new APIs
5. **Version bump** - Update `gradle.properties` following SemVer
6. **Backward compatibility** - Until 1.0.0, avoid breaking changes in minor versions
7. **ProGuard rules** - Add rules for new public APIs in `consumer-rules.pro`

## License

Dual-licensed under:
- **Apache License 2.0**
- **MIT License**

See [LICENSE](LICENSE) for details.
