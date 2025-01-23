import {
  PagedModel,
  PageRequest,
  ProductBoardRequest,
  ProductBoardResponse,
  ProductDetailResponse,
  ProductReviewDetail,
  ProductReviewListResponse,
  ProductReviewsResponse,
  SearchProductsResponse,
  SignInRequest,
  SignupRequest
} from "./type";
import {axiosInstance as $} from "./AxiosInstance.ts";
import {AxiosResponse} from "axios";

const boxing = async <T>(func: () => Promise<AxiosResponse<T>>) => {
  const {data} = await func();
  return data as T;
}

export const pMock = <T> () => {
  return {page: {size: 1, totalPages: 10, totalElements: 10, number: 1}, content: []} as PagedModel<T>
}

export const postSignUp = async (request: SignupRequest) => await $.post("/members/sign-up", request);
export const postSignIn = async (request: SignInRequest) => await $.post("/members/sign-in", request);
export const postSignOut = async () => await $.post("/members/sign-out");


export const getCategories = async () => await $.get("/products/categories");

export const getProducts = ({page, size}: PageRequest) => boxing(async () => await $.get<SearchProductsResponse>(`/products?page=${page}&size=${size}`));
export const getProductDetails = ({id}: { id: number }) => boxing(async () => await $.get<ProductDetailResponse>(`/products/${id}`));
export const getProductsReview = ({id, page, size}: { id: number } & PageRequest) => boxing(async () => await $.get<ProductReviewsResponse>(`/products/${id}/reviews?page=${page}&size=${size}`))

export const getAllReviews = () =>
  boxing(async () => await $.get<ProductReviewListResponse>("/products/reviews"))

export const getSalesProductReview = ({productId, reviewId}: { productId: number, reviewId: number }) =>
  boxing(async () => await $.get<ProductReviewDetail>(`/products/${productId}/reviews/${reviewId}`))

export const getProductsBoard = ({productId}: { productId: number }) =>
  boxing(() => $.get<ProductBoardResponse>(`products/${productId}/boards`));

export const postProductsBoard = () => async (request: ProductBoardRequest & { productId: number }) => await $.post(`/products/${request.productId}/boards`, request)

export const getProfile = async () => boxing(() => $.get<{ name: string, email: string }>("/mypage"))

export const postQuestion = async ({question}: {question: string}) => {
  const {data} = await $.post<{answer: string}>("/chatbot/asks", {question})
  return data;
  // return new Promise((resolve) => {
  //   setTimeout(() => {
  //     resolve({answer: "답변띠"})
  //   }, 5000);
  // });
  // return {answer: "답변띠"}
}