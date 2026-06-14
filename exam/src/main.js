import Vue from 'vue'
import App from './App.vue' // 修正：加上了 .vue 后缀
import router from './router/index.js' // 修正：加上了 .js 后缀
import echarts from 'echarts'
import axios from 'axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import VueCookies from 'vue-cookies'
import store from '@/vuex/store.js' // 修正：加上了 .js 后缀

Vue.use(ElementUI)
Vue.use(VueCookies)

Vue.config.productionTip = false
Vue.prototype.bus = new Vue()
Vue.prototype.$echarts = echarts
Vue.prototype.$axios = axios

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App) // 优化：Vite 运行版更兼容这种标准的现代渲染写法
})
