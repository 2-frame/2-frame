import {Link} from "react-router";
import Typo from "../@common/Typo";
import {CiHome, CiMenuBurger, CiUser} from "react-icons/ci";
import {PropsWithChildren} from "react";


interface NavItemProps {
  to: string;
  text: string;
}

const NavItem = ({to, text, children}: NavItemProps & PropsWithChildren) => {
  return (
    <Link to={to} className="flex flex-col align-middle justify-center gap-1 items-center">
      {children}
      <Typo.H6 text={text} className="text-xs"/>
    </Link>
  )
}

const BottomNavigator = () => {
  return <div className="fixed bottom-0 left-0 w-full bg-gray-100 flex justify-around align-middle p-[10px] z-[100]">
    <NavItem to={""} text={"카테고리"}>
      <CiMenuBurger size={20}/>
    </NavItem>
    <NavItem to={""} text={"홈"}>
      <CiHome size={20}/>
    </NavItem>
    <NavItem to={""} text={"My"}>
    <CiUser size={20}/>
  </NavItem>
  </div>
}

export default BottomNavigator;