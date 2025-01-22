import {CssStylePropsWithChildren} from "../types";


export const CenterWrapper = ({children, className = ""}: CssStylePropsWithChildren) => {
    return (
        <div className={"flex align-middle justify-center ".concat(className)}>
            {children}
        </div>
    )
}