type AuthenticationData = {
  password: string;
  email: string;
}

export type SignupForm = AuthenticationData & {
  name: string;
  rePassword: string;
}

export type SignInForm = AuthenticationData;