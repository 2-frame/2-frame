import {Accordion, AccordionItem} from "@heroui/react";
import Typo from "../../components/@common/Typo";
import {term as terms} from "./ProductInfo.ts";


const ProductGuide = () => {

  return (
    <>
      <Accordion>
        {terms.map((term, index) => <AccordionItem key={index} title={term.title}>
          <Typo.H6 text={term.description} className="whitespace-pre-line text-gray-400"/>
        </AccordionItem>)}
      </Accordion>
    </>
  )
}

export default ProductGuide;