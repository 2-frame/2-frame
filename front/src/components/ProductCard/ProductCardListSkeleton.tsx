import ProductCardSkeleton from "./ProductCardSkeleton.tsx";

interface ProductCardListSkeletonProps {
  isRow?: boolean
  n: number
}

export const ProductCardListSkeleton = ({isRow, n}: ProductCardListSkeletonProps) => {
  const className = isRow ? "flex flex-wrap gap-3 justify-center" : "flex gap-3";
  const count = n ? n : 20;

  return <div className={className}>
    {new Array(count).fill(0).map((_, index) => <ProductCardSkeleton key={index}/>)}
  </div>

}