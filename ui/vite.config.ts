import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  server: {
    proxy: {
        '/products': {
            target: 'http://localhost:8081',
        },
        '/carts': {
            target: 'http://localhost:8081',
        }
    }
  },
  plugins: [react()],
})
