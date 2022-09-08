import '../styles/globals.css'
import type { AppProps } from 'next/app'
import { createTheme, ThemeProvider } from '@mui/material';

export const theme = createTheme({
  palette: {
    success: {
      main: "#4411AA",
    },
  },
});

function MyApp({ Component, pageProps }: AppProps) {
  return <ThemeProvider theme={theme}><Component {...pageProps} /></ThemeProvider> 
}

export default MyApp
