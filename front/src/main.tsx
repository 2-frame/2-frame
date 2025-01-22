import {StrictMode} from 'react'
import {createRoot} from 'react-dom/client'
import App from './App'
import {NextUIProvider} from "@nextui-org/react";
import {RecoilRoot} from "recoil";
import {QueryClient, QueryClientProvider} from '@tanstack/react-query';

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <NextUIProvider>
      <QueryClientProvider client={new QueryClient({
        defaultOptions: {
          queries: {
            retry: 1
          }
        }
      })}>
        <RecoilRoot>
          <App/>
        </RecoilRoot>
      </QueryClientProvider>
    </NextUIProvider>
  </StrictMode>,
)
