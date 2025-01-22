import {PropsWithChildren} from "react";
import Slider from "react-slick";

const settings = {
  infinite: true,
  centerMode: false,
  slidesToShow: 1,
  slidesToScroll: 1,
  variableWidth: true,
  autoplay: true,
  className: "slider variable-width overflow-x-hidden overflow-y-hidden",
  dots: true,
  adaptiveHeight: true,
  slidesPerRow: 1,
  speed: 2000,
  autoplaySpeed: 2000,
};


const SlideContainer = ({children}: PropsWithChildren) => {

  return <div className="slider-container gap-3">
    <Slider {...settings}
    >
      {children}
    </Slider>
  </div>
}

export default SlideContainer;