import {Outlet} from "react-router";
import {CenterWrapper} from "../components/@common/CenterWrapper";
import ApplicationNavbar from "../components/ApplicationNavbar/ApplicationNavbar.tsx";

const Layout = () => {


  return (
    <CenterWrapper className="min-w-[340px] w-full max-w-[912px] flex flex-col xl:m-auto">
      <ApplicationNavbar/>
      <div className={"lg:p-0 xl:p-16 overflow-x-hidden+"}>
        <Outlet/>
      </div>
    </CenterWrapper>
  )
}
export default Layout;