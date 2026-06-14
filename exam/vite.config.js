import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue2'
import { resolve } from 'path'

export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      // 映射旧项目中的 @ 别名到 src 目录
      '@': resolve(__dirname, './src')
    },
    // 自动解析的扩展名
    extensions: ['.mjs', '.js', '.ts', '.jsx', '.tsx', '.json', '.vue']
  },
  server: {
    port: 8081, // 你之前 Webpack 的启动端口
    open: true  // 启动时自动打开浏览器
  }
})
