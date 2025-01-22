export type Id = {
    id: number
};

export type RenameIdField<T, NewKey extends string> = {
  [K in keyof T as K extends "id" ? NewKey : K]: T[K];
};


export type ImageURL = string;
export type ImageURLs = ImageURL[];


export type PagedModel<T> = {
  totalPages: number;
  totalElements: number;
  size: number;
  number: number;
  content: T[];
}


