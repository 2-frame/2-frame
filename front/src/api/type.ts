import {ImageURLs} from "../types/core.ts";
import {ProductOption} from "../types/option.ts";

export type PageRequest = {
  page: number;
  size: number;
}

export type PagedModel<T> = {
  content: T[],
  page: {
    size: number
    number: number
    totalElements: number;
    totalPages: number;
  }
}

export type SignupRequest = {
  email: string;
  password: string;
  name: string
}

export type SignInRequest = {
  email: string;
  password: string;
}

export type SearchProduct = {
  id: number;
  name: string;
  price: number;
  mainImageURLs: ImageURLs;
}
export type SearchProductsResponse = PagedModel<SearchProduct>

export type ProductDetailResponse = {
  productId: number;
  name: string;
  description: string;
  price: number;
  soldOut: boolean;
  options: ProductOption[]
  previewImgURLs: ImageURLs,
  mainImgURLs: ImageURLs,
  detailURLs: ImageURLs
}

export type ProductReview = {
  reviewId: number
  rate: number;
  title: string;
  name: string;
  productName: string;
  createdAt: string;
};
export type ProductReviewsResponse = PagedModel<ProductReview>;

export type ProductReviewDetail = {
  reviewId: number;
  rate: number;
  title: string;
  content: string;
  name: string;
  productName: string;
  productMainImage: string;
  imageUrls: ImageURLs;
  createdAt: string;
}
export type ProductReviewListResponse = PagedModel<ProductReview>;

export type ProductBoard = {
  inquiryId: number;
  title: string;
  question: string;
  author: string;
  createdAt: string;
  replyContent: string;
  replyCreatedAt: string;
}

export type ProductBoardResponse = PagedModel<ProductBoard>;

export type ProductBoardRequest = {
  title: string;
  question: string;
}