import {Button, Card, CardBody, CardHeader, Divider} from "@heroui/react";
import {CenterWrapper} from "../components/@common/CenterWrapper";
import Input from "../components/@common/Input";
import Typo from "../components/@common/Typo";
import {Link} from "react-router";
import {useSignup} from "./auth/useAuth.ts";

const SignupPage = () => {

  const {setValueName, handleSignup} = useSignup();
  return <CenterWrapper>
    <Card className="min-w-[320px] w-full" shadow="none">
      <CardHeader className="items-center flex justify-center">
        <Typo.H3 text="회원가입" className="font-light text-center"/>
      </CardHeader>
      <CardBody className="gap-3">
        <Input.Basic radius="none" label="email" onValueChange={setValueName("email")}/>
        <Input.Basic radius="none" label="패스워드" type="password" onValueChange={setValueName("password")}/>
        <Input.Basic radius="none" label="패스워드" type="password" onValueChange={setValueName("rePassword")}/>
        <Input.Basic radius="none" label="이름" onValueChange={setValueName("name")}/>
        <Button radius="none" className="h-[60px]" onPress={handleSignup}>회원가입</Button>
        <Divider></Divider>
        <Link to={"/login"}><Typo.H6 text="계정이 있으신가요?" className="text-sm font-light"/></Link>
      </CardBody>
    </Card>
  </CenterWrapper>
}

export default SignupPage;