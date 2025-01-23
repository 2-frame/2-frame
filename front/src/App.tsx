import {Toaster} from "sonner";
import Router from "./router/Router";
import {SignIn} from "./components/SignIn/SignIn.tsx";

function App() {
  return (
    <SignIn>
      <Router/>
      <Toaster richColors closeButton position={"top-center"}/>
    </SignIn>
  )
}

export default App
