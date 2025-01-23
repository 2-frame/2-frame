import {Navbar, NavbarBrand, NavbarContent, NavbarMenu, NavbarMenuItem, NavbarMenuToggle, Spacer} from "@heroui/react";
import {Link, useLocation, useNavigate} from "react-router";
import Typo from "../@common/Typo";
import {BiShoppingBag} from "react-icons/bi";
import {useCallback, useEffect, useState} from "react";
import {useRecoilState} from "recoil";
import {signInUser} from "../../store/auth.ts";
import {useMutation, useQuery} from "@tanstack/react-query";
import {getCategories, postSignOut} from "../../api";
import {toast} from "sonner";

const ApplicationNavbar = () => {
  const [isLoggedIn, setLoggedIn] = useRecoilState(signInUser)
  const [isMenuOpen, setMenuOpen] = useState<boolean>(false);
  const location = useLocation();
  const navigator = useNavigate();
  const logoutMutation = useMutation({
    mutationKey: ['sigout'],
    mutationFn: postSignOut
  });
  const {data} = useQuery({
    queryKey: ['get all category'],
    queryFn: () => getCategories()
  })
  useEffect(() => {
    setMenuOpen(false);
  }, [location])

  const handleSignOut = useCallback(() => {
    setLoggedIn(false);
    logoutMutation.mutate();
    toast.info("잘가용~");
    navigator("/")
  }, [setLoggedIn])


  return <Navbar isMenuOpen={isMenuOpen} onMenuOpenChange={setMenuOpen} maxWidth="full" className="flex flex-between ">
    <NavbarBrand>
      <Link to={"/"}>
        <Typo.H6 text="2-FRAME" className="font-bold"/>
      </Link>
    </NavbarBrand>
    <NavbarContent justify="end" className="w-full">
      <Link to={"/login"}>
        <BiShoppingBag size={32}/>
      </Link>
      <NavbarMenuToggle aria-label={isMenuOpen ? "Close menu" : "Open menu"}/>
    </NavbarContent>
    <NavbarMenu className="bg-gray-0 gap-3">
      <NavbarMenuItem>
        <Typo.H3 text="랭킹" className="font-semibold"/>
        <Link to={"/products"}>
          <Typo.H3 text="쇼핑" className="font-semibold"/>
        </Link>
      </NavbarMenuItem>

      <Spacer y={20}/>
      <NavbarMenuItem onClick={() => setMenuOpen(false)}>
        {!isLoggedIn ? (
          <>
            <Link to={"/login"}><Typo.H5 text="로그인" className="font-light"/></Link>
            <Link to={"/signup"}><Typo.H5 text="회원가입" className="font-light"/></Link>
          </>
        ) : (
          <div onClick={handleSignOut}>
            <Typo.H5 text="로그아웃" className="font-light"/>
          </div>
        )}
      </NavbarMenuItem>
    </NavbarMenu>
  </Navbar>
}
export default ApplicationNavbar;