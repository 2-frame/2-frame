import {Id, ImageURLs, RenameIdField} from "./core";
import {ProductOption} from "./option.ts";
import {Categroy} from "./category.ts";

export type Product = Id & Category & {
  productId: number
  name: string;
  price: number;
  mainImgURLs: ImageURLs
}

export type ProductDetail = Product
  & {
  options: ProductOption[]
  category: Categroy;
  previewImagesURLs: ImageURLs
}

type ProductReviewId = RenameIdField<Id, "productId">;

export type ProductReviewForm = {
  rating: number;
  title: string;
  content: string;
  imageUrls: ImageURLs
}

export type ProductReviewPreview = ProductReviewId & {
  author: string;
  createdAt: string;
}
