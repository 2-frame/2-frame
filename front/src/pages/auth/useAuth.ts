import {useForm} from "../../hooks/useForm.ts";
import {SignInForm, SignupForm} from "../../types/member.ts";
import {useSignInMutation, useSignupMutation} from "../../hooks";
import {useCallback} from "react";
import {toast} from "sonner";
import {useRecoilState} from "recoil";
import {signInUser} from "../../store/auth.ts";
import {useNavigate} from "react-router";

export const useSignup = () => {
  const {form, setValueName} = useForm<SignupForm>({
    name: "",
    email: "",
    rePassword: "",
    password: ""
  })
  const mutation = useSignupMutation();
  const navigator = useNavigate();

  const handleSignup = useCallback(() => {
    if (form.password !== form.rePassword) {
      toast.error("입력하신 비밀번호가 일치하지 않습니다.")
      return;
    }
    mutation.mutate(form, {
      onSuccess: () => {
        toast.success("회원가입 성공");
        navigator("/login");
      },
      onError: (error) => {
        console.log(error);
        toast.error(error.message)
      }
    })
  }, [form])


  return {
    setValueName,
    handleSignup
  }
}

export const useSignIn = () => {
  const {form, setValueName} = useForm<SignInForm>({
    email: "", password: ""
  })
  const [_, setLoggedIn] = useRecoilState(signInUser);
  const navigator = useNavigate();
  const mutation = useSignInMutation();

  const handleSignIn = useCallback(() => {
    mutation.mutate(form, {
      onSuccess: () => {
        toast.success("환영합니다.")
        setLoggedIn(true)
        navigator("/")
      },
      onError: () => toast.error("아이디 혹은 비밀번호가 잘못됐습니다")
    })
  }, [form])

  return {
    setValueName,
    handleSignIn
  }


}