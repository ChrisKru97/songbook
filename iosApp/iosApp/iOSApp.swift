import SwiftUI
import FirebaseCore
import shared

class AppDelegate: NSObject, UIApplicationDelegate {
  func application(_ application: UIApplication,
                   didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
    FirebaseApp.configure()
    return true
  }
}

@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate
    
    init() {
        KoinKt.doInitKoin(driverFactory: DriverFactory(), settingsFactory: SettingsFactory())
    }
    
	var body: some Scene {
		WindowGroup {
            ContentView(viewModel: ContentView.ViewModel())
		}
	}
}
