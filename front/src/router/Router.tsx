import {createBrowserRouter, RouterProvider} from "react-router";
import Layout from "../layouts/Layout.tsx";
import Home from "../pages/Home.tsx";
import LoginPage from "../pages/LoginPage.tsx";
import SignupPage from "../pages/SignupPage.tsx";
import ProductDetailsPage from "../pages/ProductDetailsPage.tsx";
import ReviewListPage from "../pages/ReviewListPage.tsx";
import ProductsPage from "../pages/ProductsPage.tsx";
import {LogoutPage} from "../pages/LogoutPage.tsx";


const router = createBrowserRouter([
  {
    path: "/",
    element: <Layout/>,
    children: [
      {
        index: true,
        element: <Home/>
      },
      {
        path: "/login",
        element: <LoginPage/>
      },
      {
        path: "/products",
        element: <ProductsPage/>
      },
      {
        path: "/logout",
        element: <LogoutPage/>
      },
      {
        path: "/signup",
        element: <SignupPage/>
      },
      {
        path: "/products/:id",
        element: <ProductDetailsPage/>
      },
      {
        path: '/reviews',
        element: <ReviewListPage/>
      }
    ]
  }
])


const Router = () => {
  return <RouterProvider router={router}/>
}

export default Router;