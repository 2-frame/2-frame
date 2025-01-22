import {CssStylePropsWithChildren} from "../types";


const Between = ({className = "", children}: CssStylePropsWithChildren) => {
  return <div className={"flex justify-between ".concat(className)}>{children}</div>
}


export default {
  Between,
}