import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel

	var body: some View {
        
        Text(viewModel.id)
	}
}

extension ContentView {
    class ViewModel: ObservableObject {
        @Published var id : String = "loading"
        init() {
            Greeting().getSongs { id, error  in
                if(id != nil) {
                    DispatchQueue.main.async {
                        self.id = id!
                    }
                }
            }
        }
    }
}

//struct ContentView_Previews: PreviewProvider {
//	static var previews: some View {
//		ContentView()
//	}
//}
