import {useRecoilState} from "recoil";
import {signInUser} from "../store/auth.ts";
import {useNavigate} from "react-router";
import {useMutation} from "@tanstack/react-query";
import {postSignOut} from "../api/index.ts";

export const LogoutPage = () => {
  const [isLoggedIn, setLoggedIn] = useRecoilState(signInUser);
  const navigator = useNavigate();

  const mutation = useMutation({
    mutationFn: postSignOut,
    mutationKey: ['logout']
  })

}