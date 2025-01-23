import {Button, Card, CardBody, CardHeader, Divider} from "@heroui/react";
import Input from "../components/@common/Input";
import {Link} from "react-router";
import {CenterWrapper} from "../components/@common/CenterWrapper";
import Typo from "../components/@common/Typo";
import {useSignIn} from "./auth/useAuth.ts";

const LoginPage = () => {

  const {handleSignIn, setValueName} = useSignIn();

  return (
    <CenterWrapper>
      <Card className="min-w-[320px] w-full" shadow="none">
        <CardHeader className="items-center flex justify-center">
          <Typo.H3 text="로그인" className="font-light text-center"/>
        </CardHeader>
        <CardBody className="gap-3">
          <Input.Basic radius="none" label="email" onValueChange={setValueName("email")}/>
          <Input.Basic radius="none" label="패스워드" type="password" onValueChange={setValueName("password")}/>

          <Button radius="none" className="h-[60px]" onPress={handleSignIn}>로그인</Button>
          <Divider></Divider>
          <CenterWrapper className="w-full gap-4">
            <Typo.H6 text="아이디 찾기" className="text-xs"/>
            <Divider orientation="vertical"/>
            <Typo.H6 text="비밀번호 찾기" className="text-xs"/>
            <Divider orientation="vertical"/>
            <Link to={"/signup"}><Typo.H6 text="회원가입" className="text-xs"/></Link>
          </CenterWrapper>
        </CardBody>
      </Card>
    </CenterWrapper>
  )
}

export default LoginPage;