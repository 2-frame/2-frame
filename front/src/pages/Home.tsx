import {HomeMenuNavigator} from "./Home/HomeMenuNavigator.tsx";
import ProductReviewViewer from "./Home/ProductReviewViewer.tsx";
import RecommendProductViewer from "./Home/RecommendProductViewer.tsx";

const Home = () => {


  return (
    <div className="h-full flex flex-col gap-3">
      <HomeMenuNavigator/>
      <RecommendProductViewer/>
      <ProductReviewViewer/>

      {/*<ProductReviewViewer/>*/}
      {/*<NewProductList products={products}/>*/}
    </div>
  )
}

export default Home;