import {CssStyle} from "../types";

type Text = {
    text: string
}
type TypoProps = CssStyle & Text;

const H1 = ({className = "", text}: TypoProps) => <h1 className={"text-5xl ".concat(className)}>{text}</h1>
const H2 = ({className = "", text}: TypoProps) => <h2 className={"text-4xl ".concat(className)}>{text}</h2>
const H3 = ({className = "", text}: TypoProps) => <h3 className={"text-3xl ".concat(className)}>{text}</h3>
const H4 = ({className = "", text}: TypoProps) => <h4 className={"text-2xl ".concat(className)}>{text}</h4>
const H5 = ({className = "", text}: TypoProps) => <h5 className={"text-xl ".concat(className)}>{text}</h5>
const H6 = ({className = "", text}: TypoProps) => <h6 className={"text-lg ".concat(className)}>{text}</h6>

export default {
    H1,
    H2,
    H3,
    H4,
    H5,
    H6
}