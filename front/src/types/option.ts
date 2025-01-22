
export type Option = {
  key: string;
  keyName: string;
  value: string;
}


type ChildOption = Option & {
  salesProductId: number;
}

export type ParentOption = Option & {

  child: ChildOption[]
}


export type ProductOption = {
  color: string;
  size: string;
  salesProductId: number;
}