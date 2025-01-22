import {PropsWithChildren} from "react";

export type CssStyle = {
    className?: string
}

export type CssStylePropsWithChildren = CssStyle & PropsWithChildren;