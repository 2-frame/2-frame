import ProductCard from "../ProductCard/ProductCard.tsx";
import {Product} from "../../types/product"

interface NewProductList {
    products: Product[]
}

const NewProductList = ({products}: NewProductList) => {
    return (
        <div className="flex flex-wrap gap-3">
            {products.map(product => <ProductCard {...product}/>)}
        </div>
    )
}

export default NewProductList;