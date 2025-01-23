import axios from "axios";
import {delayFulfilled, handleTokenError, waitingFulfilled} from "./interceptors.ts";

export const axiosInstance = axios.create({
  baseURL: `/api`,
  withCredentials: true,
})

axiosInstance.interceptors.request.use(delayFulfilled);
axiosInstance.interceptors.response.use(waitingFulfilled, handleTokenError);