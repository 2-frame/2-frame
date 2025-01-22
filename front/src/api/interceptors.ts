import {AxiosError, InternalAxiosRequestConfig} from "axios";

export interface ErrorResponseData {
  statusCode: number;
  message: string;
  code: string;
}

export class HTTPError {
  constructor(readonly statusCode: number, readonly message: string, readonly code: number) {
  }
}

export const handleTokenError = async (error: AxiosError<ErrorResponseData>) => {
  const originalRequest = error.config;

  if (!error.response || !originalRequest) throw new Error('에러가 발생했습니다.');

  const data = originalRequest.data;

  throw new HTTPError(status, error.response.data.message, data.code);
}


export const delayFulfilled = (config: InternalAxiosRequestConfig) => ({
  ...config,
  p0: performance.now(),
});

export const waitingFulfilled = async (response: AxiosResponse) => {
  const minimumDelay = 1000;
  const latency = performance.now() - response.config.p0;
  const shouldNotDelay = minimumDelay < latency;

  if (shouldNotDelay) {
    return response;
  }

  const remainder = minimumDelay - latency;
  const [responseWithDelay] = await Promise.all([
    response,
    new Promise((resolve) => setTimeout(resolve, remainder)),
  ]);
  return responseWithDelay;
}